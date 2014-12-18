package nolifem.ic2gears.api.action;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nolifem.ic2gears.api.weapons.WeaponGenericICG;
import nolifem.ic2gears.api.weapons.WeaponHelperE;
import nolifem.ic2gears.items.ItemAmmo;
import cn.weaponmod.api.action.ActionReload;
import cn.weaponmod.api.information.InfWeapon;

/**=============
 * 对ActionReload的扩展
 * 主要关注:
 *  多发子弹装填
 *  返还Clip载体
 * @author Nolife_M
 *
 */
public class ActionReloadEClipIn extends ActionReload{

	public ActionReloadEClipIn(int ticks, String sndFinish) {
		super(ticks, "", sndFinish);
		this.name = "reloadCIn";
	}

	@Override
	public boolean onActionBegin(World world, EntityPlayer player, InfWeapon inf) {
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
		ItemStack clip = WeaponHelperE.tryGetAmmo(wpnType.getAmmoList(), player);
		System.out.println(clip);
		//空膛或弹药未满
		if(WeaponHelperE.getAmmoID(curItem) == 0 
				|| (WeaponHelperE.getAmmoID(curItem) == ((ItemAmmo)clip.getItem()).getAmmoUnit()
				&& WeaponHelperE.getAmmo(curItem)+clip.getMaxDamage() <= wpnType.getMaxDamage())){
			player.inventory.consumeInventoryItem(clip.getItem());
			ItemStack feedback = new ItemStack(clip.getItem().getContainerItem());
			if(!player.inventory.addItemStackToInventory(feedback))
				player.entityDropItem(feedback, 1.0F);
			WeaponHelperE.setAmmo(curItem, WeaponHelperE.getAmmo(curItem) + clip.getMaxDamage());
			WeaponHelperE.setAmmoID(curItem,((ItemAmmo)clip.getItem()).getAmmoUnit());
			player.playSound(soundFinish, 0.5F, 1.0F);
		}
		return true;
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
