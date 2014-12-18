package nolifem.ic2gears.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import cn.liutils.api.client.model.IItemModel;

public class ModelM14EBR extends ModelBase implements IItemModel,IWeaponModel
{
  //fields
    ModelRenderer Barrel1;
    ModelRenderer Barrel2;
    ModelRenderer Barrel3;
    ModelRenderer Scope1;
    ModelRenderer Scope2L;
    ModelRenderer Scope2R;
    ModelRenderer Main1;
    ModelRenderer Main2;
    ModelRenderer Main3;
    ModelRenderer Main4;
    ModelRenderer Main5;
    ModelRenderer Main6;
    ModelRenderer Ammo;
    ModelRenderer Grip1;
    ModelRenderer Grip2;
    ModelRenderer Grip3;
    ModelRenderer Grip4;
    ModelRenderer Grip5;
    ModelRenderer Stock1R;
    ModelRenderer Stock1L;
    ModelRenderer Stock2;
    ModelRenderer Stock3;
    ModelRenderer Stock4;
    ModelRenderer Stock5;
    ModelRenderer Stock6;
  
  public ModelM14EBR()
  {
    textureWidth = 80;
    textureHeight = 21;
    
      Barrel1 = new ModelRenderer(this, 42, 0);
      Barrel1.addBox(0F, 0F, 0F, 12, 1, 1);
      Barrel1.setRotationPoint(30F, -3.3F, 0.5F);
      Barrel1.setTextureSize(80, 20);
      Barrel1.mirror = true;
      setRotation(Barrel1, 0F, 0F, 0F);
      Barrel2 = new ModelRenderer(this, 2, 0);
      Barrel2.addBox(0F, 0F, 0F, 1, 3, 2);
      Barrel2.setRotationPoint(33F, -3.5F, 0F);
      Barrel2.setTextureSize(80, 21);
      Barrel2.mirror = true;
      setRotation(Barrel2, 0F, 0F, 0F);
      Barrel3 = new ModelRenderer(this, 43,0 );
      Barrel3.addBox(0F, 0F, 0F, 5, 1, 1);
      Barrel3.setRotationPoint(30F, -1.8F, 0.5F);
      Barrel3.setTextureSize(80, 21);
      Barrel3.mirror = true;
      setRotation(Barrel3, 0F, 0F, 0F);
      Scope1 = new ModelRenderer(this, 50, 9);
      Scope1.addBox(0F, 0F, 0F, 1, 2, 1);
      Scope1.setRotationPoint(38F, -5.3F, 0.5F);
      Scope1.setTextureSize(80, 21);
      Scope1.mirror = true;
      setRotation(Scope1, 0F, 0F, 0F);
      Scope2L = new ModelRenderer(this, 0, 0);
      Scope2L.addBox(0F, 0F, 0F, 1, 1, 1);
      Scope2L.setRotationPoint(2.5F, -4F, 1.2F);
      Scope2L.setTextureSize(80, 21);
      Scope2L.mirror = true;
      setRotation(Scope2L, 0F, -0.0523599F, -0.7853982F);
      Scope2R = new ModelRenderer(this, 0, 0);
      Scope2R.addBox(0F, 0F, 0F, 1, 1, 1);
      Scope2R.setRotationPoint(2.5F, -4F, -0.2F);
      Scope2R.setTextureSize(80, 21);
      Scope2R.mirror = true;
      setRotation(Scope2R, 0F, 0.0523599F, -0.7853982F);
      Main1 = new ModelRenderer(this, 8, 2);
      Main1.addBox(0F, 0F, 0F, 28, 3, 3);
      Main1.setRotationPoint(1F, -3F, -0.5F);
      Main1.setTextureSize(80, 21);
      Main1.mirror = true;
      setRotation(Main1, 0F, 0F, 0F);
      Main2 = new ModelRenderer(this, 16, 16);
      Main2.addBox(0F, 0F, 0F, 13, 2, 2);
      Main2.setRotationPoint(3F, -4F, 0F);
      Main2.setTextureSize(80, 21);
      Main2.mirror = true;
      setRotation(Main2, 0F, 0F, 0F);
      Main3 = new ModelRenderer(this, 66, 12);
      Main3.addBox(0F, 0F, 0F, 6, 1, 1);
      Main3.setRotationPoint(10F, -4.5F, 0.5F);
      Main3.setTextureSize(80, 21);
      Main3.mirror = true;
      setRotation(Main3, 0F, 0F, 0F);
      Main4 = new ModelRenderer(this,16, 9);
      Main4.addBox(0F, 0F, 0F, 15, 5, 2);
      Main4.setRotationPoint(16F, -4.5F, 0F);
      Main4.setTextureSize(80, 21);
      Main4.mirror = true;
      setRotation(Main4, 0F, 0F, 0F);
      Main5 = new ModelRenderer(this, 50, 9);
      Main5.addBox(0F, 0F, 0F, 3, 1, 4);
      Main5.setRotationPoint(25F, -1.5F, -1F);
      Main5.setTextureSize(80, 21);
      Main5.mirror = true;
      setRotation(Main5, 0F, 0F, 0F);
      Main6 = new ModelRenderer(this, 46, 14);
      Main6.addBox(0F, 0F, 0F, 8, 3, 4);
      Main6.setRotationPoint(17F, -1.8F, -1F);
      Main6.setTextureSize(80, 21);
      Main6.mirror = true;
      setRotation(Main6, 0F, 0F, 0F);
      Ammo = new ModelRenderer(this, 0, 10);
      Ammo.addBox(0F, 0F, 0F, 6, 8, 2);
      Ammo.setRotationPoint(8F, -1.5F, 0F);
      Ammo.setTextureSize(80, 21);
      Ammo.mirror = true;
      setRotation(Ammo, 0F, 0F, -0.0436332F);
      Grip1 = new ModelRenderer(this, 0, 0);
      Grip1.addBox(0F, 0F, 0F, 1, 3, 2);
      Grip1.setRotationPoint(2F, 0F, 0F);
      Grip1.setTextureSize(80, 21);
      Grip1.mirror = true;
      setRotation(Grip1, 0F, 0F, 0F);
      Grip2 = new ModelRenderer(this, 0, 0);
      Grip2.addBox(0F, 0F, 0F, 2, 7, 2);
      Grip2.setRotationPoint(0F, -0.2F, 0F);
      Grip2.setTextureSize(80, 21);
      Grip2.mirror = true;
      setRotation(Grip2, 0F, 0F, 0F);
      Grip3 = new ModelRenderer(this, 0, 0);
      Grip3.addBox(0F, 0F, 0F, 1, 4, 2);
      Grip3.setRotationPoint(-1F, 2.5F, 0F);
      Grip3.setTextureSize(80, 21);
      Grip3.mirror = true;
      setRotation(Grip3, 0F, 0F, 0F);
      Grip4 = new ModelRenderer(this, 0, 0);
      Grip4.addBox(0F, 0F, 0F, 1, 2, 1);
      Grip4.setRotationPoint(5F, 0F, 0.5F);
      Grip4.setTextureSize(80, 21);
      Grip4.mirror = true;
      setRotation(Grip4, 0F, 0F, 0F);
      Grip5 = new ModelRenderer(this, 0, 0);
      Grip5.addBox(0F, 0F, 0F, 3, 1, 1);
      Grip5.setRotationPoint(2F, 1.5F, 0.5F);
      Grip5.setTextureSize(80, 21);
      Grip5.mirror = true;
      setRotation(Grip5, 0F, 0F, 0F);
      Stock1L = new ModelRenderer(this, 8, 0);
      Stock1L.addBox(0F, 0F, 0F, 16, 1, 1);
      Stock1L.setRotationPoint(-10F, -2F, 1.7F);
      Stock1L.setTextureSize(80, 21);
      Stock1L.mirror = true;
      setRotation(Stock1L, 0F, 0F, 0F);
      Stock1R = new ModelRenderer(this, 8, 0);
      Stock1R.addBox(0F, 0F, 0F, 16, 1, 1);
      Stock1R.setRotationPoint(-10F, -1F, 0.3F);
      Stock1R.setTextureSize(80, 21);
      Stock1R.mirror = true;
      setRotation(Stock1R, 3.141593F, 0F, 0F);
      Stock2 = new ModelRenderer(this, 54, 8);
      Stock2.addBox(0F, 0F, 0F, 5, 1, 3);
      Stock2.setRotationPoint(-10.2F, -3.5F, -0.5F);
      Stock2.setTextureSize(80, 21);
      Stock2.mirror = true;
      setRotation(Stock2, 0F, 0F, 0F);
      Stock3 = new ModelRenderer(this, 0, 0);
      Stock3.addBox(0F, 0F, 0F, 1, 1, 2);
      Stock3.setRotationPoint(-5.4F, -3.7F, 0F);
      Stock3.setTextureSize(80, 21);
      Stock3.mirror = true;
      setRotation(Stock3, 0F, 0F, 0F);
      Stock4 = new ModelRenderer(this, 0, 0);
      Stock4.addBox(0F, 0F, 0F, 1, 8, 2);
      Stock4.setRotationPoint(-10.8F, -4F, 0F);
      Stock4.setTextureSize(80, 21);
      Stock4.mirror = true;
      setRotation(Stock4, 0F, 0F, 0F);
      Stock5 = new ModelRenderer(this, 70, 0);
      Stock5.addBox(0F, 0F, 0F, 1, 6, 4);
      Stock5.setRotationPoint(-11F, -3F, -1F);
      Stock5.setTextureSize(80, 21);
      Stock5.mirror = true;
      setRotation(Stock5, 0F, 0F, 0F);
      Stock6 = new ModelRenderer(this, 66, 14);
      Stock6.addBox(0F, 0F, 0F, 1, 1, 2);
      Stock6.setRotationPoint(-11.2F, 4F, 0F);
      Stock6.setTextureSize(80, 21);
      Stock6.mirror = true;
      setRotation(Stock6, 0F, 0F, 0F);
  }
  
