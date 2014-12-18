package nolifem.ic2gears.client.models;

import net.minecraft.item.ItemStack;

/**武器专用模型接口，提供分块渲染
 * 
 * @author Nolife_M
 *
 */
public interface IWeaponModel {

	public void render(ItemStack is, float f5, float f);
	
	public void renderMain(ItemStack is, float f5, float f);
	public void renderAmmo(ItemStack is, float f5, float f);
	public void renderSlide(ItemStack is, float f5, float f);
	public void renderShell(ItemStack is, float f5, float f);
	
	public void setRotationAngles(ItemStack is, double posX, double posY, double posZ, float f);
	
}

