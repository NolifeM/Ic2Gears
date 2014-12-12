package nolifem.ic2gears.proxy;

import org.lwjgl.input.Keyboard;

import cn.liutils.core.client.register.LIKeyProcess;
import cn.weaponmod.core.client.keys.WMKeyHandler;

public class ClientProxy extends CommonProxy{
	
	public static final String
	KEY_ID_CHAMBER= "wme_chambering";
	
	public void preInit(){
		LIKeyProcess.instance.addKey(KEY_ID_CHAMBER, Keyboard.KEY_F, false, new WMKeyHandler(3));
	}
	
	public void load(){}
	

}
