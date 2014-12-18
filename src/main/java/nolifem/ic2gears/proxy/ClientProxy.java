package nolifem.ic2gears.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import nolifem.ic2gears.ClientProps;
import nolifem.ic2gears.client.TickHandlerClient;
import nolifem.ic2gears.client.models.ModelM14EBR;
import nolifem.ic2gears.client.render.RenderModelBulletWeaponE;
import nolifem.ic2gears.items.ICGItems;

import org.lwjgl.input.Keyboard;

import cn.liutils.core.client.register.LIKeyProcess;
import cn.weaponmod.api.weapon.WeaponGeneric;
import cn.weaponmod.core.client.keys.WMKeyHandler;

/**
 * 枪械渲染绑定模型部分
 * 
 * @author Nolife_M
 *
 */
public class ClientProxy extends CommonProxy{
	
	public static final String
	KEY_ID_CHAMBER= "wme_chambering";
	
	public void preInit(){
		LIKeyProcess.instance.addKey(KEY_ID_CHAMBER, Keyboard.KEY_F, false, new WMKeyHandler(3));
	}
	
	public void init(){
		RenderModelBulletWeaponE pistol_render = new RenderModelBulletWeaponE(new ModelM14EBR(), (WeaponGeneric) ICGItems.normalPistolMark1,ClientProps.M14_PATH);
		
		//一般:x向前，y下,z向右
		pistol_render.setEquipOffset(1.0,-0.1,0)
		
							.setDefaultLeftHand()
							.setRifleRightHand()
							
							.setFirstPersonScale(1.3)
							.setFirstPersonOffset(-0.5,- 0.3, -0.6)
							.setFirstPersonRotaion(0, -10, 15);
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
		
		MinecraftForgeClient.registerItemRenderer(ICGItems.normalPistolMark1, pistol_render);
		
		//客户端的TickHandler
		new TickHandlerClient();
	}
	

}
