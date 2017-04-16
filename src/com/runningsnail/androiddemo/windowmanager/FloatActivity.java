package com.runningsnail.androiddemo.windowmanager;

import com.runningsnail.androiddemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FloatActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_float);
		Button btnButton = (Button) findViewById(R.id.float_open);
		btnButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(FloatActivity.this,
						FloatWindowService.class);
				startService(it);
				finish();
			}
		});
	}

}
