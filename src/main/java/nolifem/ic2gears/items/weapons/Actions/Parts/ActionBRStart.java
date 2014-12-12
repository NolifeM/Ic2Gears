package nolifem.ic2gears.items.weapons.Actions.Parts;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cn.weaponmod.api.action.Action;
import cn.weaponmod.api.information.InfWeapon;

/**=============
 * 对ActionReload的扩展
 * 主要关注:
 *  单发子弹装填
 *  正确处理空膛装填
 * @author Nolife_M
 *
 */
public class ActionBRStart extends Action{

	public String sound = "";
	
	public ActionBRStart(int ticks, String snd) {
		super(ticks, "brStart");
		this.sound = snd;
		//this.name = "reloadBIn";
	}

	@Override
	public boolean onActionBegin(World world, EntityPlayer player, InfWeapon inf) {
		player.playSound(sound,  0.5F, 1.0F);
		return true;
	}
	
	@Override
	public boolean onActionTick(World world, EntityPlayer player, InfWeapon inf) {
		return true;
	}
	
	//改动
	@Override
	public boolean onActionEnd(World world, EntityPlayer player, InfWeapon inf) {
		return true;
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public boolean doesConcurrent(Action other) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderPriority() {
		// TODO Auto-generated method stub
		return 2;
	}
}
