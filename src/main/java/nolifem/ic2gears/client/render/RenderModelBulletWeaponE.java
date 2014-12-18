/** 
 * Copyright (c) Lambda Innovation Team, 2013
 * 版权许可：LambdaCraft 制作小组， 2013.
 * http://lambdacraft.cn/
 * 
 * The mod is open-source. It is distributed under the terms of the
 * Lambda Innovation Open Source License. It grants rights to read, modify, compile
 * or run the code. It does *NOT* grant the right to redistribute this software
 * or its modifications in any form, binary or source, except if expressively
 * granted by the copyright holder.
 *
 * 本Mod是完全开源的，你允许参考、使用、引用其中的任何代码段，但不允许将其用于商业用途，在引用的时候，必须注明原作者。
 */
package nolifem.ic2gears.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import nolifem.ic2gears.client.models.IWeaponModel;
import cn.liutils.api.client.model.IItemModel;
import cn.liutils.api.client.render.RenderModelItem;
import cn.weaponmod.api.weapon.WeaponGeneric;

/**
 * 基于RendererModelBulletWeaponE修改
 *
 * @author WeathFolD
 *
 *An Expansion by Nolife_M
 */
public class RenderModelBulletWeaponE extends RendererBulletWeaponBaseE {
	
	public RenderModelWeaponE mdlRenderer;
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		// TODO Auto-generated method stub
		switch (type) {
		case EQUIPPED:
		case EQUIPPED_FIRST_PERSON:
			return true;
		case ENTITY:
			return mdlRenderer.renderEntityItem;
		default:
			return false;
		}

	}
	
	@Override
	public void renderEntityItem(RenderBlocks a, EntityItem b) {
		mdlRenderer.renderEntityItem(a, b);
	}
	
	/**
	 * @param mdl
	 * @param texture
	 */
	//获得mdl
	public RenderModelBulletWeaponE(IWeaponModel mdl, WeaponGeneric type, ResourceLocation texture) {
		super(type);
		mdlRenderer = new RenderModelWeaponE(mdl, texture);
		setInvRotation(0F, 0F, 37.5F);
		//修正参数整合
		//Std : x向左,y向上,z向后，rotate用于修正武器渲染矩阵为使用者习惯的朝向
		setStdRotation(0F, -90F, 0F);
		setOffset(0F, 0F, 0.5F);
		setScale(0.75F);
	}
	
	public RenderModelBulletWeaponE setInformationFrom(RenderModelBulletWeaponE a) {
		mdlRenderer.setInformationFrom(a.mdlRenderer);
		return this;
	}
	
	public RenderModelBulletWeaponE setRenderInventory(boolean b) {
		mdlRenderer.setRenderInventory(b);
		return this;
	}
	
	public RenderModelBulletWeaponE setRenderEntityItem(boolean b) {
		mdlRenderer.setRenderEntityItem(b);
		return this;
	}
	
	public RenderModelBulletWeaponE setInventorySpin(boolean b) {
		mdlRenderer.setInventorySpin(b);
		return this;
	}
	
	public RenderModelBulletWeaponE setStdRotation(float x, float y, float z) {
		mdlRenderer.setStdRotation(x, y, z);
		return this;
	}
	
	public RenderModelBulletWeaponE setEquipRotation(float x, float y, float z) {
		mdlRenderer.setEquipRotation(x, y, z);
		return this;
	}
	
	public RenderModelBulletWeaponE setFirstPersonRotaion(float x, float y, float z) {
		mdlRenderer.setFirstPersonRotaion(x, y, z);
		return this;
	}
	
	public RenderModelBulletWeaponE setInvRotation(float x, float y, float z) {
		mdlRenderer.setInvRotation(x, y, z);
		return this;
	}
	
	public RenderModelBulletWeaponE setEntityItemRotation(float b0, float b1, float b2) {
		mdlRenderer.setEntityItemRotation(b0, b1, b2);
		return this;
	}
	
	public RenderModelBulletWeaponE setScale(double s) {
		mdlRenderer.setScale(s);
		return this;
	}
	
	public RenderModelBulletWeaponE setInvScale(double s) {
		mdlRenderer.setInvScale(s);
		return this;
	}
	
	public RenderModelBulletWeaponE setFirstPersonScale(double s) {
		mdlRenderer.setFirstPersonScale(s);
		return this;
	}
	
	public RenderModelBulletWeaponE setOffset(float offsetX, float offsetY, float offsetZ) {
		mdlRenderer.setOffset(offsetX, offsetY, offsetZ);
		return this;
	}
	
	public RenderModelBulletWeaponE setInvOffset(float offsetX, float offsetY) {
		mdlRenderer.setInvOffset(offsetX, offsetY);
		return this;
	}
	
	public RenderModelBulletWeaponE setEquipOffset(double b0, double b1, double b2) {
		mdlRenderer.setEquipOffset(b0, b1, b2);
		return this;
	}
	
	public RenderModelBulletWeaponE setFirstPersonOffset(double b0, double b1, double b2) {
		mdlRenderer.setFirstPersonOffset(b0, b1, b2);
		return this;
	}

	@Override
	protected void renderWeapon(ItemStack stack, EntityPlayer pl,
			ItemRenderType type) {
		mdlRenderer.renderEquipped(stack, null, pl, type);
	}
	
	//================Preset 预制参数===============
	public RenderModelBulletWeaponE setDefaultLeftHand(){		
		mdlRenderer.setLeftHandOffset(0.3,  -0.05, 0.6);
		mdlRenderer.setLeftHandRotation(-25, 0, -110);
		return this;
	}

	public RenderModelBulletWeaponE setRifleRightHand(){		
		mdlRenderer.setRightHandOffset(-0.9,  -0.4, 0);
		mdlRenderer.setRightHandRotation(0, 0, -90);
		return this;
	}

	public RenderModelBulletWeaponE setPistolRightHand(){		
		mdlRenderer.setRightHandOffset(0.3,  -0.05, 0.6);
		mdlRenderer.setRightHandRotation(-25, 0, -110);
		return this;
	}
	
	/*GL11.glTranslated(-0.9, -0.4, 0);
	GL11.glRotated(-90,0 , 0, 1.0D);
	GL11.glRotated(0, 1.0D , 0 , 0);*/
}
