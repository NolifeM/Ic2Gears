package nolifem.ic2gears.api.action.parts;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cn.weaponmod.api.action.Action;
import cn.weaponmod.api.information.InfWeapon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**=============
 * 对ActionReload的扩展
 * 主要关注:
 *  单发子弹装填
 *  正确处理空膛装填
 * @author Nolife_M
 *
 */
public class ActionBREnd extends Action{

	public String sound = "";
	
	public ActionBREnd(int ticks, String snd) {
		super(ticks, "brEnd");
		this.sound = snd;
		//this.name = "reloadBIn";
	}

	@Override
	public boolean onActionBegin(World world, EntityPlayer player, InfWeapon inf) {
		System.out.println("结束过程Begin");
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
		System.out.println("结束过程End");
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
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderPriority() {
		// TODO Auto-generated method stub
		return 2;
	}
}
