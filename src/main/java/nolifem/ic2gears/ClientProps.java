package nolifem.ic2gears;

import net.minecraft.util.ResourceLocation;

/** 
 * @author Nolife_M
 */
public class ClientProps {
	public static final ResourceLocation 
		BASE_PATH = src("ic2gears:textures/weapons/baseModelTexture.png"),
		M14_PATH = src("ic2gears:textures/weapons/m14EBR.png"),
		PISTOL_PATH = src("ic2gears:textures/weapons/pistolEMK1.png");
	
	private static ResourceLocation src(String s) {
		return new ResourceLocation(s);
	}
}
