package nolifem.ic2gears.items.weapons;

import net.minecraft.item.Item;
import cn.weaponmod.api.action.ActionJam;
import cn.weaponmod.api.action.ActionUplift;
import cn.weaponmod.api.action.expand.ActionChamberingIn;
import cn.weaponmod.api.action.expand.ActionChamberingOut;
import cn.weaponmod.api.action.expand.ActionReloadEMagazineIn;
import cn.weaponmod.api.action.expand.ActionReloadEMagazineOut;
import cn.weaponmod.api.action.expand.ActionShootE;
import cn.weaponmod.api.action.expand.pack.APBatteryOut;
import cn.weaponmod.api.action.expand.pack.APPistolBatteryIn;
import cn.weaponmod.api.weapon.expand.WeaponGenericE;

public class WeaponPistolE extends WeaponGenericE{

	public WeaponPistolE(String name, Item ammo, boolean consumeInv) {
		super(name, ammo, consumeInv);
		actionShoot = new ActionShootE(5, 4 , "ic2gears:weapons.pistolShoot").setShootRate(4).setConsume(true, this.loadingConsume);
		actionUplift = new ActionUplift(3.0F,1.0F,.5F,3.0F);
		actionJam = new ActionJam(10, "");
		actionChamberingIn = new ActionChamberingIn("","",5,15);
		actionChamberingOut = new ActionChamberingOut(40,"","");
		actionReloadMagezineIn = new ActionReloadEMagazineIn(15,1, "").setActionPack(new APPistolBatteryIn());
		actionReloadMagezineOut = new ActionReloadEMagazineOut(20,"").setActionPack(new APBatteryOut());
	}
}
