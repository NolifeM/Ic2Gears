package cn.weaponmod.api.action.expand.pack;

import cn.weaponmod.api.client.render.RenderModelWeapon;

public class APNormalShoot extends ActionPack{

	float uplift,back;
	
	public APNormalShoot() {
		super();
	}
	
	/*public APUplift(RenderModelWeaponE r) {
		super(r);
	}*/
	
	public APNormalShoot(float uplift) {
		this();
		 this.uplift = uplift * 2.5F;
		 this.back = uplift/10;
	}

	@Override
	public void updatePack() {
		super.updatePack();
		switch(tick){
		case 1:
			mainAction.setOffsetX(5, 0, -back, 0, 0);
			mainAction.setRotation(5, 0, 0, 0, -uplift);
			mainAction.reset();
			slideAction.setOffsetX(5, 0, -back, 0, 0);
			slideAction.reset();
			break;
		case 6:
			mainAction.setOffsetX(5, 0, 0, 0, 0);
			mainAction.setRotation(5, 0, 0, 0, 0);
			mainAction.reset();
			slideAction.setOffsetX(5, 0, 0, 0, 0);
			slideAction.reset();
			break;
		case 11:
			this.isUpdating = false;
			break;
		}
	}
}
