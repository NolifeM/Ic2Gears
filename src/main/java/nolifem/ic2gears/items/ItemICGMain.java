package nolifem.ic2gears.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import nolifem.ic2gears.Ic2Gears;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**==============
 * mod所有物品的父类
 * 说明：用于快捷设置物品的一些既有信息，比如createTabs、图标名等等
 *你可以在这里加入一些通用设定，快捷地设置好mod里所有的Item
 *也可以写一些你用着方便的方法，在子类中愉快地调动(´ ･ ω ･ `  )
 *===============
 * @author Nolife_M
 *
 */
public class ItemICGMain extends Item{
	
	public ItemICGMain(String name) {
		super();
		
		//这里可以设定物品的createTabs,但若这样做，则需要在主类中将createTabs的定义提前至ItemInit之前
		//this.setCreativeTab(Ic2Gears.Ic2GeatsItemsTab);
		
		//在这里完成了所有物品的默认非本地化名称设定和材质名称设定
		this.setUnlocalizedName(name);
		this.setTextureName(Ic2Gears.TEXTURE_PREFIX + name);
	}
	
	//在这里返回物品的createTabs，这样做能确保物品的createTabs被设定
	@Override
    public CreativeTabs getCreativeTab()
    {
        return Ic2Gears.Ic2GearsItemsTab;
    }
	
	//重写IconRegister
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(Ic2Gears.TEXTURE_PREFIX + getUnlocalizedName().substring(5));
    }
}
