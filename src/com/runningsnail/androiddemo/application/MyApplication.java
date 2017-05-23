package com.runningsnail.androiddemo.application;

import java.io.File;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.mscv5plusdemo.SpeechApp;
import com.runningsnail.androiddemo.crash.CrashHandler;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.watcher.RefWatcher;
import com.runningsnail.androiddemo.R;
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
		StringBuffer param = new StringBuffer();
		param.append("appid="+getString(R.string.app_id));
		param.append(",");
		// 设置使用v5+
		param.append(SpeechConstant.ENGINE_MODE+"="+SpeechConstant.MODE_MSC);
		SpeechUtility.createUtility(MyApplication.this, param.toString());
		refWatcher = LeakCanary.install(this);
		File file = new File(PICPATH);
	    if(!file.exists()){
	    	file.mkdirs();
	    }
	    if (DEBUG) {
			CrashHandler crashHandler = CrashHandler.getInstance();
			// ע��crashHandler
			crashHandler.init(getApplicationContext());
		}
	}
	public static RefWatcher getRefWatcher(Context context) {
		MyApplication application = (MyApplication) context
				.getApplicationContext();
		return application.refWatcher;
	}

	

}
