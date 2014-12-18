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
			renderer.mainAction.setRotation(20, 0, 0, -10, -30);
			renderer.mainAction.reset();
			renderer.leftAction.setOffsetX(20, 0, -0.2, 0, 0);
			renderer.leftAction.setRotation(20, 0, -15, 0, 0);
			renderer.leftAction.reset();
			break;
		case 21:
			renderer.mainAction.setRotation(5, 0, 0, -10, -33);
			renderer.mainAction.reset();
			renderer.ammoAction.setOffsetX(60, 0, -0.3, 0, 0);
			renderer.ammoAction.setOffsetY(60, 0, 1.3, 0, 0);
			renderer.ammoAction.reset();
			renderer.leftAction.setRotation(40, 0, 0, 0, 100);
			renderer.leftAction.reset();
			break;
		case 26:
			renderer.mainAction.setRotation(5, 5, 0, -10, -27);
			renderer.mainAction.reset();
			break;
		case 36:
			renderer.mainAction.setRotation(5, 0, 0, -10, -30);
			renderer.mainAction.reset();
			break;
		case 81:
			this.isUpdating = false;
			break;
		}		
	}

}
