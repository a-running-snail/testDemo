package com.runningsnail.androiddemo.lock;

public class Point {// 自定义点

	float x;
	float y;
	public static final int NORMAL = 0;
	public static final int PRESS = 1;
	public static final int ERROR = 2;
    int state = 0;

	Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	float distance(Point touchPoint) {
		// TODO Auto-generated method stub
		float dis = (float) Math.sqrt((touchPoint.x - x) * (touchPoint.x - x)
				+ (touchPoint.y - y) * (touchPoint.y - y));
		return dis;
	}
}
