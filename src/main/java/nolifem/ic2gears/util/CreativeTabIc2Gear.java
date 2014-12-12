package nolifem.ic2gears.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
/**===============
 * 用于Ic2Gears的createTabs
 * 新建一个createTabs，没啥好说的，注释一行细节。
 * ===============
 * @author Nolife_M
 *
 */
public class CreativeTabIc2Gear extends CreativeTabs
{
	//这两个参数决定了createTabs标示显示的物品或方块的图标，在new的时候选个顺眼的吧！
    private final Item itemForTab;
    private final int metaForTab;

    public CreativeTabIc2Gear(int par1, String par2Str, Item itemForTab, int metaForTab)
    {
        super(par1, par2Str);
        this.itemForTab = itemForTab;
        this.metaForTab = metaForTab;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem()
    {
        return this.itemForTab;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int func_151243_f()
    {
        return this.metaForTab;
    }
}