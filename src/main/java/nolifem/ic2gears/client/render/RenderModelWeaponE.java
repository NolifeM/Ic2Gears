package nolifem.ic2gears.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import nolifem.ic2gears.api.action.packs.ActionPack;
import nolifem.ic2gears.api.weapons.WeaponGenericICG;
import nolifem.ic2gears.client.models.IWeaponModel;

import org.lwjgl.opengl.GL11;

import cn.liutils.api.client.model.IItemModel;
import cn.liutils.api.client.render.RenderModelItem;
import cn.liutils.api.client.util.RenderUtils;


/**
 * 给ModelWeapon专用的渲染器，提供给RendererModelBulletWeaponE调用
 *@author Nolife_M
 */
public class RenderModelWeaponE extends RenderModelItem{

	IWeaponModel modelE;
	ResourceLocation currentTexturePath;
	
	double testDouble;

	//EntityClientPlayerMP playerClient = (EntityClientPlayerMP)player;
	private RenderPlayer handRender = new RenderPlayer();
	
	//单个动作渲染器
	public RenderActionE mainAction = new RenderActionE();
	public RenderActionE ammoAction = new RenderActionE();
	public RenderActionE shellAction = new RenderActionE();
	public RenderActionE slideAction = new RenderActionE();
	
	public RenderActionE leftAction = new RenderActionE();
	public RenderActionE rightAction = new RenderActionE();
	
	//动作管理包
	public ActionPack actionPack;
	
	/*部件参数-旧
	double[] main = new double[7],
				ammo = new double[7],
				shell= new double[7],
				slide= new double[7];
	double mainX = 0,mainY= 0,mainZ= 0 , mainRX= 0,mainRY= 0,mainRZ= 0,mainAngle= 0*/
				
	public RenderModelWeaponE(IWeaponModel mdl, ResourceLocation texture) {
		super((IItemModel)mdl, texture);
		this.modelE = mdl;
		this.currentTexturePath = texture;
	}
	
	public void changeTexturePath(ResourceLocation texture){
		this.currentTexturePath = texture;
	}
	
	@Override
	//完成渲染的主要方法
	public void renderEquipped(ItemStack item, RenderBlocks render,
			EntityLivingBase entity, ItemRenderType type) {
		
		boolean firstPerson = false;

		WeaponGenericICG wpnType = (WeaponGenericICG) item.getItem();
		
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			//firstPerson控制
		    firstPerson = (entity == Minecraft.getMinecraft().thePlayer && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) 
				&& !(Minecraft.getMinecraft().currentScreen instanceof net.minecraft.client.gui.inventory.GuiInventory);
		}
		
		if (item.stackTagCompound == null)
			item.stackTagCompound = new NBTTagCompound();
		
