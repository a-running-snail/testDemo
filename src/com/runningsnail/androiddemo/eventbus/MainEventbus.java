package com.runningsnail.androiddemo.eventbus;

import com.runningsnail.androiddemo.R;
import com.tandong.swichlayout.SwichLayoutInterFace;
import com.tandong.swichlayout.SwitchLayout;

import de.greenrobot.event.EventBus;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainEventbus extends Activity implements SwichLayoutInterFace{
	

	Button btn;
	TextView tv;
	EventBus eventBus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainbus);

		EventBus.getDefault().register(this);

		btn = (Button) findViewById(R.id.btn_try);

		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						SecondEventbus.class);
				startActivity(intent);
			}
		});
		setEnterSwichLayout();
	}

	public void onEventMainThread(FirstEvent event) {

		Log.d("harvic", "onEventMainThread�յ�����Ϣ��" + event.getMsg());
	}

	//SecondEvent���պ���һ
	public void onEventMainThread(SecondEvent event) {

		Log.d("harvic", "onEventMainThread�յ�����Ϣ��" + event.getMsg());
	}
	//SecondEvent���պ�����
	public void onEventBackgroundThread(SecondEvent event){
		Log.d("harvic", "onEventBackground�յ�����Ϣ��" + event.getMsg());
	}
	//SecondEvent���պ�����
	public void onEventAsync(SecondEvent event){
		Log.d("harvic", "onEventAsync�յ�����Ϣ��" + event.getMsg());
	}

	public void onEvent(ThirdEvent event) {
		Log.d("harvic", "OnEvent�յ�����Ϣ��" + event.getMsg());
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		EventBus.getDefault().unregister(this);
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
	public boolean onKeyDown(int keyCode, KeyEvent event) {// �����ؼ�ʱ�˳�Activity��Activity��Ч����

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			setExitSwichLayout();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
