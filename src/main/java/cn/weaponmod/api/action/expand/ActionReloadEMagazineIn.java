package cn.weaponmod.api.action.expand;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import cn.weaponmod.api.action.ActionReload;
import cn.weaponmod.api.action.expand.pack.ActionPack;
import cn.weaponmod.api.client.render.RenderModelWeapon;
import cn.weaponmod.api.client.render.RendererModelBulletWeapon;
import cn.weaponmod.api.information.InfWeapon;
import cn.weaponmod.api.weapon.expand.ItemAmmo;
import cn.weaponmod.api.weapon.expand.WeaponGenericE;
import cn.weaponmod.api.weapon.expand.WeaponHelperE;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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

	int amountConsume = 1;
	public ActionReloadEMagazineIn(int ticks, int amountConsume,String snd) {
		super(ticks,  "",snd);
		this.amountConsume = amountConsume;
		//this.name = "reloadMIn";
	}

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
		WeaponGenericE wpnType = (WeaponGenericE) curItem.getItem();
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
}
