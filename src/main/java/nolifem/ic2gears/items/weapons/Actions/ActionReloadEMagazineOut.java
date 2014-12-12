package nolifem.ic2gears.items.weapons.Actions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nolifem.ic2gears.items.ItemAmmo;
import nolifem.ic2gears.items.ItemElecAmmo;
import nolifem.ic2gears.items.weapons.WeaponGenericICG;
import nolifem.ic2gears.items.weapons.WeaponHelperE;
import cn.weaponmod.api.WeaponHelper;
import cn.weaponmod.api.action.ActionReload;
import cn.weaponmod.api.information.InfWeapon;
import cn.weaponmod.api.weapon.WeaponGeneric;

/**=============
 * 对ActionReload的扩展
 * 主要关注:
 * 取出当前枪械内的弹夹
 * 正确地返还电力枪械弹夹的电力
 * @author Nolife_M
 *
 */
public class ActionReloadEMagazineOut extends ActionReload{

	public ActionReloadEMagazineOut(int ticks, String snd) {
		super(ticks, snd, "");
		//this.name = "reloadMOut";
	}

	/**
	 * 步骤整理：读取枪械内弹夹并返还至player背包内
	 * ——重置当前枪械状况
	 * ——进入ActionEnd
	 */
	@Override
	public boolean onActionBegin(World world, EntityPlayer player, InfWeapon inf) {
		ItemStack curItem = player.getCurrentEquippedItem();
		WeaponGenericICG wpnType = (WeaponGenericICG) curItem.getItem();
		if(curItem == null || !(curItem.getItem() instanceof WeaponGenericICG || WeaponHelperE.getAmmoID(curItem) == 0))
			return false;
		player.playSound(sound, 0.5F, 1.0F);
			return true;
	}
	
	@Override
	public boolean onActionTick(World world, EntityPlayer player, InfWeapon inf) {
		return true;
	}
	
	@Override
	public boolean onActionEnd(World world, EntityPlayer player, InfWeapon inf) {
		//获取当前武器信息
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
