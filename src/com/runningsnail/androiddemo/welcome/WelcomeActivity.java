package com.runningsnail.androiddemo.welcome;


import com.ant.liao.GifView;
import com.ant.liao.GifView.GifImageType;
import com.runningsnail.androiddemo.R;
import com.runningsnail.androiddemo.tools.SPUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Toast;

public class WelcomeActivity extends Activity{
	private static final int  WAIT = 100;
	private static final int  WELCOME = 0;
	private static final int  HOME = 1;
	private GifView gifView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
		        WindowManager.LayoutParams.FLAG_FULLSCREEN); 
		setContentView(R.layout.activity_welcome);
		gifView = (GifView) findViewById(R.id.gifview);
		gifView.setGifImage(R.drawable.welcome);
		gifView.setShowDimension(500,500); 
		gifView.setGifImageType(GifImageType.COVER);
		gifView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean isSplash = SPUtils.getFirstShowSplash(WelcomeActivity.this);
				if(isSplash){
					SPUtils.setFirstShowSplash(WelcomeActivity.this, false);
					handler.sendEmptyMessageDelayed(WELCOME, WAIT);
				}else {
					handler.sendEmptyMessageDelayed(HOME, WAIT);
				}
			}
		}); 
		init();
	}
	private  Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case WELCOME:
				gotoGuide();
				break;
            case HOME:
            	gotoHome();
				break;

			default:
				break;
			}
		};
	};
	
	private void init() {
		// TODO Auto-generated method stub
	}
	private void gotoGuide() {
		Intent intent = new Intent();
		intent.setClass(WelcomeActivity.this,GuideActivity.class);
		startActivity(intent);
		finish();
	}
	private void gotoHome() {
		Intent intent = new Intent();
		intent.setClass(WelcomeActivity.this,LauncherActivity.class);
		startActivity(intent);
		finish();
	}
}
