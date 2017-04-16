package com.runningsnail.androiddemo.view;

import com.runningsnail.androiddemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyViewActivity extends Activity {
	private Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myview);
		btn = (Button) this.findViewById(R.id.view1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MyViewActivity.this, ChildActivity.class));
			}
		});
	}

}
