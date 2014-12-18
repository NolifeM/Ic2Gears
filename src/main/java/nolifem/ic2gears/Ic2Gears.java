package nolifem.ic2gears;

import net.minecraft.creativetab.CreativeTabs;
import nolifem.ic2gears.items.ICGItems;
import nolifem.ic2gears.proxy.CommonProxy;
import nolifem.ic2gears.util.CreativeTabIc2Gear;


import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


/**===============
 * MOD的主类（class）
 * 说明：Mod的心脏，在这个类中要完成大部分的载入和注册工作，这些工作通常会被分配到其他类中，并在此类中调用
 * 此类一般含有三个方法：preInit(), init(), postInit().
 * 下面需要注册的内容顺序仅作参考
 * =====preInit()=====
 * 这个方法对应mod预载入的动作，一般在这里注册event、block、item等基本内容
 *=====Init()=====
 *这个方法对应mod载入的动作，我们可以在这里
 *1、创建、读取config
 *2、注册createTab、handler、mobs、entity、tile等许多内容
 *3、某些event也可以在这里注册
 *=====postInit()=====
 *载入完成后，作者对这个方法的用处暂时不明
 *Galacticraft的作者似乎在这里loadrecipe、注册TickHandler和GuiHandler，可供参考
 * ===============
 *@author Nolife_M
 *
 */
@Mod(modid = Ic2Gears.MODID,name = Ic2Gears.NAME, version = Ic2Gears.VERSION)
public class Ic2Gears
{
    public static final String MODID = "ic2gears";
    public static final String NAME = "Ic2 Gears";
    public static final String VERSION = "0.1Alpha";
    
    //实用前缀字段
    public static String ASSET_PREFIX = "ic2gears";
    public static String TEXTURE_PREFIX = Ic2Gears.ASSET_PREFIX + ":";
    public static String PREFIX = "micdoodle8.";  
    
    //代理类实例
    @SidedProxy(clientSide = "nolifem.ic2gears.proxy.ClientProxy", serverSide = "nolifem.ic2gears.proxy.CommonProxy")
    public static CommonProxy proxy;
    
    //新建CreateTabs
    public static CreativeTabs Ic2GearsBlocksTab;
    public static CreativeTabs Ic2GearsItemsTab;
    public static CreativeTabs Ic2GearsWeaponsTab;
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
    	/*某个可能有用的判断，以modID判断某个mod是否被
    	 * if (Loader.isModLoaded("modID"))
        {
          do sth
        }*/
        System.out.println("Ic2 Gears Loading");
        ICGItems.ItemInit();
        proxy.preInit();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	//具体设定createTabs
    	Ic2GearsWeaponsTab = new CreativeTabIc2Gear(CreativeTabs.getNextID(),
    			"Ic2GearsWeapons", ICGItems.normalPistolMark1, 0);
    	
    	Ic2GearsItemsTab = new CreativeTabIc2Gear(CreativeTabs.getNextID(),
    			"Ic2GearsItem", ICGItems.normalElecMagazine, 0);
    	
    	proxy.init();
        System.out.println("Ic2 Gears Loaded");
    }
    
    @EventHandler
    public void postInit(FMLInitializationEvent event){
    }
}
