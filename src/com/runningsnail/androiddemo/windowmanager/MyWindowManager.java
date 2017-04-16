package com.runningsnail.androiddemo.windowmanager;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.runningsnail.androiddemo.R;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

public class MyWindowManager {

	private static WindowManager manager;
	private static SmallFloatWindowLayout smalllayout;
	private static LayoutParams smallwindowParams;
	private static LayoutParams bigwindowParams;
	private static BigFloatWindowLayout biglayout;
	
	public static void createSmallWindow(Context context) {
		Log.e("tag", "----------createSmallWindow-------------");
		WindowManager manager = getWindowManage(context);
		int width = manager.getDefaultDisplay().getWidth();
		int height = manager.getDefaultDisplay().getHeight();
		if (smalllayout == null) {
			smalllayout = new SmallFloatWindowLayout(context);
			if (smallwindowParams == null) {
				smallwindowParams = new LayoutParams();
				smallwindowParams.type = LayoutParams.TYPE_PHONE;
				smallwindowParams.format = PixelFormat.RGBA_8888;
				smallwindowParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
						| LayoutParams.FLAG_NOT_FOCUSABLE;
				smallwindowParams.gravity = Gravity.LEFT | Gravity.TOP;
				smallwindowParams.width = LayoutParams.WRAP_CONTENT;
				smallwindowParams.height = LayoutParams.WRAP_CONTENT;
				smallwindowParams.x = width;
				smallwindowParams.y = height / 2;
			}
			smalllayout.setParams(smallwindowParams);
			manager.addView(smalllayout, smallwindowParams);
		}
	}
	public static void removeSmallWindow(Context context){
		Log.e("tag", "----------removeSmallWindow-------------");
		WindowManager manager = getWindowManage(context);
		if (smalllayout != null) {
			manager.removeView(smalllayout);
		}
		smalllayout =null;
	}
	public static void createBigWindow(Context context) {
		Log.e("tag", "----------createBigWindow-------------");
		WindowManager manager = getWindowManage(context);
		int width = manager.getDefaultDisplay().getWidth();
		int height = manager.getDefaultDisplay().getHeight();
		if (biglayout == null) {
			biglayout = new BigFloatWindowLayout(context);
			if (bigwindowParams == null) {
				bigwindowParams = new LayoutParams();
				bigwindowParams.type = LayoutParams.TYPE_PHONE;
				bigwindowParams.format = PixelFormat.RGBA_8888;
				bigwindowParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
						| LayoutParams.FLAG_NOT_FOCUSABLE;
				bigwindowParams.gravity = Gravity.LEFT | Gravity.TOP;
				bigwindowParams.width = LayoutParams.WRAP_CONTENT;;
				bigwindowParams.height = LayoutParams.WRAP_CONTENT;;
				bigwindowParams.x = width;
				bigwindowParams.y = height / 2;
			}
			manager.addView(biglayout, bigwindowParams);
		}
	}
	public static void removeBigWindow(Context context){
		Log.e("tag", "----------removeBigWindow-------------");
		WindowManager manager = getWindowManage(context);
		if (biglayout != null) {
			manager.removeView(biglayout);
		}
		biglayout =null;
	}
	public static WindowManager getWindowManage(Context context) {

		if (manager == null) {
			manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		}
		return manager;
	};
	public static boolean isWindowShowing(){
		if (biglayout != null || smalllayout !=null) {
			return true;
		}else {
			return false;
		}
	}
	public static String getInfo(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		return format.format(date);
	}
	public static void updateUsedPercent(Context applicationContext) {
		// TODO Auto-generated method stub
		if (smalllayout != null) {
			TextView tvTextView = (TextView) smalllayout.findViewById(R.id.float_small_tv);
			if (smalllayout == null) {
				Log.e("tag", "----------updateUsedPercent---------smalllayout == null----");
			}
			if (tvTextView == null) {
				Log.e("tag", "----------updateUsedPercent---------tvTextView == null----");
			}
			Log.e("tag", "----------updateUsedPercent-------------");
			tvTextView.setText(getInfo());
		}
	}
}
