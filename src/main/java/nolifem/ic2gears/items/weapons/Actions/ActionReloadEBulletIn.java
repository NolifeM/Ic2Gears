package nolifem.ic2gears.items.weapons.Actions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nolifem.ic2gears.items.weapons.WeaponGenericICG;
import nolifem.ic2gears.items.weapons.WeaponHelperE;
import nolifem.ic2gears.items.weapons.Actions.Parts.ActionBREnd;
import nolifem.ic2gears.items.weapons.Actions.Parts.ActionBRStart;
import nolifem.ic2gears.items.weapons.Actions.Parts.ActionBulletSingleIn;
import cn.weaponmod.api.action.Action;
import cn.weaponmod.api.action.ActionReload;
import cn.weaponmod.api.information.InfWeapon;

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
		starter = new ActionBRStart(startTicks,snd);
		loader = new ActionBulletSingleIn(singleTicks, sndIn);
		ender = new ActionBREnd(finishTicks,sndFinish);
		//this.name = "reloadBIn";
	}

	@Override
	public boolean doesConcurrent(Action other) {
		return true;
	}
	
	@Override
	public boolean onActionBegin(World world, EntityPlayer player, InfWeapon inf) {
		ItemStack curItem = player.getCurrentEquippedItem();
		WeaponGenericICG wpnType = (WeaponGenericICG) curItem.getItem();
		int ammoNeeded = wpnType.getMaxDamage() - WeaponHelperE.getAmmo(curItem);
		if(wpnType.canDirectLoading())
			ammoNeeded+= wpnType.loadingConsume;
		if(curItem == null || !(curItem.getItem() instanceof WeaponGenericICG) || ammoNeeded <= 0)
			return false;
		this.maxTick = startTicks + singleTicks*ammoNeeded/wpnType.loadingConsume -1;
		System.out.println("开始上弹过程，需要的弹数:"+ ammoNeeded + "总时长" + maxTick);
		inf.executeAction(starter);
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
		WeaponGenericICG wpnType = (WeaponGenericICG) curItem.getItem();
		if(curItem == null || !(curItem.getItem() instanceof WeaponGenericICG))
			return false;
		System.out.println("结束上弹过程");
		inf.executeAction(ender);
		return true;
	}
	
	protected boolean stackFine(ItemStack stack, EntityPlayer player) {
		if(stack.getItem() instanceof WeaponGenericICG) {
			return player.capabilities.isCreativeMode || WeaponHelperE.getChamber(stack) != 0;
		}
		return false;
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
