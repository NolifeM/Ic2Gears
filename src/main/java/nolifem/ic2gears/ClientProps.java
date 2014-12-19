package nolifem.ic2gears;

import net.minecraft.util.ResourceLocation;

/** 
 * @author Nolife_M
 */
public class ClientProps {
	public static final ResourceLocation 
		BASE_PATH = src("ic2gears:textures/weapons/baseModelTexture.png"),
		
		PISTOL_PATH = src("ic2gears:textures/weapons/pistolEMK1.png"),
		M14_PATH = src("ic2gears:textures/weapons/m14EBR.png");
	
	private static ResourceLocation src(String s) {
		return new ResourceLocation(s);
	}
}
