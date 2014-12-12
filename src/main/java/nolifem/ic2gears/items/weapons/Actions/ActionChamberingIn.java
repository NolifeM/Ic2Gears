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
 * 上膛的Action，会根据按下时不同的状况选择不同的上膛动作
 * @author Nolife_M
 */
public class ActionChamberingIn extends Action {

	public String sound = "";
	public int maxTick1 = 10,
			maxTick2 = 10;
	
	public ActionChamberingIn(String sound1,String sound2,int maxTick1,int maxTick2) {
		super(10, "chamberIn");
		this.sound = sound2;
		this.maxTick = maxTick2;
		this.maxTick1 = maxTick1;
		this.maxTick2 = maxTick2;
	}

	@Override
	public int getPriority() {
		return 2;
	}

	@Override
	public boolean doesConcurrent(Action other) {
		return false;
	}

	/*动作调用时弹仓无弹，此时要做的事情便是将弹药压入弹仓，或恢复挂机
	 * 分两种情况：空仓挂机压弹、一般压弹
	 * Begin时判断Action的时长，End时完成装弹或者恢复挂机装填
	 */
	@Override
	public boolean onActionBegin(World world, EntityPlayer player, InfWeapon inf) {
		ItemStack curItem = player.getCurrentEquippedItem();
		WeaponGenericICG wpnType = (WeaponGenericICG) curItem.getItem();
		if(WeaponHelperE.getChamber(curItem) != 0)
			return false;
		if(curItem == null || !(curItem.getItem() instanceof WeaponGenericICG))
			return false;
		//如果此时处于挂机状态，则可快速上弹
		if(wpnType.hasBoltCatch() && WeaponHelperE.getBoltCatch(curItem)){
			System.out.println("空仓挂机上弹");
			this.maxTick = maxTick1;
		}
		return true;
	}
	
	@Override
	public boolean onActionEnd(World world, EntityPlayer player, InfWeapon inf) {
		ItemStack curItem = player.getCurrentEquippedItem();
		WeaponGenericICG wpnType = (WeaponGenericICG) curItem.getItem();
		if(WeaponHelperE.getChamber(curItem) != 0)
			return false;
		if(curItem == null || !(curItem.getItem() instanceof WeaponGenericICG))
			return false;
		//如果此时非挂机状态，则完成挂机
		if(wpnType.hasBoltCatch()){
			if(!WeaponHelperE.getBoltCatch(curItem))
				WeaponHelperE.setBoltCatch(curItem, true);
			else
				WeaponHelperE.setBoltCatch(curItem, false);
		}
		//若武器中有弹药，且余弹大于0，给弹仓补上弹药
		if(WeaponHelperE.getAmmoID(curItem) != 0 && WeaponHelperE.getAmmo(curItem) > 0){
			ItemAmmo ammo = (ItemAmmo) wpnType.getItemById(WeaponHelperE.getAmmoID(curItem));
			WeaponHelperE.setAmmo(curItem, WeaponHelperE.getAmmo(curItem) - wpnType.loadingConsume);
			WeaponHelperE.setChamber(curItem,ammo.getAmmoUnit());
		}
		return true;
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
