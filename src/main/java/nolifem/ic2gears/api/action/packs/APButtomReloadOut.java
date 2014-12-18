package nolifem.ic2gears.api.action.packs;

import nolifem.ic2gears.client.render.RenderModelWeaponE;

public class APButtomReloadOut extends ActionPack {

	public APButtomReloadOut(RenderModelWeaponE r) {
		super(r);
	}

	@Override
	public void updatePack() {
		super.updatePack();
		switch(tick){
		case 1:
			renderer.mainAction.setOffsetX(20, 10, -0.1, 0, 0);
			renderer.mainAction.setOffsetZ(20, 10, 0.1, 0, 0);
			renderer.mainAction.setRotation(20, 20, 0, -20, -15);
			renderer.mainAction.reset();
			renderer.ammoAction.setOffsetY(40, 5, 1, 9.8 ,0);
			renderer.ammoAction.setOffsetZ(40, 0, 1,  0.1   ,-0.02);
			renderer.ammoAction.reset();
			break;
		case 41:
			this.isUpdating = false;
			break;
		}		
	}

}
