package nolifem.ic2gears.api.action;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import nolifem.ic2gears.api.action.packs.APBatteryOut;
import nolifem.ic2gears.api.action.packs.APButtomReloadIn;
import nolifem.ic2gears.api.action.packs.APButtomReloadOut;
import nolifem.ic2gears.api.action.packs.ActionPack;
import nolifem.ic2gears.api.weapons.WeaponGenericICG;
import nolifem.ic2gears.api.weapons.WeaponHelperE;
import nolifem.ic2gears.client.render.RenderModelBulletWeaponE;
import nolifem.ic2gears.client.render.RenderModelWeaponE;
import nolifem.ic2gears.items.ItemElecAmmo;
import nolifem.ic2gears.util.PhysicsUtil;
import cn.weaponmod.api.action.ActionReload;
import cn.weaponmod.api.information.InfWeapon;
import cn.weaponmod.api.weapon.WeaponGeneric;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**=============
 * 对ActionReload的扩展
 * 主要关注:
 * 取出当前枪械内的弹夹
 * 正确地返还电力枪械弹夹的电力
 * @author Nolife_M
 *
 */
public class ActionReloadEMagazineOut extends ActionReload{

	private RenderModelWeaponE renderer;
	private ActionPack actionPack;
	
	public ActionReloadEMagazineOut(int ticks, String snd) {
		super(ticks, snd, "");
		//this.name = "reloadMOut";
	}

	public ActionReloadEMagazineOut setActionPack(ActionPack ap){
		this.actionPack = ap;
		return this;
	}
	
	
	/**
	 * 步骤整理：读取枪械内弹夹并返还至player背包内
	 * ——重置当前枪械状况
	 * ——进入ActionEnd
	 */
	@Override
	public boolean onActionBegin(World world, EntityPlayer player, InfWeapon inf) {
		ItemStack curItem = player.getCurrentEquippedItem();
		WeaponGenericICG wpnType = (WeaponGenericICG) curItem.getItem();
		if(curItem == null || !(curItem.getItem() instanceof WeaponGenericICG || WeaponHelperE.getAmmoID(curItem) == 0))
			return false;
		this.renderer = this.getWeaponRenderer(curItem);
		this.applyRenderEffect(world, player, inf, true);
		player.playSound(sound, 0.5F, 1.0F);
			return true;
	}
	
	@Override
	public boolean onActionTick(World world, EntityPlayer player, InfWeapon inf) {
		return true;
	}
	
	@Override
	public boolean onActionEnd(World world, EntityPlayer player, InfWeapon inf) {
		//获取当前武器信息
		ItemStack curItem = player.getCurrentEquippedItem();
		WeaponGenericICG wpnType = (WeaponGenericICG) curItem.getItem();
		if(curItem == null || !(curItem.getItem() instanceof WeaponGeneric))
			return false;
		//一般如果为0，是不会进入本Action的，此处为保险
		//初始化武器弹夹ID		
		if(WeaponHelperE.getAmmoID(curItem) == 0)
		{
			WeaponHelperE.setAmmoID(curItem, wpnType.getIdFromItem(wpnType.ammoItem));
		}
		//获取武器内弹夹，并设置弹药数
		ItemStack origClip = new ItemStack(wpnType.getItemById(WeaponHelperE.getAmmoID(curItem)));
		origClip.setItemDamage(origClip.getMaxDamage() - 1 - WeaponHelperE.getAmmo(curItem));
		//如果为能量弹夹，还需要返还对应的充能数
		if(origClip.getItem() instanceof ItemElecAmmo){
			WeaponHelperE.setCharge(origClip, WeaponHelperE.getAmmo(curItem) * (((ItemElecAmmo)origClip.getItem()).getSingleCharge()));
			//System.out.println("电力弹夹返还电力:" + WeaponHelperE.getCharge(origClip));
			}
		//尝试添加，装不下就掉下来
		if(!player.inventory.addItemStackToInventory(origClip))
			player.entityDropItem(origClip, 1.0F);
		//System.out.println("返还弹夹信息:弹容数 "+ (origClip.getMaxDamage() - 1 - origClip.getItemDamage()));
		//清空弹夹ID和武器弹药数
		WeaponHelperE.setAmmoID(curItem, 0);
		WeaponHelperE.setAmmo(curItem, 0);
		//此时原旧弹夹返还完毕，可以写入新的弹夹弹数和ID
		//System.out.println("弹夹已拆卸");
		
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void applyRenderEffect(World world, EntityPlayer player, InfWeapon inf, boolean first) {
		//int te = this.maxTick -  inf.getTickLeft(this);
		ItemStack curItem = player.getCurrentEquippedItem();
		if(curItem != null && this.actionPack != null){
			renderer.actionPack = this.actionPack.setRenderer(renderer);
			renderer.actionPack.reset();	
		}
	}
	
	private RenderModelWeaponE getWeaponRenderer(ItemStack curItem){
		return  ((RenderModelBulletWeaponE)MinecraftForgeClient.getItemRenderer(curItem, IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)).mdlRenderer;
	}
}
