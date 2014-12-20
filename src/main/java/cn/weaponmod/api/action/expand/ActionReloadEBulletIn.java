package cn.weaponmod.api.action.expand;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cn.weaponmod.api.action.Action;
import cn.weaponmod.api.action.ActionReload;
import cn.weaponmod.api.action.expand.part.ActionBREnd;
import cn.weaponmod.api.action.expand.part.ActionBRStart;
import cn.weaponmod.api.action.expand.part.ActionBulletSingleIn;
import cn.weaponmod.api.information.InfWeapon;
import cn.weaponmod.api.weapon.expand.WeaponGenericE;
import cn.weaponmod.api.weapon.expand.WeaponHelperE;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**=============
 * 对ActionReload的扩展
 * 主要关注:
 *  单发子弹装填
 *  正确处理空膛装填
 * @author Nolife_M
 *
 */
public class ActionReloadEBulletIn extends ActionReload{

	int startTicks = 10,singleTicks =5,finishTicks = 10;
	
	public ActionBRStart starter;
	public ActionBulletSingleIn loader;
	public ActionBREnd ender;
	
	public ActionReloadEBulletIn(int startTicks,int singleTicks, int finishTicks, String snd, String sndIn,String sndFinish) {
		super(200, snd, sndFinish);
		this.startTicks = startTicks;
		this.singleTicks =singleTicks;
		this.finishTicks = finishTicks;
		starter = new ActionBRStart(startTicks,"");
		loader = new ActionBulletSingleIn(singleTicks, sndIn);
		ender = new ActionBREnd(finishTicks,sndFinish);
		this.name = "reloadBIn";
	}

	@Override
	public boolean doesConcurrent(Action other) {
		return true;
	}
	
	@Override
	public boolean onActionBegin(World world, EntityPlayer player, InfWeapon inf) {
		ItemStack curItem = player.getCurrentEquippedItem();
		WeaponGenericE wpnType = (WeaponGenericE) curItem.getItem();
		int ammoNeeded = wpnType.getMaxDamage() - WeaponHelperE.getAmmo(curItem);
		if(wpnType.canDirectLoading() && WeaponHelperE.getChamber(curItem) == 0)
			ammoNeeded+= wpnType.loadingConsume;
		if(curItem == null || !(curItem.getItem() instanceof WeaponGenericE))
			return false;
		this.maxTick = startTicks + singleTicks*ammoNeeded/wpnType.loadingConsume -1;
		System.out.println("开始上弹过程，需要的弹数:"+ ammoNeeded + "总时长" + maxTick);
		inf.executeAction(starter);
		player.playSound(sound, 0.5F, 1.0F);
		return true;
	}
	
	@Override
	public boolean onActionTick(World world, EntityPlayer player, InfWeapon inf) {
		int te = maxTick - inf.getTickLeft(this);
		System.out.println("当前ticks" + te);
		if(te >= startTicks){
			te -= startTicks;
			te += singleTicks;
			if(te % singleTicks == 0) {
				System.out.println("装弹");
				inf.executeAction(loader);
				if(!stackFine(player.getCurrentEquippedItem(), player)) 
					return false;
			}
		}
		return true;	
	}
	
	//改动
	@Override
	public boolean onActionEnd(World world, EntityPlayer player, InfWeapon inf) {
		ItemStack curItem = player.getCurrentEquippedItem();
		WeaponGenericE wpnType = (WeaponGenericE) curItem.getItem();
		/*System.out.println("结束上弹过程");*/
		if(curItem == null || !(curItem.getItem() instanceof WeaponGenericE) || inf.getTickLeft(this) > 0)
			return false;
		inf.executeAction(ender);
		return true;
	}
	
	protected boolean stackFine(ItemStack stack, EntityPlayer player) {
		if(stack.getItem() instanceof WeaponGenericE) {
			return player.capabilities.isCreativeMode || WeaponHelperE.getAmmo(stack) < stack.getItem().getMaxDamage();
		}
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void applyRenderEffect(World world, EntityPlayer player, InfWeapon inf, boolean first) {
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
