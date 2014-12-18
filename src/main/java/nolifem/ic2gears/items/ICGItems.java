package nolifem.ic2gears.items;

import java.util.ArrayList;

import net.minecraft.item.Item;
import nolifem.ic2gears.Ic2Gears;
import nolifem.ic2gears.api.weapons.WeaponGenericICG;


import cpw.mods.fml.common.registry.GameRegistry;

/**============
 * 物品总处理类
 * 说明：这个类内包含需要在主类中调用的所有方法
 * 这个类的存在是为了让mod结构更清晰明了，并且统一对物品的加入、注册进行处理
 * 如果需要处理的信息不多，其实可以不要这个类，把里面的方法直接写入主类中（这样容易让主类臃肿
 * ============
 * @author Nolife_M
 *
 */
public class ICGItems {
	
	//在这里新建物品
	public static ItemAmmo lowElecMagazine;
	public static ItemAmmo normalElecMagazine;
	public static ItemAmmo elecNagnumBullet;
	public static Item normalPistolMark1;
	public static Item normalRevolverMark1;
	
	//在这里具体加入物品
	public static void ItemInit(){
		
		
		
		//小型弹夹，最大储存电20000EU，能量等级2，额外设定12发装弹，MaxDamage为12+2
		lowElecMagazine = ((ItemAmmo) new ItemElecAmmo("lowElecMagazine", 20000, 2,80 ).setMaxDamage(14)).setAmmoType(ItemAmmo.TYPE_MAGAZINE);
		normalElecMagazine = ((ItemAmmo) new ItemElecAmmo("normalElecMagazine",40000,2,160).setMaxDamage(27)).setAmmoType(ItemAmmo.TYPE_MAGAZINE);
		elecNagnumBullet = ((ItemAmmo) new ItemAmmo("elecNagnumBullet").setMaxDamage(0).setMaxStackSize(16)).setAmmoType(ItemAmmo.TYPE_BULLET);
		//mark-1手枪，装弹夹型
		normalPistolMark1 = new WeaponGenericICG("normalPistolMark1",ICGItems.lowElecMagazine, false)
			.addAmmo(ICGItems.normalElecMagazine).setBoltCatch(true);
		normalRevolverMark1 = ((WeaponGenericICG)new WeaponGenericICG("normalRevolverMark1",ICGItems.elecNagnumBullet, false).setMaxDamage(5)).setDirectLoading(true);
		
		ICGItems.registerItems();	
		
		//在这里为需要AmmoUnit的物品分配
		//尤其是Bullet类，注意其以自身ID为其AmmoUnit
		elecNagnumBullet.setAmmoUnitItem(elecNagnumBullet);
	}
	
	public static void ItemPostInit(){
	}
	
	//物品注册，将这个方法在上面的方法中调用即可
	public static void registerItems(){
		ICGItems.registerItem(ICGItems.lowElecMagazine);
		ICGItems.registerItem(ICGItems.normalElecMagazine);
		ICGItems.registerItem(ICGItems.elecNagnumBullet);
		ICGItems.registerItem(ICGItems.normalPistolMark1);
		ICGItems.registerItem(ICGItems.normalRevolverMark1);
	}
	
	//自己写一个方法将后面的内容囊括进去
	private static void registerItem(Item item)
    {
        GameRegistry.registerItem(item, item.getUnlocalizedName(), Ic2Gears.MODID);
    }
}
