package com.runningsnail.androiddemo.view;


import com.runningsnail.androiddemo.R;

import android.app.Activity;
import android.os.Bundle;

public class ChildActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_childview);
	}
}
