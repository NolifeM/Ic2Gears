package cn.weaponmod.api.action.expand.part;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cn.weaponmod.api.WeaponHelper;
import cn.weaponmod.api.action.Action;
import cn.weaponmod.api.action.ActionReload;
import cn.weaponmod.api.information.InfWeapon;
import cn.weaponmod.api.weapon.WeaponGeneric;
import cn.weaponmod.api.weapon.expand.ItemAmmo;
import cn.weaponmod.api.weapon.expand.WeaponGenericE;
import cn.weaponmod.api.weapon.expand.WeaponHelperE;

/**=============
 * 对ActionReload的扩展
 * 主要关注:
 *  单发子弹装填
 *  正确处理空膛装填
 * @author Nolife_M
 *
 */
public class ActionBulletSingleIn extends ActionReload{

	int startTicks = 10,singleTicks =5,finishTicks = 10;
	public ActionBulletSingleIn(int ticks, String sndFinish) {
		super(ticks, "", sndFinish);
		this.name = "singleIn";
	}

	@Override
	public boolean doesConcurrent(Action other) {
		return true;
	}
	
	@Override
	public boolean onActionBegin(World world, EntityPlayer player, InfWeapon inf) {
		return true;
	}
	
	protected boolean stackFine(ItemStack stack, EntityPlayer player) {
		if(stack.getItem() instanceof WeaponGenericE) {
			System.out.println("武器符合连装" + (player.capabilities.isCreativeMode || WeaponHelperE.getChamber(stack) != 0));
			return player.capabilities.isCreativeMode || WeaponHelperE.getChamber(stack) != 0;
		}
		return false;
	}
	
	@Override
	public boolean onActionTick(World world, EntityPlayer player, InfWeapon inf) {
		return true;
	}
	
	//改动
	@Override
	public boolean onActionEnd(World world, EntityPlayer player, InfWeapon inf) {
		System.out.println("开始Bullet弹药装填");
		ItemStack curItem = player.getCurrentEquippedItem();
		WeaponGenericE wpnType = (WeaponGenericE) curItem.getItem();
		ItemStack bullet = WeaponHelperE.tryGetAmmo(wpnType.getAmmoList(), player);
		System.out.println(bullet);
		if(inf.getTickLeft(this)>0 || bullet == null)
			return false;
		//空膛会进入这一项
		if(WeaponHelperE.getChamber(curItem) == 0 && wpnType.canDirectLoading()){
			boolean flag = true;
			for(int i = 1; i <= wpnType.loadingConsume; i++){
				if(!player.inventory.consumeInventoryItem(bullet.getItem())){
					System.out.println("上膛所需弹数不足，退还");
					flag = false;
					//ItemStack feedBack = new ItemStack(bullet.getItem());
					for(int j = 1; j <= i ; j++){
					if(!player.inventory.addItemStackToInventory(bullet))
						player.entityDropItem(bullet, 1.0F);
					}
				}
			}
			if(flag){
				System.out.println("弹药已直接上入膛中");
				WeaponHelperE.setChamber(curItem, wpnType.getIdFromItem(bullet.getItem()));
				player.playSound(soundFinish, 0.5F, 1.0F);
			}
			else{
				System.out.println("不符合弹药入膛条件");
				return false;
			}				
		}
		//这一项的控制使得没有枪内弹容的枪械都无法进入本项
		//每次装填一发
		else if((WeaponHelperE.getAmmo(curItem) < wpnType.getMaxDamage())){
			if(!player.inventory.consumeInventoryItem(bullet.getItem()))
				return false;
			WeaponHelperE.setAmmo(curItem, WeaponHelperE.getAmmo(curItem) + 1);
			System.out.println("膛内弹数设为" + WeaponHelperE.getAmmo(curItem) + 1);
			WeaponHelperE.setAmmoID(curItem, bullet.getItem().getIdFromItem(bullet.getItem()));
			player.playSound(soundFinish, 0.5F, 1.0F);
		}
		return true;
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
