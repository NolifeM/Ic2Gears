package nolifem.ic2gears.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import cn.liutils.api.client.model.IItemModel;

public class ModelPistolEMK1 extends ModelBase implements IItemModel,IWeaponModel
{
  //fields
    ModelRenderer Grip01;
    ModelRenderer Grip02;
    ModelRenderer Grip03;
    ModelRenderer Grip04;
    ModelRenderer Grip05;
    ModelRenderer Grip06;
    ModelRenderer Grip07;
    ModelRenderer SlideL;
    ModelRenderer SlideR;
    ModelRenderer Main01;
    ModelRenderer Main02;
    ModelRenderer MainCore1;
    ModelRenderer MainCore2;
    ModelRenderer MainCore3;
    ModelRenderer MainBarrel;
    ModelRenderer MainT1;
    ModelRenderer MainT2;
    ModelRenderer MainB1;
    ModelRenderer MainB2;
    ModelRenderer Battery1;
    ModelRenderer Battery2;
  
  public ModelPistolEMK1()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Grip01 = new ModelRenderer(this, 8, 8);
      Grip01.addBox(0F, 0F, 0F, 1, 4, 2);
      Grip01.setRotationPoint(2F, 2F, 0F);
      Grip01.setTextureSize(64, 32);
      Grip01.mirror = true;
      setRotation(Grip01, 0F, 0F, 0F);
      Grip02 = new ModelRenderer(this, 0, 8);
      Grip02.addBox(0F, 0F, 0F, 2, 6, 2);
      Grip02.setRotationPoint(0F, 2F, 0.03333334F);
      Grip02.setTextureSize(64, 32);
      Grip02.mirror = true;
      setRotation(Grip02, 0F, 0F, 0F);
      Grip03 = new ModelRenderer(this, 8, 14);
      Grip03.addBox(0F, 0F, 0F, 1, 4, 2);
      Grip03.setRotationPoint(-1F, 4F, 0.03333334F);
      Grip03.setTextureSize(64, 32);
      Grip03.mirror = true;
      setRotation(Grip03, 0F, 0F, 0F);
      Grip04 = new ModelRenderer(this, 0, 17);
      Grip04.addBox(0F, 0F, 0F, 1, 1, 1);
      Grip04.setRotationPoint(3F, 2F, 0.5F);
      Grip04.setTextureSize(64, 32);
      Grip04.mirror = true;
      setRotation(Grip04, 0F, 0F, 0F);
      Grip05 = new ModelRenderer(this, 0, 19);
      Grip05.addBox(0F, 0F, 0F, 3, 1, 1);
      Grip05.setRotationPoint(3F, 3F, 0.5F);
      Grip05.setTextureSize(64, 32);
      Grip05.mirror = true;
      setRotation(Grip05, 0F, 0F, 0F);
      Grip06 = new ModelRenderer(this, 4, 17);
      Grip06.addBox(2F, 0F, 0F, 1, 1, 1);
      Grip06.setRotationPoint(3F, 2F, 0.5F);
      Grip06.setTextureSize(64, 32);
      Grip06.mirror = true;
      setRotation(Grip06, 0F, 0F, 0F);
      Grip07 = new ModelRenderer(this, 22, 0);
      Grip07.addBox(0F, 0F, 0F, 8, 1, 2);
      Grip07.setRotationPoint(-1F, 8F, 0F);
      Grip07.setTextureSize(64, 32);
      Grip07.mirror = true;
      setRotation(Grip07, 0F, 0F, 0F);
      SlideL = new ModelRenderer(this, 22, 0);
      SlideL.addBox(0F, 0F, 0F, 7, 1, 1);
      SlideL.setRotationPoint(7.5F, -0.5F, 1.7F);
      SlideL.setTextureSize(64, 32);
      SlideL.mirror = true;
      setRotation(SlideL, 0F, 0F, 0F);
      SlideR = new ModelRenderer(this, 22, 0);
      SlideR.addBox(0F, 0F, 0F, 7, 1, 1);
      SlideR.setRotationPoint(7.5F, -0.5F, -0.7F);
      SlideR.setTextureSize(64, 32);
      SlideR.mirror = true;
      setRotation(SlideR, 0F, 0F, 0F);
      Main01 = new ModelRenderer(this, 0, 0);
      Main01.addBox(0F, 0F, 0F, 8, 2, 3);
      Main01.setRotationPoint(0F, 0F, -0.5F);
      Main01.setTextureSize(64, 32);
      Main01.mirror = true;
      setRotation(Main01, 0F, 0F, 0F);
      Main02 = new ModelRenderer(this, 0, 5);
      Main02.addBox(0F, 0F, 0F, 6, 1, 2);
      Main02.setRotationPoint(1F, -1F, 0F);
      Main02.setTextureSize(64, 32);
      Main02.mirror = true;
      setRotation(Main02, 0F, 0F, 0F);
      MainCore1 = new ModelRenderer(this, 24, 8);
      MainCore1.addBox(0F, 0F, 0F, 5, 4, 2);
      MainCore1.setRotationPoint(9F, 0F, 0F);
      MainCore1.setTextureSize(64, 32);
      MainCore1.mirror = true;
      setRotation(MainCore1, 0F, 0F, 0F);
      MainCore2 = new ModelRenderer(this, 14, 8);
      MainCore2.addBox(0F, 0F, 0F, 3, 6, 2);
      MainCore2.setRotationPoint(6F, 0F, 0F);
      MainCore2.setTextureSize(64, 32);
      MainCore2.mirror = true;
      setRotation(MainCore2, 0F, 0F, 0F);
      MainCore3 = new ModelRenderer(this, 14, 8);
      MainCore3.addBox(0F, 0F, 0F, 2, 2, 2);
      MainCore3.setRotationPoint(6F, 6F, 0F);
      MainCore3.setTextureSize(64, 32);
      MainCore3.mirror = true;
      setRotation(MainCore3, 0F, 0F, 0F);
      MainBarrel = new ModelRenderer(this, 22, 0);
      MainBarrel.addBox(0F, 0F, 0F, 2, 1, 3);
      MainBarrel.setRotationPoint(13F, 2.5F, -0.5F);
      MainBarrel.setTextureSize(64, 32);
      MainBarrel.mirror = true;
      setRotation(MainBarrel, 0F, 0F, 0F);
      MainT1 = new ModelRenderer(this, 22, 0);
      MainT1.addBox(0F, 0F, 0F, 8, 1, 2);
      MainT1.setRotationPoint(7F, -1F, 0F);
      MainT1.setTextureSize(64, 32);
      MainT1.mirror = true;
      setRotation(MainT1, 0F, 0F, 0F);
      MainT2 = new ModelRenderer(this, 22, 0);
      MainT2.addBox(0F, 0F, 0F, 8, 1, 1);
      MainT2.setRotationPoint(6F, -1.5F, 0.5F);
      MainT2.setTextureSize(64, 32);
      MainT2.mirror = true;
      setRotation(MainT2, 0F, 0F, 0F);
      MainB1 = new ModelRenderer(this, 22, 0);
      MainB1.addBox(0F, 0F, 0F, 6, 1, 3);
      MainB1.setRotationPoint(8.5F, 3.2F, -0.5F);
      MainB1.setTextureSize(64, 32);
      MainB1.mirror = true;
      setRotation(MainB1, 0F, 0F, 0F);
      MainB2 = new ModelRenderer(this, 22, 0);
      MainB2.addBox(0F, 0F, 0F, 5, 1, 3);
      MainB2.setRotationPoint(8.5F, 4.2F, -0.5F);
      MainB2.setTextureSize(64, 32);
      MainB2.mirror = true;
      setRotation(MainB2, 0F, 0F, 0F);
      Battery1 = new ModelRenderer(this, 14, 8);
      Battery1.addBox(0F, 0F, 0F, 3, 3, 1);
      Battery1.setRotationPoint(6F, 2F, 2F);
      Battery1.setTextureSize(64, 32);
      Battery1.mirror = true;
      setRotation(Battery1, 0F, 0F, 0F);
      Battery2 = new ModelRenderer(this, 14, 16);
      Battery2.addBox(0F, 0F, 0F, 2, 2, 2);
      Battery2.setRotationPoint(6.5F, 2.5F, 1.5F);
      Battery2.setTextureSize(64, 32);
      Battery2.mirror = true;
      setRotation(Battery2, 0F, 0F, 0F);
  }
  
  public void render(ItemStack is, float f5, float f)
  {
    Grip01.render(f5);
    Grip02.render(f5);
    Grip03.render(f5);
    Grip04.render(f5);
    Grip05.render(f5);
    Grip06.render(f5);
    Grip07.render(f5);
    SlideL.render(f5);
    SlideR.render(f5);
    Main01.render(f5);
    Main02.render(f5);
    MainCore1.render(f5);
    MainCore2.render(f5);
    MainCore3.render(f5);
    MainBarrel.render(f5);
    MainT1.render(f5);
    MainT2.render(f5);
    MainB1.render(f5);
    MainB2.render(f5);
    Battery1.render(f5);
    Battery2.render(f5);
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
	Grip01.render(f5);
    Grip02.render(f5);
    Grip03.render(f5);
    Grip04.render(f5);
    Grip05.render(f5);
    Grip06.render(f5);
    Grip07.render(f5);
    Main01.render(f5);
    Main02.render(f5);
    MainCore1.render(f5);
    MainCore2.render(f5);
    MainCore3.render(f5);
    MainBarrel.render(f5);
    MainT1.render(f5);
    MainT2.render(f5);
    MainB1.render(f5);
    MainB2.render(f5);
}

@Override
public void renderAmmo(ItemStack is, float f5, float f) {
    Battery1.render(f5);
    Battery2.render(f5);
}

@Override
public void renderSlide(ItemStack is, float f5, float f) {
    SlideL.render(f5);
    SlideR.render(f5);
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
