package nolifem.ic2gears.api.weapons;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nolifem.ic2gears.Ic2Gears;
import nolifem.ic2gears.api.action.ActionAutomaticShootE;
import nolifem.ic2gears.api.action.ActionChamberingIn;
import nolifem.ic2gears.api.action.ActionChamberingOut;
import nolifem.ic2gears.api.action.ActionReloadEBulletIn;
import nolifem.ic2gears.api.action.ActionReloadEBulletOut;
import nolifem.ic2gears.api.action.ActionReloadEClipIn;
import nolifem.ic2gears.api.action.ActionReloadEMagazineIn;
import nolifem.ic2gears.api.action.ActionReloadEMagazineOut;
import nolifem.ic2gears.api.action.ActionShootE;
import nolifem.ic2gears.items.ItemAmmo;
import cn.weaponmod.api.action.Action;
import cn.weaponmod.api.action.ActionJam;
import cn.weaponmod.api.action.ActionUplift;
import cn.weaponmod.api.information.InfWeapon;
import cn.weaponmod.api.weapon.WeaponGeneric;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**===========
 * WeaponGeneric扩展
 * 尝试通过NBT和Damage管理当前武器的弹夹容量
 * ===========
 * @author Nolife_M
 */
public class WeaponGenericICG extends WeaponGeneric{

	/**==========
	 * IC2Gears总武器类
	 * 总说明：
	 * 1.建立实例时，请调用setMaxDamage决定武器类型，给武器分配的Ammo也在一定程度上帮助决定武器类型
	 * 
	 * 枪械系统构造：枪械本身含有两个储弹方式，chamber(枪膛)和Ammo(备弹)
	 * 			
	 * 枪械类型需知：
	 * 1.任何使用Magazine的枪，枪体本身不提供内弹容的武器，不需要setMaxDamage，默认为0，并且非常不建议提供内弹容
	 *    这种枪可以通过setDirectChambering来支持单发Bullet直接入膛
	 * 2.任何只使用Bullet的枪械，枪体本身可以提供弹容（如霰弹枪等），请setMaxDamage为最大弹容
	 *    如果为左轮，建议setDirectChambering，并setMaxDamage为最大弹容-1
	 * 
	 * consumeInv若设为true，由于武器直接消耗物品栏内弹夹，将不会有上弹动作
	 * @param name 名称
	 * @param  虽然使用同一个参数，但与Ammo不同，武器会区分使用弹夹和直接使用弹药
	 * 现在的仅作为一个默认支持的弹药存在
	 * @param consumeInv 是否消耗物品栏内弹药
	 */
	public boolean consumeInv;
	//默认无空仓挂机和自动复位
	/*===空仓挂机和自动装填的关系===
	 * 如果一个枪械有自动装填功能，那么当其装入弹药时就会自动装填
	 * 但如果一个枪械同时有空仓挂机功能，则装入弹药时会判断其空仓挂机状态，如果空仓挂机状态为false，枪械不会复位
	 * 此时玩家需要手动按动上膛键
	 * 
	 */
	//上膛消耗
	public int loadingConsume = 1;
	//自动上膛、空仓挂机自动复位
	private boolean autoLoading = false,
	//可空仓挂机 
			boltCatch = false,
	//全手动武器（非自动武器）
			fullManual = false,
	//直接装膛（在Bullet装入时如果弹膛空，直接装入弹膛）
			directLoad = false,
	//快速清仓
			fastClear = false;
	//自动重装膛（在退膛后）
			//loadAfterUnload = false;	
	private ArrayList List = new ArrayList();
	
	public WeaponGenericICG(String name,Item ammo ,boolean consumeInv) {
		super(ammo);
		this.setUnlocalizedName(name);
		this.setTextureName(Ic2Gears.TEXTURE_PREFIX + name);
		this.consumeInv = consumeInv;
		this.List.add(ammo);
	}
	
	//设置List内加入支持的弹夹
	public WeaponGenericICG addAmmo(Item item){
		this.List.add(item);
		return this;
	}
	
	//获取整个ArrayList
	public ArrayList getAmmoList(){
		return this.List;
	}
	
	//判断Item是否在List内
	public boolean inList(Item item){
		for(int i = 0; i < List.size();i++){
			if (item == List.get(i)){
				return true;
			}
		}
		return false;
	}
	
	@Override
    public CreativeTabs getCreativeTab()
    {
        return Ic2Gears.Ic2GearsWeaponsTab;
    }
	
	//设置可自动上弹
	public WeaponGenericICG setAutoLoading(boolean b){
		this.autoLoading = b;
		return this;
	}
	
	public boolean isAutoLoading(){
		return this.autoLoading;
	}
	
	//可空仓挂机
	public WeaponGenericICG setBoltCatch(boolean b){
		this.boltCatch = b;
		return this;
	}
	
