package cn.weaponmod.api.weapon.expand;


import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cn.liutils.api.util.GenericUtils;
import cn.weaponmod.api.WeaponHelper;
import cn.weaponmod.api.weapon.WeaponGenericBase;

/**============
 * 对WeaponHelper的扩展和重载
 * 封装大部分对武器的管理方法
 * @author Nolife_M
 *
 */
public class WeaponHelperE extends WeaponHelper{
		
	//尝试消耗弹药部分，可用于直接消耗Inv，或弹夹装填中的直接装填
	public static int consumeAmmo(EntityPlayer player, WeaponGenericBase item, int amount) {
		return tryConsume(player, item.ammoItem, amount);
	}
	/**
	 * Tries to consume one specific item in player's inventory.
	 * 
	 * @return how many are left to be consumed
	 */
	public static int tryConsume(EntityPlayer player, Item item, int amount) {

		int left = amount;
		ItemStack is;
		//单发弹药情况
		if (item.getItemStackLimit() > 1) {

			for (int i = 0; i < player.inventory.mainInventory.length; i++) {
				is = player.inventory.mainInventory[i];
				if (is != null && is.getItem() == item) {
					if (is.stackSize > left) {
						player.inventory.decrStackSize(i, left);
						return 0;
					} else {
						left -= is.stackSize;
						player.inventory.decrStackSize(i, is.stackSize);
					}
				}
			}

			return left;

		//弹夹情况
		} else {

			for (int i = 0; i < player.inventory.mainInventory.length; i++) {
				is = player.inventory.mainInventory[i];
				int stackCap;
				if (is != null && is.getItem() == item) {
					stackCap = is.getMaxDamage() - is.getItemDamage() - 1;
					if (stackCap > left) {
						is.damageItem(left, player);
						//尝试消耗电量
						tryDisCharge(is);
						return 0;
					} else {
						left -= stackCap;
						is.setItemDamage(is.getMaxDamage() - 1);
						//尝试消耗电量
						tryDisCharge(is);					
					}
				}
			}
			return left;

		}
	}

	/**
	 * 已经变成单纯返还一个ammo的具体残弹量的方法，能（略微）减少纠结度
	 * @param ammo
	 * @return
	 */
	public static int getAmmoCapacity(ItemStack ammo) {
		return ((ItemAmmo)ammo.getItem()).getMaxAmmo() +1  - ammo.getItemDamage();
	}
	
	//============弹膛管理=============
	//管理弹膛内弹药。无论是哪种枪械，默认弹膛都只有一发
		public static int getChamber(ItemStack stack) {
			NBTTagCompound nbt = GenericUtils.loadCompound(stack);
			return nbt.getInteger("chamber");
		}
		public static void setChamber(ItemStack stack, int chamber) {
			GenericUtils.loadCompound(stack).setInteger("chamber", chamber);
			//只要不是排空chamber，解除空仓挂机
			if(chamber != 0)
				setBoltCatch(stack,false);
		}
	//============即时弹容管理===========
	//和WeaponGeneric的方法不同，全部单独提出管理，并且根据Clip需要进行改写
	public static int getAmmo(ItemStack stack) {
		NBTTagCompound nbt = GenericUtils.loadCompound(stack);
		return nbt.getInteger("ammo");
	}

	public static void setAmmo(ItemStack stack, int ammo) {
		GenericUtils.loadCompound(stack).setInteger("ammo", ammo < 0 ? 0 :  ammo);
	}
	
	@Deprecated
	/**
	 * 和内含函数一起废弃
	 * @param is
	 * @param player
	 * @return
	 */
	//===========检测弹药部分重写载===========
	public static boolean hasAmmoE(Item is, EntityPlayer player) {
		//不为空返回真
		return tryGetAmmo((WeaponGenericBase) is, player) != null;
	}
	
	
	@Deprecated
	/**==========
	 * 尝试获得弹药，已经尝试使用新方法置换
	 * @return 获得的弹药stack或空值
	 */
	public static ItemStack tryGetAmmo(WeaponGenericBase is, EntityPlayer player){
		for (ItemStack i : player.inventory.mainInventory) {
			if (i == null)
				continue;
			if (i.getItem() == is.ammoItem) {
				if (i.isStackable())
					return i;
				else if (i.getItemDamage() < i.getMaxDamage() - 1)
					return i;
			}
		}
		return null;
	}
	
