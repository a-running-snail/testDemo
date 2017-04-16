package com.runningsnail.androiddemo.windowmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class FloatWindowService extends Service {

	private Timer timer;
	private Handler handler = new Handler();

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub

		Log.e("tag", "----------onStartCommand-------------");
		if (timer == null) {
			timer = new Timer();
			timer.scheduleAtFixedRate(new Task(), 0, 1000);
		}
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.e("tag", "----------onDestroy-------------");
		timer.cancel();
		timer = null;
		super.onDestroy();
	}

	class Task extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// 当前界面是桌面，且没有悬浮窗显示，则创建悬浮窗。
			if (isHome() && !MyWindowManager.isWindowShowing()) {
				Log.e("tag", "----------TimerTask----当前界面是桌面，且没有悬浮窗显示，则创建悬浮窗---------");
				handler.post(new Runnable() {
					@Override
					public void run() {
						MyWindowManager.createSmallWindow(getApplicationContext());
					}
				});
			}
			// 当前界面不是桌面，且有悬浮窗显示，则移除悬浮窗。
			else if (!isHome() && MyWindowManager.isWindowShowing()) {
				Log.e("tag", "----------TimerTask---当前界面不是桌面，且有悬浮窗显示，则移除悬浮窗---------");
				handler.post(new Runnable() {
					@Override
					public void run() {
						MyWindowManager
								.removeSmallWindow(getApplicationContext());
						MyWindowManager
								.removeBigWindow(getApplicationContext());
					}
				});
			}
			// 当前界面是桌面，且有悬浮窗显示，则更新内存数据。
			else if (isHome() && MyWindowManager.isWindowShowing()) {
				Log.e("tag", "----------TimerTask---当前界面是桌面，且有悬浮窗显示，则更新内存数据。--------");
				handler.post(new Runnable() {
					@Override
					public void run() {
						MyWindowManager
								.updateUsedPercent(getApplicationContext());
					}
				});
			}
		}

	}

	/**
	 * 判断当前界面是否是桌面
	 */
	private boolean isHome() {
		ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		@SuppressWarnings("deprecation")
		List<RunningTaskInfo> rti = mActivityManager.getRunningTasks(1);
		return getHomes().contains(rti.get(0).topActivity.getPackageName());
	}

	/**
	 * 获得属于桌面的应用的应用包名称
	 * 
	 * @return 返回包含所有包名的字符串列表
	 */
	private List<String> getHomes() {
		List<String> names = new ArrayList<String>();
		PackageManager packageManager = this.getPackageManager();
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(
				intent, PackageManager.MATCH_DEFAULT_ONLY);
		for (ResolveInfo ri : resolveInfo) {
			names.add(ri.activityInfo.packageName);
		}
		return names;
	}
}
