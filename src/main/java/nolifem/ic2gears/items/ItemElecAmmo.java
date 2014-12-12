package nolifem.ic2gears.items;

import ic2.api.item.IElectricItem;
import ic2.api.item.IElectricItemManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cn.liutils.api.util.GenericUtils;

/**==========
 * 电力弹夹类（还是叫能量弹夹好听
 * 能量弹夹的总类，在这里处理弹夹类型等等
 * 实现Ic2的接口，来达到充电目的
 * @author Nolife_M
 *
 */
public class ItemElecAmmo extends ItemAmmo implements IElectricItem,IElectricItemManager{
	
	//弹药上限由MaxDamgae决定
	//最大充电数
	protected int maxCharge;
	//单发子弹充电数
	//能量等级
	protected int tier;
	//充能速度
	protected int speed;
	
	public ItemElecAmmo(String name,int maxCharge,int tier,int speed) {
		super(name);
		this.maxCharge = maxCharge;
		this.tier = tier;
		this.speed = speed;
		//this.setAmmoType(ItemAmmo.TYPE_MAGAZINE);
		//设置Unit为1(不返还型)
		this.setAmmoUnitID(1);
	}
		
	@Override
	public void onUpdate(ItemStack stack, World world,
			Entity entity, int par4, boolean par5) {
		//System.out.println(GenericUtils.loadCompound(stack));
	}
	
	//获取单发子弹的充能数
	public int getSingleCharge(){
		return this.maxCharge/(this.getMaxDamage()-2);
	}
	
	//无法提供能量
	@Override
	public boolean canProvideEnergy(ItemStack itemStack) {
		return false;
	}

	//用于充电的Item
	@Override
	public Item getChargedItem(ItemStack itemStack) {
		return this;
	}

	//空电量的Item
	@Override
	public Item getEmptyItem(ItemStack itemStack) {
		return this;
	}

	/*
	 * 上面两个的区别在工业2貌似没体现
	 * 暂时不管
	 */
	
	//最大充电量
	@Override
	public double getMaxCharge(ItemStack itemStack) {
        // TODO Auto-generated method stub
		return this.maxCharge;
	}

	//能量等级，决定了你能在什么样的储电器中充电
	@Override
	public int getTier(ItemStack itemStack) {
		// TODO Auto-generated method stub
		return this.tier;
	}

	//能量交换限制，大概是充电速度
	@Override
	public double getTransferLimit(ItemStack itemStack) {
		// TODO Auto-generated method stub
		return this.speed;
	}

	@Override
	public double charge(ItemStack stack, double amount, int tier,
			boolean ignoreTransferLimit, boolean simulate) {
		// TODO Auto-generated method stub
		return amount;
	}

	@Override
	public double discharge(ItemStack stack, double amount, int tier,
			boolean ignoreTransferLimit, boolean externally, boolean simulate) {
		// TODO Auto-generated method stub
		return amount;
	}

	@Override
	public double getCharge(ItemStack stack) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canUse(ItemStack stack, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean use(ItemStack stack, double amount, EntityLivingBase entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void chargeFromArmor(ItemStack stack, EntityLivingBase entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getToolTip(ItemStack stack) {
		// TODO Auto-generated method stub
		return null;
	}
}