	//============电力消耗部分==================
	//进入这里的is为背包中的弹夹
	//如果为能量弹夹，才进行处理
	private static void tryDisCharge(ItemStack is) {
		if(is.getItem() instanceof ItemElecAmmo){
		double leftE  =(is.getItem().getMaxDamage() - 1 - is.getItemDamage()) 
				* ((ItemElecAmmo)is.getItem()).getMaxCharge(is)/
				(is.getItem().getMaxDamage() -2 );
		setCharge(is,leftE);
		System.out.println("消耗电力");
		System.out.println("剩余" +getCharge(is));
		}
	}

	public final static double getCharge(ItemStack stack) {
		NBTTagCompound nbt = GenericUtils.loadCompound(stack);
		return nbt.getDouble("charge");
	}
	
	public final static void setCharge(ItemStack stack, double charge) {
		GenericUtils.loadCompound(stack).setDouble("charge", charge < 0 ? 0 : 
			charge);
	}
	//========即时最大弹夹容量管理========
	//现在已经直接读取weapon的AmmoID进行管理
	/*@Deprecated
	public final static int getMaxAmmo(ItemStack stack) {
		NBTTagCompound nbt = GenericUtils.loadCompound(stack);
		return nbt.getInteger("MaxAmmo");
	}
	@Deprecated
	public final static void setMaxAmmo(ItemStack stack, int maxAmmo) {
		GenericUtils.loadCompound(stack).setInteger("MaxAmmo", maxAmmo < 0 ? 0 : 
			maxAmmo);
	}*/

	//=========将弹夹ID信息读取、写入NBT========
	public final static int getAmmoID(ItemStack stack) {
		NBTTagCompound nbt = GenericUtils.loadCompound(stack);
		return nbt.getInteger("CurAmmo");
	}
	
	public final static void setAmmoID(ItemStack stack, int itemid) {
		GenericUtils.loadCompound(stack).setInteger("CurAmmo", itemid);
	}
	//=========弹夹列表对比和获取=========
	//搜索玩家背包，若找到合适itemstack，返回合适的itemstack
	//查找顺序：所有支持的非空弹夹的第一个
	//——若无，所有支持的空弹夹的第一个
	//优先返还非空弹夹
	public static ItemStack tryGetAmmo(ArrayList list, EntityPlayer player) {
		ItemStack result = null;
		for(int i = 0; i < list.size();i++){	
			Item target = (Item) list.get(i);
			//找出所有合适的弹夹
			for (int j = 0; j < player.inventory.mainInventory.length; j++) {
				//记录符合的弹夹并赋值给result
				if (player.inventory.mainInventory[j] != null && player.inventory.mainInventory[j].getItem() == target){			
					result = player.inventory.mainInventory[j] ;
					//如果在这里面发现了非空弹夹，立即return
					if (result.getItemDamage() != result.getMaxDamage() - 1){
						System.out.println("返回非空弹夹");
						return result;
					}
					//如果没有非空弹夹，则继续遍历
				}		
			}
		}
		System.out.println("Ammo背包检测完成，返回"+ result);
		//遍历完毕若仍没有非空弹夹，则返回最后一个符合的弹夹，或null
		return result;
	}
	
	//==========消耗弹夹===========
	//进入本方法的Item是指定的，因而在本方法内只需要优先消耗非空弹夹即可
	public static boolean consumClip(EntityPlayer player, Item item){
		ItemStack is;
		for (int i= 0; i < player.inventory.mainInventory.length; i++) {
			is = player.inventory.mainInventory[i];
			if (is != null && is.getItem() == item && is.getItemDamage() != is.getMaxDamage() - 1) {
				//清理该弹夹
				//System.out.println("返回非空弹夹");
				player.inventory.mainInventory[i] = null;
				return true;	
			}
			else if (is != null && is.getItem() == item ) {
				//清理该弹夹
				//System.out.println("返回任意弹夹");
				player.inventory.mainInventory[i] = null;
				return true;
			}
		}
		return false;
	}
	//==============空仓挂机控制============
	public final static boolean getBoltCatch(ItemStack stack) {
		NBTTagCompound nbt = GenericUtils.loadCompound(stack);
		return nbt.getBoolean("BoltCatch");
	}
	
	public final static void setBoltCatch(ItemStack stack, boolean boltCatch) {
		GenericUtils.loadCompound(stack).setBoolean("BoltCatch", boltCatch);
	}
	
	/*//===========依ItemStack获得Renderer==========
	public final static RendererModelBulletWeaponE  getWeaponRenderer(ItemStack stack){			
		return (RendererModelBulletWeaponE) customItemRenderers.get(stack.getItem());
	}*/
}
