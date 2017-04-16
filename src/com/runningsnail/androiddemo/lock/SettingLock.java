package com.runningsnail.androiddemo.lock;

import java.util.List;

import com.runningsnail.androiddemo.R;
import com.runningsnail.androiddemo.tools.SPUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SettingLock extends Activity implements OnClickListener{
	
	private Button resetbtn,savebtn;
	private LockView lockView ;
	private List<Integer> indexList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initView();
	
	}
	private void initView() {
		// TODO Auto-generated method stub
		resetbtn = (Button) findViewById(R.id.reset);
		savebtn =  (Button) findViewById(R.id.save);
		lockView = (LockView) findViewById(R.id.lock_set);
		lockView.setOnDrawFinishedListener(new LockView.OnDrawFinishedListener() {
			
			@Override
			public boolean OnDrawFinished(List<Integer> passList) {
				// TODO Auto-generated method stub
				if (passList.size() < 3) {
					Toast.makeText(SettingLock.this, "密码长度不能少于三个", Toast.LENGTH_SHORT).show();
					return false;
				}else {
					indexList = passList;
					return true;
				}
			}
		});
		resetbtn.setOnClickListener(this);
		savebtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.reset:
			lockView.resetPoints();
			break;
		case R.id.save:
             saveData();
			break;
		default:
			break;
		}
	}
	private void saveData() {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < indexList.size(); i++) {
			builder.append(indexList.get(i));
		}
		SPUtils.setLockPwd(getApplicationContext(), builder.toString());
		Toast.makeText(SettingLock.this, "保存完成", Toast.LENGTH_SHORT).show();
	}
}
