package nolifem.ic2gears.util;

public class PhysicsUtil {
	
	/**
	 * 通过初速度、加速度和时间获得物品当前向某个方向运动的距离
	 * 你也可以只输入v和a来求匀速运动距离……
	 * @param v
	 * @param a
	 * @param ticks
	 * @return 当前的距离
	 */
	public static double getDistanceWithAcceleration(double v,double a, int ticks){
		double t = (double)ticks/20;
		double s = v*t -a*t*t/2;
		return s;
	}
}
