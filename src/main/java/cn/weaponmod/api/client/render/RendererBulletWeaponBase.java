package cn.weaponmod.api.client.render;

import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.IItemRenderer;
import cn.weaponmod.api.action.Action;
import cn.weaponmod.api.information.InfWeapon;
import cn.weaponmod.api.weapon.WeaponGenericBase;
import cn.weaponmod.api.weapon.WeaponGeneric;
import cn.weaponmod.core.proxy.WMClientProxy;

/**
 * 基于RenderBulletWeaponBase修改，尝试实现Action对renderer的软性控制
 * 已经合并，主要改动：
 * 去掉Uplift和Recoil渲染，全部交由ActionPack完成
 * 去掉对applyRenderEffect的调用
 * 
 *此类提供了基本的底层方法和渲染方式
 *2D渲染实现交由RenderBulletWeaoponE中的renderWeapon实现
 *3D模型渲染交由RenderModelBulletWeapon中的renderWeapon实现
 * 
 * @author WeathFolD
 *
 *An Expansion by Nolife_M
 */
public abstract class RendererBulletWeaponBase implements IItemRenderer {
	
	protected WeaponGenericBase weaponType;
	protected static Random RNG = new Random();
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		// TODO Auto-generated method stub
		switch (type) {
		case EQUIPPED:
		case EQUIPPED_FIRST_PERSON:
		case ENTITY:
			return true;
		default:
			return false;
		}

	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		switch (helper) {
		case ENTITY_ROTATION:
		case ENTITY_BOBBING:
			return true;

		default:
			return false;

		}
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch (type) {
		case EQUIPPED:
		case EQUIPPED_FIRST_PERSON:
			renderEquipped(item, (RenderBlocks) data[0],
					(EntityLivingBase) data[1], type);
			break;
		case ENTITY:
			renderEntityItem((RenderBlocks)data[0], (EntityItem)data[1]);
			break;
		default:
			break;

		}

	}
	
	public void renderEntityItem(RenderBlocks renderer, EntityItem ent) {
	}
	
	/**
	 * @param mdl
	 * @param texture
	 */
	public RendererBulletWeaponBase(WeaponGeneric type) {
		weaponType = type;
	}

	//修改部分
	public void renderEquipped(ItemStack stack, RenderBlocks render,
			EntityLivingBase entity, ItemRenderType type) {
		
		if(!(entity instanceof EntityPlayer)) {
			renderWeapon(stack, null, type);
			return;
		}
		
		EntityPlayer player = (EntityPlayer) entity;
		InfWeapon inf = weaponType.loadInformation(stack, player);
		
		boolean firstPerson = 
				entity == Minecraft.getMinecraft().thePlayer 
				&& Minecraft.getMinecraft().gameSettings.thirdPersonView == 0;
		
		GL11.glPushMatrix(); {
			renderWeapon(stack, player, type);
		} GL11.glPopMatrix();
	}
	
	private Vec3 initVec() {
		return Vec3.createVectorHelper(0D, 0D, 0D);
	}
	
	protected void initVec(Vec3 vec, double x, double y, double z) {
		if(vec == null) {
			vec = Vec3.createVectorHelper(x, y, z);
			return;
		}
		vec.xCoord = x;
		vec.yCoord = y;
		vec.zCoord = z;
	}
	
	protected boolean doesApplyRender(Action a) {
		return true;
	}
	
	protected abstract void renderWeapon(ItemStack stack, EntityPlayer pl, ItemRenderType type);

}
