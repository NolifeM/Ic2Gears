package cn.weaponmod.api.action.expand.pack;

import cn.weaponmod.api.client.render.RenderModelWeapon;

public class APPistolBatteryIn extends ActionPack {

	/*public APBatteryIn(RenderModelWeaponE r) {
		super(r);
	}*/

	@Override
	public void updatePack() {
		super.updatePack();
		switch(tick){
		case 1:
			leftAction.setOffsetX(20, 0, 0.4, 0, 0);
			leftAction.setOffsetY(20, 0, -0.2, 0, 0);
			leftAction.setRotation(20, 0, 0, 0, -15);
			leftAction.reset();
			ammoAction.setOffsetZ(20, 0, 0, 0, 0);
			ammoAction.setOffsetY(20, 0, 0, 0, 0);
			ammoAction.reset();
			break;
		case 21:
			mainAction.setRotation(20, 0, 0, 0, 0);
			mainAction.reset();
			slideAction.setOffsetX(40, 0, 0, 0, 0);
			slideAction.reset();
			leftAction.setOffsetX(20, 0, -0.2, 0, 0);
			leftAction.setOffsetY(20, 0, -0.1, 0, 0);
			leftAction.setRotation(20, 0, 0, -25, -8);
			leftAction.reset();
			break;
		case 41:
			leftAction.setOffsetX(20, 0, 0, 0, 0);
			leftAction.setOffsetY(20, 0, 0, 0, 0);
			leftAction.setRotation(20, 0, 0, 0, 0);
			leftAction.reset();
			break;
		case 61:
			this.isUpdating = false;
			break;
		}		
	}
}
