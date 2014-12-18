package cn.liutils.api.client.model;

import net.minecraft.item.ItemStack;

public interface IItemModel {

	public void render(ItemStack is, float f5, float f);
	public void setRotationAngles(ItemStack is, double posX, double posY, double posZ, float f);
	
}

