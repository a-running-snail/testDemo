package com.runningsnail.androiddemo.windowmanager;

import java.lang.reflect.Field;

import com.runningsnail.androiddemo.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SmallFloatWindowLayout extends LinearLayout {


	//public static int viewWidth;
	//public static int viewHeight;
	private int statusBarHeight;
    private WindowManager manager;
    private WindowManager.LayoutParams windowParams;
	private float xinView,yinView,xinScreen,yinScreen;
	private float xDownInScreen;

	/**
	 * 记录手指按下时在屏幕上的纵坐标的值
	 */
	private float yDownInScreen;
	
	public SmallFloatWindowLayout(Context context, AttributeSet attrs,int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public SmallFloatWindowLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public SmallFloatWindowLayout(Context context) {
		super(context);
		if (manager == null) {
			manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		}
		View view = LayoutInflater.from(context).inflate(R.layout.float_small, null);
		//viewWidth = view.getLayoutParams().width;
		//viewHeight = view.getLayoutParams().height;
		TextView tvView = (TextView) view.findViewById(R.id.float_small_tv);
		if (tvView == null) {
			Log.e("tag", "------SmallFloatWindowLayout----tvView == null--------");
		}else {
			Log.e("tag", "------SmallFloatWindowLayout----tvView !!!!= null--------");
		}
		tvView.setText(MyWindowManager.getInfo());
		this.addView(view);
		// TODO Auto-generated constructor stub
	}

	public void setParams(WindowManager.LayoutParams windowParams) {
		// TODO Auto-generated method stub
		this.windowParams = windowParams;
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			xinView = event.getX();
			yinView = event.getY();
			xDownInScreen = event.getRawX();
			yDownInScreen = event.getRawY() - getStatusBarHeight();
			xinScreen = event.getRawX();
			yinScreen = event.getRawY()-getStatusBarHeight();
			break;
		case MotionEvent.ACTION_MOVE:
			xinScreen = event.getRawX();
			yinScreen = event.getRawY()-getStatusBarHeight();
			updatePosition();
			break;
		case MotionEvent.ACTION_UP:
			// 如果手指离开屏幕时，xDownInScreen和xInScreen相等，且yDownInScreen和yInScreen相等，则视为触发了单击事件。
			if (xDownInScreen == xinScreen && yDownInScreen == yinScreen) {
				openBigWindow();
			}
			break;

		default:
			break;
		}
		return true;
	}
	private void openBigWindow() {
		MyWindowManager.createBigWindow(getContext());
		MyWindowManager.removeSmallWindow(getContext());
	}
  public void updatePosition(){
	  windowParams.x = (int) (xinScreen-xinView);
	  windowParams.y = (int) (yinScreen-yinView);
	  manager.updateViewLayout(this, windowParams);
  }
  /**
	 * 用于获取状态栏的高度。
	 * 
	 * @return 返回状态栏高度的像素值。
	 */
	private int getStatusBarHeight() {
		if (statusBarHeight == 0) {
			try {
				Class<?> c = Class.forName("com.android.internal.R$dimen");
				Object o = c.newInstance();
				Field field = c.getField("status_bar_height");
				int x = (Integer) field.get(o);
				statusBarHeight = getResources().getDimensionPixelSize(x);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return statusBarHeight;
	}
}
