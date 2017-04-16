package com.runningsnail.androiddemo.surfaceview;

import com.runningsnail.androiddemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SurfaceViewActivity extends Activity {
	
	private MySurfaceView surfaceView;
	private Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_surfaceview);
		surfaceView = (MySurfaceView) findViewById(R.id.mysurface);
		btn = (Button) findViewById(R.id.reset);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				surfaceView.reset();
			}
		});
	}
}
