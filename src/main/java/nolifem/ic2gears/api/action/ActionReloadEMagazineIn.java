package nolifem.ic2gears.api.action;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import nolifem.ic2gears.api.action.packs.APButtomReloadIn;
import nolifem.ic2gears.api.action.packs.APButtomReloadOut;
import nolifem.ic2gears.api.action.packs.ActionPack;
import nolifem.ic2gears.api.weapons.WeaponGenericICG;
import nolifem.ic2gears.api.weapons.WeaponHelperE;
import nolifem.ic2gears.client.render.RenderModelBulletWeaponE;
import nolifem.ic2gears.client.render.RenderModelWeaponE;
import nolifem.ic2gears.items.ItemAmmo;
import cn.weaponmod.api.action.ActionReload;
import cn.weaponmod.api.information.InfWeapon;
import cn.weaponmod.api.weapon.WeaponGeneric;

/**=============
 * 对ActionReload的扩展
 * 主要关注:
 * 弹夹类装填
 * 普通弹夹和电力弹夹通用的Action
 * 正确地消耗电力枪械弹夹的电力
 * 第一次装填将会为弹仓上膛
 * @author Nolife_M
 *
 */
public class ActionReloadEMagazineIn extends ActionReload{

	RenderModelWeaponE renderer;
	ActionPack actionPack;
	
	int amountConsume = 1;
	public ActionReloadEMagazineIn(int ticks, int amountConsume,String snd) {
		super(ticks,  "",snd);
		this.amountConsume = amountConsume;
		//this.name = "reloadMIn";
	}

	public ActionReloadEMagazineIn setActionPack(ActionPack ap){
		this.actionPack = ap;
		return this;
	}
	/**
	 * 步骤整理：首先查找背包内是否有非空弹夹(tryGetAmmo，返回一个弹夹ItemStack)
	 * ——查找到非空弹夹后，给武器弹夹ID赋值，设定前弹容
	 * ——进入ActionEnd
	 */
	@Override
	public boolean onActionBegin(World world, EntityPlayer player, InfWeapon inf) {
		ItemStack curItem = player.getCurrentEquippedItem();
		this.renderer = this.getWeaponRenderer(curItem);
		this.applyRenderEffect(world, player, inf, false);
		return true;
	}
	
	@Override
	public boolean onActionTick(World world, EntityPlayer player, InfWeapon inf) {
		return true;
	}
	
	//改动
	@Override
	public boolean onActionEnd(World world, EntityPlayer player, InfWeapon inf) {
		ItemStack curItem = player.getCurrentEquippedItem();
		WeaponGenericICG wpnType = (WeaponGenericICG) curItem.getItem();
		ItemStack magazine = WeaponHelperE.tryGetAmmo(wpnType.getAmmoList(), player);
		//System.out.println(magazine);
		if(magazine != null)
		{
			//设置当前ClipID
			WeaponHelperE.setAmmoID(curItem, wpnType.getIdFromItem(magazine.getItem()));
			//System.out.println("设定当前弹夹为"+ magazine.getUnlocalizedName());
			//由Clip的maxAmmo决定枪械即时弹夹容量，枪械即时容量一般会比弹夹最大容量多1(当前弹仓多一发)
			//WeaponHelperE.setMaxAmmo(curItem, ((ItemAmmo) magazine.getItem()).getMaxAmmo()+1);
			//System.out.println("设定当前弹容完成");
			//System.out.println("当前stack" + curItem);
			//消耗掉背包中的当前弹夹
			WeaponHelperE.consumClip(player, magazine.getItem());			
			//获取弹药量
			int ammo = WeaponHelperE.getAmmoCapacity(magazine);
			//有自动上弹，无空仓挂机，true
			//有自动上弹，有空仓挂机，处于挂机状态，true
			//有自动上弹
			if(wpnType.isAutoLoading() && ammo > 0)
			{
				System.out.println("枪械支持自动上弹");
				boolean flag = false;
				//无空仓挂机
				if(!wpnType.hasBoltCatch()){
					System.out.println("直接自动上弹");
					flag = true;
				}
				//否则判定其空仓挂机状态
				else if(WeaponHelperE.getBoltCatch(curItem)){
					System.out.println("空仓挂机自动复位上弹");
					flag = true;
				}
				if(flag){
					ammo -= amountConsume;
					//设置弹膛内的Unit，首先返还AmmoID，然后从中读取AmmoItem的UnitID
					int unit = ((ItemAmmo)curItem.getItem().getItemById(WeaponHelperE.getAmmoID(curItem))).getAmmoUnit();
					WeaponHelperE.setChamber(curItem, unit);
				}
				else
					System.out.println("不符合自动上弹条件");
			}
			else
				System.out.println("不能自动上弹，需要手动上膛");
			//设定当前武器的弹药量
			WeaponHelperE.setAmmo(curItem, ammo);
			//完毕
			player.playSound(soundFinish, 0.5F, 1.0F);
			//System.out.println("当前弹容数" + wpnType.getAmmo(curItem) );
			return true;
		}
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void applyRenderEffect(World world, EntityPlayer player, InfWeapon inf, boolean first) {
		ItemStack curItem = player.getCurrentEquippedItem();
		if(curItem != null && actionPack != null){
			renderer.actionPack = actionPack.setRenderer(renderer);
			renderer.actionPack.reset();	
		}
	}
	
	private RenderModelWeaponE getWeaponRenderer(ItemStack curItem){
		return  ((RenderModelBulletWeaponE)MinecraftForgeClient.getItemRenderer(curItem, IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)).mdlRenderer;
	}
	/*@Deprecated
	//由于现在直接置换弹夹，已经不需要判断枪支残弹数
	@Override
	//判定是否可以Reload，在这里不再使用枪械本身的Damage，而是调用当前枪械的MaxAmmo和Ammo对比
	protected boolean canReload(EntityPlayer player, ItemStack stack) {
		WeaponGenericICG wpne = (WeaponGenericICG) stack.getItem();
		int ammo = wpne.getAmmo(stack) ;
		return ammo < WeaponHelperE.getMaxAmmo(stack);
	}*/
}
