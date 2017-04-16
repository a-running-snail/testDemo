package com.runningsnail.androiddemo.guaguale;

import com.runningsnail.androiddemo.R;
import com.runningsnail.androiddemo.guaguale.GuaguaView.FinishListener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class GuaguaActivity extends Activity {
	private GuaguaView view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guaguale);
		view = (GuaguaView) findViewById(R.id.guagua);
		view.setListener(new FinishListener() {
			
			@Override
			public void finish() {
				// TODO Auto-generated method stub
				Toast.makeText(GuaguaActivity.this, "发财啦", Toast.LENGTH_SHORT).show();
			}
		});
	}

}