  public void render(ItemStack is, float f5, float f) {
    Barrel1.render(f5);
    Barrel2.render(f5);
    Barrel3.render(f5);
    Scope1.render(f5);
    Scope2L.render(f5);
    Scope2R.render(f5);
    Main1.render(f5);
    Main2.render(f5);
    Main3.render(f5);
    Main4.render(f5);
    Main5.render(f5);
    Main6.render(f5);
    Ammo.render(f5);
    Grip1.render(f5);
    Grip2.render(f5);
    Grip3.render(f5);
    Grip4.render(f5);
    Grip5.render(f5);
    Stock1R.render(f5);
    Stock1L.render(f5);
    Stock2.render(f5);
    Stock3.render(f5);
    Stock4.render(f5);
    Stock5.render(f5);
    Stock6.render(f5);
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
	Barrel1.render(f5);
    Barrel2.render(f5);
    Barrel3.render(f5);
    Scope1.render(f5);
    Scope2L.render(f5);
    Scope2R.render(f5);
    Main1.render(f5);
    Main2.render(f5);
    Main3.render(f5);
    Main4.render(f5);
    Main5.render(f5);
    Main6.render(f5);
    Grip1.render(f5);
    Grip2.render(f5);
    Grip3.render(f5);
    Grip4.render(f5);
    Grip5.render(f5);
    Stock1R.render(f5);
    Stock1L.render(f5);
    Stock2.render(f5);
    Stock3.render(f5);
    Stock4.render(f5);
    Stock5.render(f5);
    Stock6.render(f5);
	
}

@Override
public void renderAmmo(ItemStack is, float f5, float f) {
	Ammo.render(f5);
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
