package nolifem.ic2gears.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import cn.liutils.api.client.model.IItemModel;
import cn.weaponmod.api.client.model.IWeaponModel;


/**两倍大的标准BaseGun，需要scale0.5
 * @author Nolife_M
 */
public class ModelBaseGunDS extends ModelBase implements IItemModel,IWeaponModel
{
	ModelRenderer base01;
    ModelRenderer base02;
  
  public ModelBaseGunDS()
  {
	  textureWidth = 64;
	    textureHeight = 32;
	    
	      base01 = new ModelRenderer(this, 0, 0);
	      base01.addBox(0F, 0F, 0F, 32, 3, 4);
	      base01.setRotationPoint(3F, 0F, -1F);
	      base01.setTextureSize(64, 32);
	      base01.mirror = true;
	      setRotation(base01, 0F, 0F, 0F);
	      base02 = new ModelRenderer(this, 0, 0);
	      base02.addBox(0F, 0F, 0F, 3, 8, 1);
	      base02.setRotationPoint(0F, 0F, 0F);
	      base02.setTextureSize(64, 32);
	      base02.mirror = true;
	      setRotation(base02, 0F, 0F, 0F);
  }
  
  @Override
  public void render(ItemStack is, float f5, float f) {
	    base01.render(f5);
	    base02.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5){}

@Override
public void renderMain(ItemStack is, float f5, float f) {
    base01.render(f5);
    base02.render(f5);
}

@Override
public void renderAmmo(ItemStack is, float f5, float f) {
	// TODO Auto-generated method stub
	
}

@Override
public void renderSlide(ItemStack is, float f5, float f) {
	// TODO Auto-generated method stub
	
}

@Override
public void renderShell(ItemStack is, float f5, float f) {
	// TODO Auto-generated method stub
	
}

@Override
public void setRotationAngles(ItemStack is, double posX, double posY,
		double posZ, float f) {
	// TODO Auto-generated method stub
	
}

}
