package nolifem.ic2gears.api.action.packs;

import nolifem.ic2gears.client.render.RenderModelWeaponE;

public class APButtomReloadIn extends ActionPack {

	public APButtomReloadIn(RenderModelWeaponE r) {
		super(r);
	}

	@Override
	public void updatePack() {
		super.updatePack();
		switch(tick){
		case 1:
			renderer.ammoAction.setOffsetX(60, 0, 0, 0, 0);
			renderer.ammoAction.setOffsetY(60, 0, 0, 0, 0);
			renderer.ammoAction.reset();
			
			renderer.leftAction.setOffsetX(40, 0, -0.2, 0, 0);
			renderer.leftAction.setRotation(40, 0, -15, 0, 0);
			renderer.leftAction.reset();
			break;
		case 41:
			renderer.mainAction.setRotation(10, 0, 0, 0, -35);
			renderer.mainAction.reset();
			
			renderer.leftAction.setOffsetX(20, 0, -0.1, 0, 0);
			renderer.leftAction.setRotation(10, 0, -7, 0, 10);
			renderer.leftAction.reset();
			break;
		case 51:
			renderer.mainAction.setRotation(20, 0, 0, 0, 2);
			renderer.mainAction.reset();
			
			renderer.leftAction.setRotation(10, 0, 0, 0, 20);
			renderer.leftAction.reset();
			break;
		case 71:
			renderer.mainAction.setRotation(20, 0, 0, 0, 0);
			renderer.mainAction.reset();
			
			renderer.leftAction.setOffsetX(20, 0, 0, 0, 0);
			renderer.leftAction.setRotation(20, 0, 0, 0, 0);
			renderer.leftAction.reset();
			break;
		case 81:
			this.isUpdating = false;
			break;
		}		
	}
}