		if(actionPack != null)
			actionPack.updatePack();
		
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glPushMatrix(); {
			RenderUtils.loadTexture(currentTexturePath);
			float sc2 = 0.0625F;
			GL11.glRotatef(40F, 0, 0, 1);
			this.doTransformation(equipOffset);
			this.doRotation(equipRotation);
			GL11.glRotatef(90, 0, -1, 0);
			renderAtStdPositionE(getModelAttribute(item, entity),firstPerson, entity);
		} GL11.glPopMatrix();
		
	}
		
	protected final void renderAtStdPositionE() {
		renderAtStdPosition(0.0F);
	}
	
	protected final void renderAtStdPositionE(float i,boolean firstPerson,EntityLivingBase entity ) {
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glScaled(scale, -scale, scale);
		this.doTransformation(stdOffset);                                                                                                      
		GL11.glRotated(stdRotation.yCoord + 180, 0.0F, 1.0F, 0.0F);
		GL11.glRotated(stdRotation.zCoord, 0.0F, 0.0F, 1.0F);
		GL11.glRotated(stdRotation.xCoord, 1.0F, 0.0F, 0.0F);
		
		//updatePart();
		/*firstPersonRotation.xCoord++;
		doRotation(firstPersonRotation);*/
		//完成武器的四个部分的渲染
		//if(firstPerson && entity instanceof EntityPlayer){
			
			EntityPlayer player = (EntityPlayer)entity;
			EntityClientPlayerMP playerClient = (EntityClientPlayerMP)player;

			ammoAction.updateAction();
			mainAction.updateAction();
			shellAction.updateAction();
			slideAction.updateAction();
			
			GL11.glScalef(1F , 1F , -1F );
			//测试代码
			//GL11.glTranslated(0, 0, 2);
			
			doTransformation(mainAction.updateOffset());
			doRotationWithPos(mainAction.updateRotation(),mainAction.updateOffset());
			
			GL11.glPushMatrix(); {
					
				GL11.glScaled(firstPersonScale,firstPersonScale,firstPersonScale);
				doTransformation(firstPersonOffset);
				doRotation(firstPersonRotation);

			System.out.println("Main当前offset" + mainAction.updateOffset());
			System.out.println("Main当前rotation" + mainAction.updateRotation());
				modelE.renderMain(null, 0.0625F, i);
			
				GL11.glPushMatrix(); {
					doTransformation(ammoAction.updateOffset());
					doRotationWithPos(ammoAction.updateRotation(),ammoAction.updateOffset());
				System.out.println("Ammo当前offset" + ammoAction.updateOffset());
				System.out.println("Ammo当前rotation" + ammoAction.updateRotation());
					modelE.renderAmmo(null, 0.0625F, i);
				} GL11.glPopMatrix();
			
				GL11.glPushMatrix(); {
					doTransformation(shellAction.updateOffset());
					doRotationWithPos(shellAction.updateRotation(),shellAction.updateOffset());
					modelE.renderShell(null, 0.0625F, i);
				} GL11.glPopMatrix();
			
				GL11.glPushMatrix(); {
					doTransformation(slideAction.updateOffset());
					doRotationWithPos(slideAction.updateRotation(),slideAction.updateOffset());
					modelE.renderSlide(null, 0.0625F, i);
				} GL11.glPopMatrix();
			} GL11.glPopMatrix();
			//手部渲染
				RenderUtils.loadTexture(playerClient.getLocationSkin());
			
			//左手部渲染调试
				GL11.glPushMatrix(); {
					leftAction.updateAction();
					doTransformation(leftAction.updateOffset().addVector(leftHandOffset.xCoord, leftHandOffset.yCoord, leftHandOffset.zCoord));
					doRotation(leftAction.updateRotation().addVector(leftHandRotation.xCoord, leftHandRotation.yCoord, leftHandRotation.zCoord));
					//修正点
					GL11.glTranslated(0.6, 0, 0);
					GL11.glScalef(1.95F, 1.95F, 1.95F);
				
					handRender.renderFirstPersonArm(player);
				} GL11.glPopMatrix();
			
			//右手部渲染调试
				GL11.glPushMatrix(); {
					GL11.glScaled(2, 2, 2);
					rightAction.updateAction();
					doTransformation(rightAction.updateOffset().addVector(rightHandOffset.xCoord, rightHandOffset.yCoord, rightHandOffset.zCoord));
					doRotation(rightAction.updateRotation().addVector(rightHandRotation.xCoord, rightHandRotation.yCoord, rightHandRotation.zCoord));
					//旋转点测试
					//GL11.glRotated(testDouble++, 0, 0, 1.0D);
					//修正旋转点
					GL11.glTranslated(0.3, 0, 0);
					handRender.renderFirstPersonArm(player);
				} GL11.glPopMatrix();
		/*}
		else
			modelE.render(null, 0.0625F, i);*/
		GL11.glEnable(GL11.GL_CULL_FACE);
	}
	
	public RenderModelWeaponE setFirstPersonOffset(double b0, double b1, double b2) {
		initVec(firstPersonOffset, b0, b1, b2);
		return this;
	}

	public RenderModelWeaponE setFirstPersonRotaion(float x, float y, float z) {
		initVec(firstPersonRotation, x, y, z);	
		return this;
	}
	
	public RenderModelWeaponE setFirstPersonScale(double d) {
		firstPersonScale = d;
		return this;
	}

	public RenderModelWeaponE setLeftHandOffset(double b0, double b1, double b2) {
		initVec(leftHandOffset, b0, b1, b2);
		return this;
	}

	public RenderModelWeaponE setLeftHandRotation(float x, float y, float z) {
		initVec(leftHandRotation, x, y, z);	
		return this;
	}

	public RenderModelWeaponE setRightHandOffset(double b0, double b1, double b2) {
		initVec(rightHandOffset, b0, b1, b2);
		return this;
	}

	public RenderModelWeaponE setRightHandRotation(float x, float y, float z) {
		initVec(rightHandRotation, x, y, z);	
		return this;
	}
	
	protected void doRotationWithPos(Vec3 vec3, Vec3 pos) {
		if(vec3 != null && pos != null) {
			GL11.glTranslated(pos.xCoord,pos.yCoord,pos.zCoord);
			GL11.glRotated(vec3.yCoord, 0.0F, 1.0F, 0.0F);
			GL11.glRotated(vec3.zCoord, 0.0F, 0.0F, 1.0F);
			GL11.glRotated(vec3.xCoord, 1.0F, 0.0F, 0.0F);
			GL11.glTranslated(-pos.xCoord,-pos.yCoord,-pos.zCoord);	
		}
	}
	
	/** FirstPerson*/
	private Vec3 firstPersonOffset = initVec();
	private Vec3 firstPersonRotation = initVec();
	private double firstPersonScale = 1; 
	
	/** leftHand*/
	private Vec3 leftHandOffset = initVec();
	private Vec3 leftHandRotation = initVec();
	
	/** rightHand*/
	private Vec3 rightHandOffset = initVec();
	private Vec3 rightHandRotation = initVec();
	
	//预制
	/** main*//*
	private Vec3 mainOffset = initVec();
	private Vec3 mainRotation = initVec();
	
	*//** ammo*//*
	private Vec3 ammoOffset = initVec();
	private Vec3 ammoRotation = initVec();
	
	*//** shell*//*
	private Vec3 shellOffset = initVec();
	private Vec3 shellRotation = initVec();
	
	*//** slide*//*
	private Vec3 slideOffset = initVec();
	private Vec3 slideRotation = initVec();*/
}