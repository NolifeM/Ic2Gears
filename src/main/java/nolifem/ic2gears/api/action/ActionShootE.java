package nolifem.ic2gears.api.action;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nolifem.ic2gears.api.weapons.WeaponGenericICG;
import nolifem.ic2gears.api.weapons.WeaponHelperE;
import cn.weaponmod.api.action.Action;
import cn.weaponmod.api.action.ActionShoot;
import cn.weaponmod.api.information.InfUtils;
import cn.weaponmod.api.information.InfWeapon;
import cn.weaponmod.api.weapon.WeaponGeneric;
import cn.weaponmod.api.weapon.WeaponGenericBase;

/**=============
 * 对ActionShoot的扩展
 * 在这里完成弹药消耗的判定
 * @author Nolife_M
 *=============
 */
public class ActionShootE extends ActionShoot {

	public ActionShootE(int dmg, float scat, String snd) {
		super(dmg, scat, snd);
	}

	protected Action getActionUplift(InfWeapon inf) {
		ItemStack cs = inf.owner.getCurrentEquippedItem();
		if(cs == null) return null;
		
		Item it = cs.getItem();
		if(it instanceof WeaponGenericICG) {
			return ((WeaponGenericICG) it).actionUplift;
		}
		return null;
	}
	
	//通过consumeInv来判定弹药comsume的部分
	@Override
	protected boolean consumeAmmo(EntityPlayer player, ItemStack stack, int amount) {
		WeaponGenericICG wpne = (WeaponGenericICG)stack.getItem();
		if(wpne.consumeInv) { //Consume inventory
			//这样会尝试消耗物品栏内的弹药或弹夹
			return WeaponHelperE.consumeAmmo(player, wpne, amount) == 0;
		}
		//武器本身尝试消耗弹药
		return wpne.consumeAmmo(stack, amount);
	}
}
