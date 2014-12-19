package nolifem.ic2gears.api.action.packs;

import nolifem.ic2gears.client.render.RenderModelWeaponE;

public class APBatteryOut extends ActionPack {

	public APBatteryOut(){
		super();
	}
	
	@Override
	public void updatePack() {
		super.updatePack();
		switch(tick){
		case 1:
			mainAction.setRotation(20, 0, 0, -10, -10);
			mainAction.reset();
			leftAction.setRotation(20, 0, 0, 0, 90);
			leftAction.reset();
			break;
		case 21:
			ammoAction.setOffsetZ(20, 0, 1, 0, -1.5);
			ammoAction.setOffsetY(20, 5, 2, 0, -4.9);
			ammoAction.reset();
			slideAction.setOffsetX(40, 0, -0.3, 0, 0);
			slideAction.reset();
			break;
		case 61:
			this.isUpdating = false;
			break;
		}		
	}
}
