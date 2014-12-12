package nolifem.ic2gears.items.weapons.Actions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nolifem.ic2gears.items.ItemAmmo;
import nolifem.ic2gears.items.weapons.WeaponGenericICG;
import nolifem.ic2gears.items.weapons.WeaponHelperE;
import cn.weaponmod.api.action.Action;
import cn.weaponmod.api.information.InfWeapon;
import cn.weaponmod.api.weapon.WeaponGeneric;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**======上膛动作======
 * 退膛的Action，会根据按下时不同的状况选择不同的退膛动作
 * 轻点会退弹，点至放开，如不为非自动武器，且弹夹内有弹，会自动上一发弹。
 * @author Nolife_M
 */
public class ActionChamberingOut extends Action {

	public String sound = "", soundFinish = "";
	
	public ActionChamberingOut(int maxTick,String snd,String sndFinish) {
		super(maxTick, "chamberOut");
		sound = snd;
		soundFinish = sndFinish;
	}

	@Override
	public int getPriority() {
		return 2;
	}

	@Override
	public boolean doesConcurrent(Action other) {
		return false;
	}

	/*动作调用时弹仓有弹，此时执行退弹动作
	 * 提前取消动作，即可实现只退弹、不上弹
	 */
	@Override
	public boolean onActionBegin(World world, EntityPlayer player, InfWeapon inf) {
		ItemStack curItem = player.getCurrentEquippedItem();
		WeaponGenericICG wpnType = (WeaponGenericICG) curItem.getItem();
		int chambering = WeaponHelperE.getChamber(curItem);
		System.out.println(chambering);
		if(chambering == 0 ||curItem == null || !(curItem.getItem() instanceof WeaponGenericICG))
			return false;
		if(chambering != 1){
			ItemStack bullet = new ItemStack(wpnType.getItemById(chambering));
			System.out.println(bullet);
			bullet.stackSize = wpnType.loadingConsume;
			//System.out.println("尝试将子弹返还背包" + bullet);
			if(!player.inventory.addItemStackToInventory(bullet)){
				System.out.println("返还至背包失败");
				player.entityDropItem(bullet, 1);
			}				
		}
		else
			System.out.println("能量弹类型，已作释放处理");
		WeaponHelperE.setChamber(curItem, 0);
		player.playSound(sound, 0.5F, 1.0F);
		return true;
	}
	
	@Override
	public boolean onActionEnd(World world, EntityPlayer player, InfWeapon inf) {
		ItemStack curItem = player.getCurrentEquippedItem();
		WeaponGenericICG wpnType = (WeaponGenericICG) curItem.getItem();
		if(WeaponHelperE.getChamber(curItem) != 0 || curItem == null || !(curItem.getItem() instanceof WeaponGenericICG || inf.getTickLeft(this)>0))
			return false;
		//若不是全手动，会恢复上膛
		if(!wpnType.isFullManual()){
			int orig = WeaponHelperE.getAmmo(curItem);
			if(orig >= wpnType.loadingConsume) {
				WeaponHelperE.setAmmo(curItem, orig - wpnType.loadingConsume);
				if(WeaponHelperE.getAmmoID(curItem) != 0){
					int unit = ((ItemAmmo)curItem.getItem().getItemById(WeaponHelperE.getAmmoID(curItem))).getAmmoUnit();
					WeaponHelperE.setChamber(curItem, unit);
					System.out.println("弹膛ID设为"+ unit);
					player.playSound(soundFinish, 0.5F, 1.0F);	
				}		
			}
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean onActionTick(World world, EntityPlayer player, InfWeapon inf) {
		return true;
	}
		
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderPriority() {
		return 2;
	}

}
