package nolifem.ic2gears.client.models;

import cn.liutils.api.client.model.IItemModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class ModelNPMark1 extends ModelBase implements IItemModel,IWeaponModel
{
  //fields
	int PART_CLIP = 1;
	int PART_BODY = 2;
    ModelRenderer body01;
    ModelRenderer body01L;
    ModelRenderer body01R;
    ModelRenderer body02;
    ModelRenderer body03;
    ModelRenderer body04L;
    ModelRenderer body04R;
    ModelRenderer grip01;
    ModelRenderer centure01;
    ModelRenderer centure;
    ModelRenderer battery01;
    ModelRenderer battery02;
    ModelRenderer battery03;
    ModelRenderer grip02;
    ModelRenderer grip03;
  
  public ModelNPMark1()
  {
    textureWidth = 24;
    textureHeight = 14;
    
      body01 = new ModelRenderer(this, 0, 0);
      body01.addBox(0F, 0F, 0F, 6, 1, 1);
      body01.setRotationPoint(-1F, 0F, 0.5F);
      body01.setTextureSize(64, 32);
      body01.mirror = true;
      setRotation(body01, 0F, 0F, 0F);
      body01L = new ModelRenderer(this, 0, 0);
      body01L.addBox(0F, 0F, 0F, 6, 1, 1);
      body01L.setRotationPoint(-1F, 0F, 1.5F);
      body01L.setTextureSize(64, 32);
      body01L.mirror = true;
      setRotation(body01L, -1F, 0F, 0F);
      body01R = new ModelRenderer(this, 0, 0);
      body01R.addBox(0F, 0F, 0F, 6, 1, 1);
      body01R.setRotationPoint(-1F, 0F, 0.5F);
      body01R.setTextureSize(64, 32);
      body01R.mirror = true;
      setRotation(body01R, -0.6F, 0F, 0F);
      body02 = new ModelRenderer(this, 14, 0);
      body02.addBox(0F, 0F, 0F, 4, 2, 1);
      body02.setRotationPoint(-4F, 0.5F, 0.5F);
      body02.setTextureSize(64, 32);
      body02.mirror = true;
      setRotation(body02, 0F, 0F, 0F);
      body03 = new ModelRenderer(this, 0, 7);
      body03.addBox(0F, 0F, 0F, 3, 1, 2);
      body03.setRotationPoint(-1.5F, 1F, 0F);
      body03.setTextureSize(64, 32);
      body03.mirror = true;
      setRotation(body03, 0F, 0F, 0F);
      body04L = new ModelRenderer(this, 0, 5);
      body04L.addBox(0F, 0F, 0F, 4, 1, 1);
      body04L.setRotationPoint(0.5F, 3.5F, 1F);
      body04L.setTextureSize(64, 32);
      body04L.mirror = true;
      setRotation(body04L, 0.5F, 0F, -0.2F);
      body04R = new ModelRenderer(this, 0, 5);
      body04R.addBox(0F, 0F, 0F, 4, 1, 1);
      body04R.setRotationPoint(0.5F, 3F, 0F);
      body04R.setTextureSize(64, 32);
      body04R.mirror = true;
      setRotation(body04R, -0.56F, 0F, -0.2F);
      grip01 = new ModelRenderer(this, 0, 2);
      grip01.addBox(0F, 0F, 0F, 3, 2, 1);
      grip01.setRotationPoint(-5F, 5F, 0.5F);
      grip01.setTextureSize(64, 32);
      grip01.mirror = true;
      setRotation(grip01, 0F, 0F, -1.35F);
      centure01 = new ModelRenderer(this, 18, 4);
      centure01.addBox(0F, 0F, 0F, 2, 3, 1);
      centure01.setRotationPoint(-1F, 2F, 0.5F);
      centure01.setTextureSize(64, 32);
      centure01.mirror = true;
      setRotation(centure01, 0F, 0F, 0F);
      centure = new ModelRenderer(this, 0, 10);
      centure.addBox(0F, 0F, 0F, 3, 2, 1);
      centure.setRotationPoint(1F, 1F, 0.5F);
      centure.setTextureSize(64, 32);
      centure.mirror = true;
      setRotation(centure, 0F, 0F, 0F);
      battery01 = new ModelRenderer(this, 10, 5);
      battery01.addBox(0F, 0F, 0F, 2, 2, 1);
      battery01.setRotationPoint(0F, 1F, 1.2F);
      battery01.setTextureSize(64, 32);
      battery01.mirror = true;
      setRotation(battery01, 0F, 0F, 0.8F);
      battery02 = new ModelRenderer(this, 11, 3);
      battery02.addBox(0F, 0F, 0F, 1, 1, 1);
      battery02.setRotationPoint(0F, 1.7F, 1.6F);
      battery02.setTextureSize(64, 32);
      battery02.mirror = true;
      setRotation(battery02, 0F, 0F, 0.8F);
      battery03 = new ModelRenderer(this, 10, 5);
      battery03.addBox(0F, 0F, 0F, 2, 1, 1);
      battery03.setRotationPoint(-1F, 2.8F, 1.2F);
      battery03.setTextureSize(64, 32);
      battery03.mirror = true;
      setRotation(battery03, 0F, 0F, -0.8F);
      grip02 = new ModelRenderer(this, 16, 3);
      grip02.addBox(0F, 0F, 0F, 3, 1, 1);
      grip02.setRotationPoint(-5F, 5F, 0.5F);
      grip02.setTextureSize(64, 32);
      grip02.mirror = true;
      setRotation(grip02, 0F, 0F, 0.2F);
      grip03 = new ModelRenderer(this, 10, 8);
      grip03.addBox(0F, 0F, 0F, 4, 1, 1);
      grip03.setRotationPoint(-3F, 5.5F, 0.5F);
      grip03.setTextureSize(64, 32);
      grip03.mirror = true;
      setRotation(grip03, 0F, 0F, -0.3F);
  }
  
  public void renderPart(ItemStack is,int part, float f5, float f) {
	  switch(part){
	  case 1:
	    body01.render(f5);
	    body01L.render(f5);
	    body01R.render(f5);
	    body02.render(f5);
	    body03.render(f5);
	    body04L.render(f5);
	    body04R.render(f5);
	    grip01.render(f5);
	    centure01.render(f5);
	    centure.render(f5);
	    grip02.render(f5);
	    grip03.render(f5);
	    break;
	  case 2:
	   
	    break;
	  }
}
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    body01.render(f5);
    body01L.render(f5);
    body01R.render(f5);
    body02.render(f5);
    body03.render(f5);
    body04L.render(f5);
    body04R.render(f5);
    grip01.render(f5);
    centure01.render(f5);
    centure.render(f5);
    battery01.render(f5);
    battery02.render(f5);
    battery02.render(f5);
    grip02.render(f5);
    grip03.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void render(ItemStack is, float f5, float f) {
	    body01.render(f5);
	    body01L.render(f5);
	    body01R.render(f5);
	    body02.render(f5);
	    body03.render(f5);
	    body04L.render(f5);
	    body04R.render(f5);
	    grip01.render(f5);
	    centure01.render(f5);
	    centure.render(f5);
	    battery01.render(f5);
	    battery02.render(f5);
	    battery02.render(f5);
	    grip02.render(f5);
	    grip03.render(f5);
  }
  
  public void setRotationAngles(ItemStack is, double posX, double posY,
			double posZ, float f) {}

@Override
public void renderMain(ItemStack is, float f5, float f) {
	// TODO Auto-generated method stub
	body01.render(f5);
    body01L.render(f5);
    body01R.render(f5);
    body02.render(f5);
    body03.render(f5);
    body04L.render(f5);
    body04R.render(f5);
    grip01.render(f5);
    centure01.render(f5);
    centure.render(f5);
    grip02.render(f5);
    grip03.render(f5);
}

@Override
public void renderAmmo(ItemStack is, float f5, float f) {
	// TODO Auto-generated method stub
	 	battery01.render(f5);
	    battery02.render(f5);
	    battery03.render(f5);
}

@Override
public void renderSlide(ItemStack is, float f5, float f) {
	// TODO Auto-generated method stub
	
}

@Override
public void renderShell(ItemStack is, float f5, float f) {
	// TODO Auto-generated method stub
	
}
}
