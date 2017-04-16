package com.runningsnail.androiddemo.eventbus;

import com.runningsnail.androiddemo.R;
import com.tandong.swichlayout.SwichLayoutInterFace;
import com.tandong.swichlayout.SwitchLayout;

import de.greenrobot.event.EventBus;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

@SuppressLint("NewApi")
public class SecondEventbus extends Activity implements SwichLayoutInterFace{

	private Button btn_FirstEvent, btn_SecondEvent, btn_ThirdEvent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secondbus);
		btn_FirstEvent = (Button) findViewById(R.id.btn_first_event);
		btn_SecondEvent = (Button) findViewById(R.id.btn_second_event);
		btn_ThirdEvent = (Button) findViewById(R.id.btn_third_event);

		btn_FirstEvent.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EventBus.getDefault().post(
						new FirstEvent("FirstEvent btn clicked"));
			}
		});
		
		btn_SecondEvent.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EventBus.getDefault().post(
						new SecondEvent("SecondEvent btn clicked"));
			}
		});

		btn_ThirdEvent.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EventBus.getDefault().post(
						new ThirdEvent("ThirdEvent btn clicked"));

			}
		});
		setEnterSwichLayout();

	}
	@Override
	public void setEnterSwichLayout() {
		// TODO Auto-generated method stub
		SwitchLayout.ScaleBig(this, false, null);
	}


	@Override
	public void setExitSwichLayout() {
		// TODO Auto-generated method stub
		SwitchLayout.ScaleBig(this, true, null);
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {// 按返回键时退出Activity的Activity特效动画

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			setExitSwichLayout();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
