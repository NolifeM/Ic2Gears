package nolifem.ic2gears.items.weapons.Actions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nolifem.ic2gears.items.weapons.WeaponGenericICG;
import nolifem.ic2gears.items.weapons.WeaponHelperE;
import cn.weaponmod.api.action.ActionAutomaticShoot;
import cn.weaponmod.api.action.ActionShoot;
import cn.weaponmod.api.information.InfWeapon;
import cn.weaponmod.api.weapon.WeaponGenericBase;

/**===========扩展AutomaticShootE
 * 	
 * @author Nolife_M
 *
 */
public class ActionAutomaticShootE extends ActionAutomaticShoot{
	
	int rate;
	public  ActionShootE shooter;
	
	public ActionAutomaticShootE copy() {
		return new ActionAutomaticShootE(shooter, rate, this.maxTick);
	}
	
	public ActionAutomaticShootE(ActionShootE act, int ticks, int shootRate) {
		super(act, shootRate,ticks);
		shooter = act;
		rate = shootRate;
		shooter.setShootRate(shootRate - 1);
	}
	@Override
	public boolean onActionBegin(World world, EntityPlayer player, InfWeapon information) {
		information.executeAction(shooter);
		//System.out.print(shooter);
		System.out.println("连射动作Begin");
		return true;
	}

	@Override
	public boolean onActionEnd(World world, EntityPlayer player, InfWeapon information) {
		return false;
	}
	
	@Override
	public boolean onActionTick(World world, EntityPlayer player, InfWeapon inf) {
		int te = maxTick - inf.getTickLeft(this);
		if(te % rate == 0) {
			inf.executeAction(shooter);
			if(!stackFine(player.getCurrentEquippedItem(), player)) {
				System.out.println("Tick退出点");
				return false;
			}
		}
		System.out.println("可以连射，发射");
		return true;
	}
	/**
	 * 武器是否准备完毕
	 * 检测弹膛中是否有弹即可
	 */
	protected boolean stackFine(ItemStack stack, EntityPlayer player) {
		if(stack.getItem() instanceof WeaponGenericICG) {
			System.out.println("武器符合连射条件检测" + (player.capabilities.isCreativeMode || WeaponHelperE.getChamber(stack) != 0));
			return player.capabilities.isCreativeMode || WeaponHelperE.getChamber(stack) != 0;
		}
		return false;
	}
}
