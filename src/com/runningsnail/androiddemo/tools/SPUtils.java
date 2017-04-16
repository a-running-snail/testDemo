package com.runningsnail.androiddemo.tools;


import com.runningsnail.androiddemo.application.MyApplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SPUtils {
	
	private static final String SPLASH_SHOW_FIRST = "first_show_spalsh";
	private static final String LOCKPWD = "lock_pwd";
	
	
	public static boolean getFirstShowSplash(Context mContext){
		SharedPreferences settings = mContext.getSharedPreferences(MyApplication.PREFERENCES_NAME,  Context.MODE_PRIVATE);
		return settings.getBoolean(SPLASH_SHOW_FIRST, true);
	}
    public static void setFirstShowSplash(Context mContext,boolean bool){
    	
    	SharedPreferences settings = mContext.getSharedPreferences(MyApplication.PREFERENCES_NAME,  Context.MODE_PRIVATE);
    	Editor editor = settings.edit();
    	editor.putBoolean(SPLASH_SHOW_FIRST, bool);
    	editor.commit();
    }
    
    public static String getLockPwd(Context mContext){
		SharedPreferences settings = mContext.getSharedPreferences(MyApplication.PREFERENCES_NAME,  Context.MODE_PRIVATE);
		return settings.getString(LOCKPWD, "");
	}
    public static void setLockPwd(Context mContext,String pwd){
    	
    	SharedPreferences settings = mContext.getSharedPreferences(MyApplication.PREFERENCES_NAME,  Context.MODE_PRIVATE);
    	Editor editor = settings.edit();
    	editor.putString(LOCKPWD, pwd);
    	editor.commit();
    }
    
}