	public boolean hasBoltCatch(){
		return this.boltCatch;
	}
	
	//全手动
	public WeaponGenericICG setFullManual(boolean b){
		this.fullManual = b;
		return this;
	}
	
	public boolean isFullManual(){
		return this.fullManual;
	}
	
	//直接装膛
		public WeaponGenericICG setDirectLoading(boolean b){
			this.directLoad = b;
			return this;
		}
		
		public boolean canDirectLoading(){
			return this.directLoad;
		}
		//快速清仓
		public WeaponGenericICG setFastClear(boolean b){
			this.fastClear = b;
			return this;
		}
		
		public boolean canFastClear(){
			return this.fastClear;
		}
		/*
		public WeaponGenericICG setLoadAfterUnload(boolean b){
			this.loadAfterUnload = b;
			return this;
		}
		
		public boolean canLoadAfterUnload(){
			return this.loadAfterUnload;
		}*/

	/**
	 * 定义一些Action，不同的Action可以搭配出各种各样的武器
	 */
	public Action
		actionShoot = new ActionShootE(1, 2 , "").setShootRate(1).setConsume(true, this.loadingConsume),
		actionShootAuto = new ActionAutomaticShootE((ActionShootE)actionShoot,300,1),
		actionUplift = new ActionUplift(3.0F,1.0F,.5F,3.0F),
		actionChamberingIn = new ActionChamberingIn("","",5,15),
		actionChamberingOut = new ActionChamberingOut(40,"",""),
		actionReloadBulletIn = new ActionReloadEBulletIn(20,10,15, "", "", ""),
		actionReloadBulletOut = new ActionReloadEBulletOut(25, "", ""),
		actionReloadClipIn = new ActionReloadEClipIn(10, ""),
		actionReloadMagezineIn = new ActionReloadEMagazineIn(15,1, ""),
		actionReloadMagezineOut = new ActionReloadEMagazineOut(20,""),
		actionJam = new ActionJam(10, "");	
	
	//按键部分重载，主要是使用自己的Action
	@Override
	public void onItemClick(World world, EntityPlayer player, ItemStack stack, int keyid) {
		InfWeapon inf = loadInformation(player);
		switch(keyid) {
		case 0: //LMOUSE
			//调试信息
			System.out.println("枪械状态:");
			System.out.println("空仓挂机状态:" + WeaponHelperE.getBoltCatch(stack));
			System.out.println("膛内子弹ID:" + WeaponHelperE.getChamber(stack));
			System.out.println("枪内备弹数:" + WeaponHelperE.getAmmo(stack));
			if(WeaponHelperE.getAmmoID(stack) != 0){
				Item ammo = stack.getItem().getItemById(WeaponHelperE.getAmmoID(stack));
				System.out.println("弹匣ID:" + WeaponHelperE.getAmmoID(stack));
			}
			if(!canShoot(player, stack)) {
				inf.executeAction(actionJam);
			} else {
				//System.out.println("执行连射动作");
				inf.executeAction(actionShoot);
			}
			break;
		case 1://RMOUSE
			break;
		case 2: //Reload
			/*需要完成的动作：
			 * 优先级（从高至低）：
			 * 为无弹匣武器装上弹匣
			 * 为有弹匣武器卸下弹匣
			 * 为有弹匣且空仓武器单发装填
			 * 为任何武器单发或clip装填
			 * =============
			 * AmmoID标记的是武器当前使用的Ammo（包括三种类型）
			 * 如果AmmoID为0，说明当前武器没有使用任何Ammo
			 * 此时按R会先进行背包内可用弹药检测，之后进行对应装填
			 * =============
			 * 如果AmmoID不为0，检测武器内的Ammo，之后进行对应卸下
			 */
			//执行装填的情况：无Ammo，或AmmoItem为子弹型且弹仓不满
			if(WeaponHelperE.getAmmoID(stack) == 0 
			|| ((ItemAmmo)Item.getItemById(WeaponHelperE.getAmmoID(stack))).getAmmoType() == ItemAmmo.TYPE_BULLET 
			&& WeaponHelperE.getAmmo(stack) < stack.getMaxDamage()){//执行装填部分
				//在玩家背包中寻找符合AmmoList的Item
				System.out.println("执行装填");
				if(WeaponHelperE.tryGetAmmo(((WeaponGenericICG)stack.getItem()).getAmmoList(), player) != null){
					Item ammo = WeaponHelperE.tryGetAmmo(((WeaponGenericICG)stack.getItem()).getAmmoList(), player).getItem();
					if( ammo != null){
						//判断ammo类型
						switch(((ItemAmmo) ammo).getAmmoType()){
						case 1:	//Bullet类型
							System.out.println("Bullet型");
							inf.executeAction(actionReloadBulletIn);
							break;
						case 2://CLIP类型，如旧式步枪的弹夹，加兰德的弹药包，左轮快速上弹器等
							System.out.println("Clip型");
							inf.executeAction(actionReloadClipIn);
							break;
						case 3://Magazine类型，弹匣
							System.out.println("Magazine型");
							inf.executeAction(actionReloadMagezineIn);
							break;
						}
					}
				}
			}
			else{//执行卸下部分
				//获取当前枪械装填的Ammo
				Item ammo = stack.getItem().getItemById(WeaponHelperE.getAmmoID(stack));
				if( ammo != null){
					//判断ammo类型
					switch(((ItemAmmo) ammo).getAmmoType()){
					case 1:	//Bullet类型
					case 2://CLIP类型，如旧式步枪的弹夹，加兰德的弹药包，左轮快速上弹器等
						System.out.println("Bullet/Clip类卸下");
						inf.executeAction(actionReloadBulletOut);
						break;
					case 3://Magazine类型，弹匣
						System.out.println("Magazine类卸下");
						inf.executeAction(actionReloadMagezineOut);
						break;
					}
				}
			}
			break;
		case 3://F
			System.out.println("F键按下");
			//上膛部分，根据枪械当前AmmoID完成不同动作
			//若膛中无弹，直接执行上膛动作
			if(WeaponHelperE.getChamber(stack) == 0){
				System.out.println("进入上膛动作");
				inf.executeAction(actionChamberingIn);
			}
			else{
				System.out.println("进入退膛动作");
				inf.executeAction(actionChamberingOut);
			}
			//若膛中有弹，执行排出动作
			break;
		default:
			break;
		}
	}
	
