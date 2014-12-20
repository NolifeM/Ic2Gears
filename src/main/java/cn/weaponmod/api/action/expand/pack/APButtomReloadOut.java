package cn.weaponmod.api.action.expand.pack;

import cn.weaponmod.api.client.render.RenderModelWeapon;

public class APButtomReloadOut extends ActionPack {

	
	public APButtomReloadOut() {
		super();
	}
	
	/*public APButtomReloadOut(RenderModelWeaponE r) {
		super(r);
	}*/

	@Override
	public void updatePack() {
		super.updatePack();
		switch(tick){
		case 1:
			mainAction.setRotation(20, 0, 0, -10, -30);
			mainAction.reset();
			leftAction.setOffsetX(20, 0, -0.2, 0, 0);
			leftAction.setRotation(20, 0, -15, 0, 0);
			leftAction.reset();
			break;
		case 21:
			mainAction.setRotation(5, 0, 0, -10, -33);
			mainAction.reset();
			ammoAction.setOffsetX(60, 0, -0.3, 0, 0);
			ammoAction.setOffsetY(60, 0, 1.3, 0, 0);
			ammoAction.reset();
			leftAction.setRotation(40, 0, 0, 0, 100);
			leftAction.reset();
			break;
		case 26:
			mainAction.setRotation(5, 5, 0, -10, -27);
			mainAction.reset();
			break;
		case 36:
			mainAction.setRotation(5, 0, 0, -10, -30);
			mainAction.reset();
			break;
		case 81:
			this.isUpdating = false;
			break;
		}		
	}
}
