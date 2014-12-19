package nolifem.ic2gears.api.action.packs;

import nolifem.ic2gears.client.render.RenderActionE;
import nolifem.ic2gears.client.render.RenderModelWeaponE;

/**动作包抽象类
 * 
 * @author Nolife_M
 *
 */
public  class ActionPack {

	protected int tick;
	protected boolean isUpdating;
	protected RenderModelWeaponE renderer;
	protected RenderActionE 
		mainAction ,
		ammoAction,
		shellAction ,
		slideAction,
		leftAction,
		rightAction;
	public ActionPack(){
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
	
	public  ActionPack setRenderer(RenderModelWeaponE r){
		this.renderer = r;
		mainAction = renderer.mainAction;
		ammoAction = renderer.ammoAction;
		shellAction = renderer.shellAction;
		slideAction = renderer.slideAction;
		leftAction = renderer.leftAction;
		rightAction = renderer.rightAction;
		return this;
	}
}
