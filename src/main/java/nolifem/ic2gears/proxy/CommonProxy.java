package nolifem.ic2gears.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy //implements IGuiHandler
{	  
/*	  public void registerGUIHandler()
	  {
		//注册GUI
		    NetworkRegistry.instance().registerGuiHandler(ZombieStuffsMod.instance, new CommonGuiHandler());
	  }*/
	  
	public void preInit(){}
	
	public void load(){}

	 /* public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		  return null;
	  }

	 public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){  
		  return null;
	  }*/
}
