package nolifem.ic2gears.api.action.packs;

import nolifem.ic2gears.client.render.RenderModelWeaponE;

/**动作包父类
 * 
 * @author Nolife_M
 *
 */
public class ActionPack {

	protected int tick;
	protected boolean isUpdating;
	protected RenderModelWeaponE renderer;
	
	public ActionPack(RenderModelWeaponE r){
		renderer = r;
		tick = 0;
		isUpdating = false;
	}
	
	public void updatePack(){
		if(isUpdating)
			tick++;
	}
	public  void reset(){
		tick = 0;
		isUpdating = true;
	}
}
