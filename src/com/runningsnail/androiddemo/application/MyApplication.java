package com.runningsnail.androiddemo.application;

import java.io.File;

import com.runningsnail.androiddemo.crash.CrashHandler;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.watcher.RefWatcher;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

public class MyApplication extends Application {
	
	private RefWatcher refWatcher;
	public static final boolean DEBUG = false;
	public static final String PREFERENCES_NAME = "NOTEPreferences";
	public static final String PICPATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+"runningsnail"+"/";
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		refWatcher = LeakCanary.install(this);
		File file = new File(PICPATH);
	    if(!file.exists()){
	    	file.mkdirs();
	    }
	    if (DEBUG) {
			CrashHandler crashHandler = CrashHandler.getInstance();
			// ×¢²ácrashHandler
			crashHandler.init(getApplicationContext());
		}
	}
	public static RefWatcher getRefWatcher(Context context) {
		MyApplication application = (MyApplication) context
				.getApplicationContext();
		return application.refWatcher;
	}

	

}
