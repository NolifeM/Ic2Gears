package cn.weaponmod.api.action.expand.pack;

import cn.weaponmod.api.client.render.RenderAction;
import cn.weaponmod.api.client.render.RenderModelWeapon;

/**动作包抽象类
 * 
 * @author Nolife_M
 *
 */
public  class ActionPack {

	protected int tick;
	protected boolean isUpdating;
	protected RenderModelWeapon renderer;
	protected RenderAction 
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
	
	public  ActionPack setRenderer(RenderModelWeapon r){
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
