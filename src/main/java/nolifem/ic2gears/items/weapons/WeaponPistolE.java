package nolifem.ic2gears.items.weapons;

import net.minecraft.item.Item;
import nolifem.ic2gears.api.action.ActionChamberingIn;
import nolifem.ic2gears.api.action.ActionChamberingOut;
import nolifem.ic2gears.api.action.ActionReloadEMagazineIn;
import nolifem.ic2gears.api.action.ActionReloadEMagazineOut;
import nolifem.ic2gears.api.action.ActionShootE;
import nolifem.ic2gears.api.action.ActionUpliftE;
import nolifem.ic2gears.api.action.packs.APPistolBatteryIn;
import nolifem.ic2gears.api.action.packs.APBatteryOut;
import nolifem.ic2gears.api.action.packs.APNormalShoot;
import nolifem.ic2gears.api.weapons.WeaponGenericICG;
import cn.weaponmod.api.action.ActionJam;

public class WeaponPistolE extends WeaponGenericICG{

	public WeaponPistolE(String name, Item ammo, boolean consumeInv) {
		super(name, ammo, consumeInv);
		actionShoot = new ActionShootE(5, 4 , "ic2gears:weapons.pistolShoot").setShootRate(4).setConsume(true, this.loadingConsume);
		actionUplift = new ActionUpliftE(3.0F,1.0F,.5F,3.0F);
		actionJam = new ActionJam(10, "");
		actionChamberingIn = new ActionChamberingIn("","",5,15);
		actionChamberingOut = new ActionChamberingOut(40,"","");
		actionReloadMagezineIn = new ActionReloadEMagazineIn(15,1, "").setActionPack(new APPistolBatteryIn());
		actionReloadMagezineOut = new ActionReloadEMagazineOut(20,"").setActionPack(new APBatteryOut());
	}
}