	@Override
	public void onItemRelease(World world, EntityPlayer pl, ItemStack stack,
			int keyid) {
		InfWeapon inf = loadInformation(pl);
		switch(keyid) {
		case 0: //LMOUSE
			if(actionShootAuto != null)
				inf.removeAction(actionShootAuto.name);
			if(actionJam != null)
				inf.removeAction(actionJam.name);
			break;
		case 2:
			if(actionReloadBulletIn != null){
				if(inf.getTickLeft(actionReloadBulletIn) > 0)
					inf.executeAction(((ActionReloadEBulletIn)actionReloadBulletIn).ender);
				inf.removeAction(actionReloadBulletIn.name);
			}
			inf.removeAction(((ActionReloadEBulletIn)actionReloadBulletIn).loader.name);
				/*if(!inf.isActionPresent(actionReloadBulletIn.name))
					inf.executeAction(((ActionReloadEBulletIn)actionReloadBulletIn).ender);*/
			if(actionReloadBulletOut != null)
				inf.removeAction(actionReloadBulletOut.name);
			break;
		case 3:
			if(actionChamberingOut != null)
				inf.removeAction(actionChamberingOut.name);
		default:
			break;
		}
	}
	
	@Override
	//重载判定是否可射击
	public boolean canShoot(EntityPlayer player, ItemStack is) {
		if(consumeInv)
			return WeaponHelperE.hasAmmo(this, player) || player.capabilities.isCreativeMode;
		return WeaponHelperE.getChamber(is) != 0 || player.capabilities.isCreativeMode;
	}
	
	//消耗枪支内部弹药，返回false时枪械无法击发
	//尝试加入"弹膛"概念
	@Override
	public boolean consumeAmmo(ItemStack stack, int damage) {

		//弹膛不为空，则完成射击
		if(WeaponHelperE.getChamber(stack) != 0)
		{
			System.out.println("弹膛放空");
			//弹膛放空
			WeaponHelperE.setChamber(stack, 0);
			//若弹仓内弹药量足够，则给弹膛装弹(setUnitID)，否则弹膛保持放空
			int orig = getAmmo(stack);
			if(orig >= damage) {
				//弹仓内弹药减少
				WeaponHelperE.setAmmo(stack, orig - damage);
				//设置弹膛内的Unit，首先返还AmmoID，然后从中读取AmmoItem的UnitID
				if(WeaponHelperE.getAmmoID(stack) != 0){
					int unit = ((ItemAmmo)stack.getItem().getItemById(WeaponHelperE.getAmmoID(stack))).getAmmoUnit();
					WeaponHelperE.setChamber(stack, unit);
					System.out.println("弹膛ID设为"+ unit);
				}			
			}
			//在没有弹药可上的情况下，如果枪械支持空仓挂机，则进行挂机
			else{
				WeaponHelperE.setBoltCatch(stack, this.boltCatch);
			}
			return true;
		}
		else//若弹膛为空是不会补充弹药的
			return false;
	}
	
	//枪械初始0弹药
	@Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs ct, List list)
    {
		ItemStack is = new ItemStack(item);
		setAmmo(is, 0);
    	list.add(is);
    }	
}
