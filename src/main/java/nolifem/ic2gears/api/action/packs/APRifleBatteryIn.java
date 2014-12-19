package nolifem.ic2gears.api.action.packs;

import nolifem.ic2gears.client.render.RenderModelWeaponE;

public class APRifleBatteryIn extends ActionPack {

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
			leftAction.setOffsetX(20, 0, -0.2, 0, 0);
			leftAction.setOffsetY(20, 0, -0.15, 0, 0);
			leftAction.setRotation(20, 0, 0, -20, -8);
			leftAction.reset();
			mainAction.setRotation(20, 0, 0, 0, 0);
			mainAction.reset();
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
