package cn.weaponmod.api.client.render;

import cn.weaponmod.core.util.PhysicsUtil;
import net.minecraft.util.Vec3;

/**RenderActionE，辅助RenderModelWeaponE进行分块渲染
 * 单个动作的渲染参数管理器，通过更新此管理器的参数并重置，完成单个动作渲染
 * @author Nolife_M
 */
public class RenderAction {

	/*(Vec3 vec3,double x, double y, double z, int ticks, double a,double alose){*/
	private int tick = 0,
		maxTickX = 0,
		delayTickX = 0,
		maxTickY = 0,
		delayTickY = 0,
		maxTickZ = 0,
		delayTickZ = 0,
		maxTickR = 0,
		delayTickR = 0;
	private double offsetX = 0,
		offsetVX= 0,
		offsetAX = 0,
		offsetY = 0,
		offsetVY= 0,
		offsetAY = 0,
		offsetZ = 0,
		offsetVZ= 0,
		offsetAZ = 0;
	/*private boolean runBackX = false,
			runBackY = false,
			runBackZ = false,
			runBackR = false;*/
							
	private double rotationX = 0,
		rotationY = 0,
		rotationZ = 0;			
									
	private Vec3 offset = initVec(),
		rotation = initVec();
	
	public RenderAction(){}
	
	public void updateAction(){
		if(tick < 1200)
			tick++;
	}
	
	public Vec3 updateOffset(){
		double xc = getFinalPos(tick,maxTickX,delayTickX,offset.xCoord,offsetX,offsetVX,offsetAX),
				yc = getFinalPos(tick,maxTickY,delayTickY,offset.yCoord,offsetY,offsetVY,offsetAY),
				zc = getFinalPos(tick,maxTickZ,delayTickZ,offset.zCoord,offsetZ,offsetVZ,offsetAZ);
		//System.out.println("当前action数据"+xc +"  " +yc +"  "+zc);
		this.initVec(offset, xc, yc, zc);
		return offset;
	}
	
	public Vec3 updateRotation(){
		double xr = getFinalRotation(tick, maxTickR, delayTickR, rotation.xCoord, rotationX),
				yr = getFinalRotation(tick, maxTickR, delayTickR, rotation.yCoord, rotationY),
				zr = getFinalRotation(tick, maxTickR, delayTickR, rotation.zCoord, rotationZ);
		this.initVec(rotation , xr , yr , zr);
		return rotation;
	}
	
	/**根据输入的数据决定处理匀速运动或匀增减速运动
	 * @param tick
	 * @param maxtick
	 * @param p
	 * @param a
	 * @param alose
	 * @return
	 */
	private double  getFinalPos(int tick,int maxtick , int delay, double origPos,double tarPos,double v,double a){
		double p = origPos;
		if(maxtick != 0){
			if(tick > delay && tick <= maxtick + delay){
				System.out.println(tick + "===" + delay + "===" + maxtick+delay);
				if(a != 0){
					boolean flag = tarPos  - p > 0;
						double p1 = PhysicsUtil.getDistanceWithAcceleration(v, a, tick - delay) - PhysicsUtil.getDistanceWithAcceleration(v, a, tick - delay - 1);
						p += p1;
						if(flag){
							if(p > tarPos)
								p = tarPos;
						}
						else
							if(p < tarPos)
								p = tarPos;
				}
				else{
					p += (tarPos - origPos)/(maxtick + delay - tick +1);
				}
			}
		}
		return p;
		//return flag ? (p < tarPos ? p : tarPos) : p>tarPos ? p: tarPos;
	}

	/*30运动至60，加速运动
	 * 赋值为30
	 * */
	
	/**
	 * 
	 * @param tick
	 * @param maxtick
	 * @param delay
	 * @param origR
	 * @param tarR
	 * @return
	 */
	private double getFinalRotation(int tick,int maxtick , int delay, double origR,double tarR){
		double r = origR;
		boolean flag = tarR - origR > 0;
		if(tick > delay && tick <= maxtick + delay ){
				r += (tarR - origR)/(maxtick + delay - tick +1);
		}
		return r;
			//return flag ? (r < tarR ? r : tarR) : r > tarR ? r: tarR;
	}
	/**a 设为0则为x/ticks的匀速运动
	 * 否则则为有初速度v的匀加/减速度运动
	 * @param ticks 动作tick数
	 * @param delay 延迟tick数
	 * @param x 目标
	 * @param v 初速度
	 * @param a 加速度
	 */
	public void setOffsetX(int ticks, int delay , double x, double v,double a){
		this.maxTickX = ticks;
		this.delayTickX= delay;
		this.offsetX = x;
		this.offsetVX = v;
		this.offsetAX = a;
		//this.runBackX = runback;
	}
	
	/**a 设为0则为x/ticks的匀速运动
	 * 否则则为有初速度v的匀加/减速度运动
	 * @param ticks 动作tick数
	 * @param delay 延迟tick数
	 * @param y 目标
	 * @param v 初速度
	 * @param a 加速度
	 */
	public void setOffsetY(int ticks, int delay, double y, double v,double a){
		this.maxTickY = ticks;
		this.delayTickY = delay;
		this.offsetY = y;
		this.offsetVY = v;
		this.offsetAY = a;
		//this.runBackY = runback;
	}
	
	/**a 设为0则为x/ticks的匀速运动
	 * 否则则为有初速度v的匀加/减速度运动
	 * @param ticks 动作tick数
	 * @param delay 延迟tick数
	 * @param z 目标
	 * @param v 初速度
	 * @param a 加速度
	 */
	public void setOffsetZ(int ticks, int delay, double z, double v,double a){
		this.maxTickZ = ticks;
		this.delayTickZ = delay;
		this.offsetZ = z;
		this.offsetVZ = v;
		this.offsetAZ = a;
		//this.runBackZ = runback;
	}
	
	/**
	 * 基本旋转
	 * @param ticks
	 * @param delay
	 * @param x
	 * @param y
	 * @param z
	 * @param runback
	 */
	public void setRotation(int ticks,int delay,double x, double y, double z){
		this.maxTickR = ticks;
		this.delayTickR = delay;
		this.rotationX = x;
		this.rotationY = y;
		this.rotationZ = z;
		//this.runBackR = runback;
	}
	
	public void reset(){
		this.tick = 0;
	}
	
	private static void initVec(Vec3 vec) {
		vec = vec == null ?  Vec3.createVectorHelper(0D, 0D, 0D) : vec;
	}
	
	private static void initVec(Vec3 vec, Vec3 another) {
		initVec(vec, another.xCoord, another.yCoord, another.zCoord);
	}
	
	private static Vec3 initVec() {
		return Vec3.createVectorHelper(0D, 0D, 0D);
	}
	
	private static void initVec(Vec3 vec, double x, double y, double z) {
		if(vec == null)
			vec = Vec3.createVectorHelper(x, y, z);
		else {
			vec.xCoord = x;
			vec.yCoord = y;
			vec.zCoord = z;
		}
	}
}
