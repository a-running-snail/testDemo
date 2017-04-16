package com.runningsnail.androiddemo.lock;

import com.runningsnail.androiddemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LockActivity extends Activity {

	private Button setbtn, testbtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lock);
		setbtn = (Button) findViewById(R.id.set_pwd);
		setbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
              startActivity(new Intent(LockActivity.this, SettingLock.class));
			}
		});
		testbtn = (Button) findViewById(R.id.lock_test);
		testbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(LockActivity.this, LockTestActivity.class));
			}
		});
	}
}
