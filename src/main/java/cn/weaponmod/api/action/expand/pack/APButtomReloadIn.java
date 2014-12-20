package cn.weaponmod.api.action.expand.pack;

import cn.weaponmod.api.client.render.RenderModelWeapon;

public class APButtomReloadIn extends ActionPack {

	/*public APButtomReloadIn(RenderModelWeaponE r) {
		super(r);
	}*/

	@Override
	public void updatePack() {
		super.updatePack();
		switch(tick){
		case 1:
			ammoAction.setOffsetX(60, 0, 0, 0, 0);
			ammoAction.setOffsetY(60, 0, 0, 0, 0);
			ammoAction.reset();
			
			leftAction.setOffsetX(40, 0, -0.2, 0, 0);
			leftAction.setRotation(40, 0, -15, 0, 0);
			leftAction.reset();
			break;
		case 41:
			mainAction.setRotation(10, 0, 0, 0, -35);
			mainAction.reset();
			
			leftAction.setOffsetX(20, 0, -0.1, 0, 0);
			leftAction.setRotation(10, 0, -7, 0, 10);
			leftAction.reset();
			break;
		case 51:
			mainAction.setRotation(20, 0, 0, 0, 2);
			mainAction.reset();
			
			leftAction.setRotation(10, 0, 0, 0, 20);
			leftAction.reset();
			break;
		case 71:
			mainAction.setRotation(20, 0, 0, 0, 0);
			mainAction.reset();
			
			leftAction.setOffsetX(20, 0, 0, 0, 0);
			leftAction.setRotation(20, 0, 0, 0, 0);
			leftAction.reset();
			break;
		case 81:
			this.isUpdating = false;
			break;
		}		
	}
}
