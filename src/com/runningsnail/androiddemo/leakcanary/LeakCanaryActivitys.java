package com.runningsnail.androiddemo.leakcanary;

import com.runningsnail.androiddemo.R;
import com.runningsnail.androiddemo.application.MyApplication;
import com.squareup.leakcanary.watcher.RefWatcher;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LeakCanaryActivitys extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leakmain);
		Button btButton  = (Button) findViewById(R.id.start);
		btButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startAsyncTask();
			}
		});
		RefWatcher refWatcher = MyApplication.getRefWatcher(this);
	    refWatcher.watch(this);
	}
	 @SuppressLint("NewApi")
	private void startAsyncTask() {
		    // This async task is an anonymous class and therefore has a hidden reference to the outer
		    // class MainActivity. If the activity gets destroyed before the task finishes (e.g. rotation),
		    // the activity instance will leak.
		    new AsyncTask<Void, Void, Void>() {
		      @Override protected Void doInBackground(Void... params) {
		        // Do some slow work in background
		        SystemClock.sleep(20000);
		        return null;
		      }
		    }.execute();
		  }
}
