package cn.weaponmod.api.weapon.expand;

import net.minecraft.item.Item;

/**==============
 * 弹药类，封装一些方法
 * 用于实现枪支装填的弹夹部分
 * @author Nolife_M
 *
 */
public  class ItemAmmo extends Item {

	public static int TYPE_BULLET = 1,
		TYPE_CLIP= 2,
		TYPE_MAGAZINE= 3;
	//弹药类型
	protected int ammoType = 1;
	//弹药单位（口径等
	protected int ammoUnit = 0;
	//此Item用于修正MC本身getIdFromItem的BUG
	protected Item ammoUnitItem;
	
	public ItemAmmo(String name) {
		super();
		this.setMaxDamage(0);
		this.setMaxStackSize(1);
	}
	
	public  ItemAmmo setAmmoType(int type){
		this.ammoType = type;
		return this;
	}
	
	public  int getAmmoType(){
		return this.ammoType;
	}
	
	protected  void setAmmoUnitID(int id){		
		this.ammoUnit = id;
	}
	
	public  void setAmmoUnitItem(Item item){		
		this.ammoUnitItem = item;
	}
	
	public  int getAmmoUnit(){
		if(this.ammoUnitItem != null)
			return Item.getIdFromItem(ammoUnitItem);
		return this.ammoUnit;
	}
	
	//获得本物品的MaxAmmo数
	public int getMaxAmmo(){
		return this.getMaxDamage() -2;
	}
}
