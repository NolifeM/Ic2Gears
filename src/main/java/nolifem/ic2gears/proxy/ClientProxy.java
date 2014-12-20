package nolifem.ic2gears.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import nolifem.ic2gears.ClientProps;
import nolifem.ic2gears.client.TickHandlerClient;
import nolifem.ic2gears.client.models.ModelM14EBR;
import nolifem.ic2gears.client.models.ModelPistolEMK1;
import nolifem.ic2gears.items.ICGItems;
import cn.weaponmod.api.client.render.RendererModelBulletWeapon;
import cn.weaponmod.api.weapon.WeaponGeneric;

/**
 * 枪械渲染绑定模型部分
 * 
 * @author Nolife_M
 *
 */
public class ClientProxy extends CommonProxy{
		
	public void preInit(){}
	
	public void init(){
		
		RendererModelBulletWeapon pistolE_render = new RendererModelBulletWeapon(new ModelPistolEMK1(), (WeaponGeneric) ICGItems.normalPistolMark1,ClientProps.PISTOL_PATH);
		RendererModelBulletWeapon m14_renderer = new RendererModelBulletWeapon(new ModelM14EBR(), (WeaponGeneric) ICGItems.normalPistolMark1,ClientProps.M14_PATH);
		
		//一般:x向前，y上,z向左
		pistolE_render.setEquipOffset(1.0,0.05,0)
							.setEquipRotation(0, -5, 0)
		//Y向下
							.setPistolLeftHand()
							.setPistolRightHand()
							
							.setFirstPersonScale(1.3)
							.setFirstPersonOffset(0.3, -0.1, 0.3)
							.setFirstPersonRotaion(0, 0, 15);
		
		m14_renderer.setEquipOffset(1.0,-0.1,0)
							.setEquipRotation(0, -5, 0)
		
							.setRifleLeftHand()
							.setRifleRightHand()
							
							.setFirstPersonScale(1.5)
							.setFirstPersonOffset(-0.5, -0.3, 0.55)
							.setFirstPersonRotaion(0, 0, 15);
		
		MinecraftForgeClient.registerItemRenderer(ICGItems.normalPistolMark1, pistolE_render);
		MinecraftForgeClient.registerItemRenderer(ICGItems.m14EBR, m14_renderer);
		
		//客户端的TickHandler
		new TickHandlerClient();
	}
	/*
	.setOffset(0.002F, 0.402F, -0.314F)
	.setScale(1.212F)
	.setStdRotation(0F,-90F, 0F)
	.setEquipOffset(0.55, -0.2, 0)
	.setEquipRotation(0.066F, 0.844F, -8.483F)
	.setInventorySpin(false)
	.setInvOffset(-2.45F, 3.04F)
	.setInvScale(0.912F)
	.setInvRotation(-35.796F, -94.77F, -3.452F);*/

}
