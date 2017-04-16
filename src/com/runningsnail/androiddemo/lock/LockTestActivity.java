package com.runningsnail.androiddemo.lock;

import java.util.List;

import com.runningsnail.androiddemo.R;
import com.runningsnail.androiddemo.tools.SPUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class LockTestActivity extends Activity {
	private LockView lockView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locktest);
		lockView = (LockView) findViewById(R.id.lock_test);
		lockView.setOnDrawFinishedListener(new LockView.OnDrawFinishedListener() {

			@Override
			public boolean OnDrawFinished(List<Integer> passList) {
				// TODO Auto-generated method stub
				StringBuilder builder = new StringBuilder();
				Log.e("tag", "-----------setOnDrawFinishedListener-------"+passList.size());
				for (int i = 0; i < passList.size(); i++) {
					builder.append(passList.get(i));
				}
				String pwd = SPUtils.getLockPwd(getApplicationContext());
				Log.e("tag", "--set------pwd=="+pwd+"  builder=="+builder.toString());
				if (pwd.equals(builder.toString())) {
					Toast.makeText(LockTestActivity.this, "正确",Toast.LENGTH_SHORT).show();
					return true;
				} else {
					Toast.makeText(LockTestActivity.this, "错误",Toast.LENGTH_SHORT).show();
					return false;
				}
			}
		});
	}

}
