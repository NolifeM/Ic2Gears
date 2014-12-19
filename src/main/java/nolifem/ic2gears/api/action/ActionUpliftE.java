package nolifem.ic2gears.api.action;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import nolifem.ic2gears.api.action.packs.APButtomReloadIn;
import nolifem.ic2gears.api.action.packs.APNormalShoot;
import nolifem.ic2gears.client.render.RenderModelBulletWeaponE;
import nolifem.ic2gears.client.render.RenderModelWeaponE;
import cn.weaponmod.api.action.Action;
import cn.weaponmod.api.information.InfWeapon;
import cn.weaponmod.core.client.UpliftHandler;
import cn.weaponmod.core.proxy.WMClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ActionUpliftE extends Action {
	
	RenderModelWeaponE renderer;
	
	private float
		uplift_radius = UpliftHandler.DEFAULT_UPLIFT_RADIUS,
		uplift_speed = UpliftHandler.DEFAULT_UPLIFT_SPEED,
		recover_speed = UpliftHandler.DEFAULT_RECOVER_SPEED,
		max_uplift = uplift_radius * 4.3F;
	
	public ActionUpliftE() {
		super(0, "uplift");
	}
	
	public ActionUpliftE(float uplift_rad, float uplift_spd, float recover_spd, float max_rad) {
		this();
		uplift_radius = uplift_rad;
		uplift_speed = uplift_spd;
		recover_speed = recover_spd;
		max_uplift = max_rad;
	}
	
	@Override
	public boolean onActionTick(World world, EntityPlayer player, InfWeapon inf) {
		return true;
	}
	
	@Override
	public boolean onActionEnd(World world, EntityPlayer player, InfWeapon information) {
		return false;
	}

	/* (non-Javadoc)
	 * @see cn.weaponmod.api.action.Action#getPriority()
	 */
	@Override
	public int getPriority() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see cn.weaponmod.api.action.Action#doesConcurrent(cn.weaponmod.api.action.Action)
	 */
	@Override
	public boolean doesConcurrent(Action other) {
		return true;
	}
	
	@Override
	public boolean onActionBegin(World world, EntityPlayer player,
			InfWeapon information) {
		ItemStack curItem = player.getCurrentEquippedItem();
		renderer = getWeaponRenderer(curItem);
		applyRenderEffect(world, player, information, false);
		if(world.isRemote) {
			WMClientProxy.upliftHandler.setProperties(uplift_radius, uplift_speed, recover_speed, max_uplift);
			WMClientProxy.upliftHandler.doUplift();
		}
		
		
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void applyRenderEffect(World world, EntityPlayer player, InfWeapon inf, boolean first) {
		ItemStack curItem = player.getCurrentEquippedItem();
		if(curItem != null){
			renderer.actionPack = new APNormalShoot(uplift_radius);
			renderer.actionPack.setRenderer(renderer);
			renderer.actionPack.reset();	
		}
	}
	
	private RenderModelWeaponE getWeaponRenderer(ItemStack curItem){
		return  ((RenderModelBulletWeaponE)MinecraftForgeClient.getItemRenderer(curItem, IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)).mdlRenderer;
	}
	
	@Override
	public int getRenderPriority() {
		return -1;
	}

}
