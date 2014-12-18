package nolifem.ic2gears.api.action;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nolifem.ic2gears.api.weapons.WeaponGenericICG;
import nolifem.ic2gears.api.weapons.WeaponHelperE;
import nolifem.ic2gears.items.ItemAmmo;
import cn.weaponmod.api.WeaponHelper;
import cn.weaponmod.api.action.ActionReload;
import cn.weaponmod.api.information.InfUtils;
import cn.weaponmod.api.information.InfWeapon;
import cn.weaponmod.api.weapon.WeaponGeneric;

/**=============
 * 对ActionReload的扩展
 * 主要关注:
 *  单发子弹装填
 *  正确处理空膛装填
 * @author Nolife_M
 *
 */
public class ActionReloadEBulletOut extends ActionReload{

	//int fastTicks;
	public ActionReloadEBulletOut(int ticks,String snd, String sndFinish) {
		super(ticks, snd , sndFinish);
		this.name = "reloadBOut";
	}

	@Override
	public boolean onActionBegin(World world, EntityPlayer player, InfWeapon inf) {
		ItemStack curItem = player.getCurrentEquippedItem();
		WeaponGenericICG wpnType = (WeaponGenericICG) curItem.getItem();
		if(curItem == null || !(curItem.getItem() instanceof WeaponGenericICG) 
				|| WeaponHelperE.getAmmoID(curItem) == 0 )
			return false;
		player.playSound(sound, 0.5F, 1.0F);
		return true;
	}
	
	@Override
	public boolean onActionTick(World world, EntityPlayer player, InfWeapon inf) {
		return true;
	}
	
	//改动：长按R退出所有弹药
	@Override
	public boolean onActionEnd(World world, EntityPlayer player, InfWeapon inf) {
		ItemStack curItem = player.getCurrentEquippedItem();
		WeaponGenericICG wpnType = (WeaponGenericICG) curItem.getItem();
		ItemStack feedBack;
		if(curItem == null || !(curItem.getItem() instanceof WeaponGenericICG) 
				|| WeaponHelperE.getAmmoID(curItem) == 0 || inf.getTickLeft(this) > 0 )
			return false;
			for(int i = 0; i < WeaponHelperE.getAmmo(curItem); i++){
				feedBack =  new ItemStack(wpnType.getItemById(WeaponHelperE.getAmmoID(curItem)));
				System.out.println("返还子弹中" + feedBack);
				if(!player.inventory.addItemStackToInventory(feedBack))
					player.entityDropItem(feedBack, 1);
			}
			if(WeaponHelperE.getChamber(curItem) != 0){
				feedBack = new ItemStack(wpnType.getItemById(WeaponHelperE.getChamber(curItem)));
				feedBack.stackSize = wpnType.loadingConsume;
				if(!player.inventory.addItemStackToInventory(feedBack))
					player.entityDropItem(feedBack, 1);
				WeaponHelperE.setChamber(curItem, 0);
			}
			WeaponHelperE.setAmmo(curItem, 0);
			//WeaponHelperE.setAmmoID(curItem, 0);
		/*else if(WeaponHelperE.getAmmo(curItem) > 0){
			WeaponHelperE.setAmmo(curItem, WeaponHelperE.getAmmo(curItem) - 1);
			if(!player.inventory.addItemStackToInventory(feedBack))
				player.entityDropItem(feedBack, 1.0F);
			if(WeaponHelperE.getAmmo(curItem) == 0)
				WeaponHelperE.setAmmoID(curItem, 0);
		}*/
			player.playSound(soundFinish, 0.5F, 1.0F);
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
