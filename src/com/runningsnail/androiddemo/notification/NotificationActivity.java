package com.runningsnail.androiddemo.notification;

import com.runningsnail.androiddemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class NotificationActivity extends Activity implements OnClickListener{

	private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notica);
		initView();
	}
	
	
	
    private void initView() {
		// TODO Auto-generated method stub
    	btn1 = (Button) findViewById(R.id.notification1);
    	btn2 = (Button) findViewById(R.id.notification2);
    	btn3= (Button) findViewById(R.id.notification3);
    	btn4= (Button) findViewById(R.id.notification4);
    	btn5= (Button) findViewById(R.id.notification5);
    	btn6= (Button) findViewById(R.id.notification6);
    	btn1.setOnClickListener(this);
    	btn2.setOnClickListener(this);
    	btn3.setOnClickListener(this);
    	btn4.setOnClickListener(this);
    	btn5.setOnClickListener(this);
    	btn6.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.notification1:
            startActivity(new Intent(NotificationActivity.this, SimplestNotificationActivity.class));
			break;
		case R.id.notification2:
			startActivity(new Intent(NotificationActivity.this, SimpleNotificationActivity.class));
			break;
		case R.id.notification3:
			startActivity(new Intent(NotificationActivity.this, NotificationEffectActivity.class));
			break;
		case R.id.notification4:
			startActivity(new Intent(NotificationActivity.this, NotificationStyleActivity.class));
			break;
		case R.id.notification5:
			startActivity(new Intent(NotificationActivity.this, TaskStackBuilderActivity.class));
			break;
		case R.id.notification6:
			startActivity(new Intent(NotificationActivity.this, NotificationListenerServiceActivity.class));
			break;
		default:
			break;
		}
	}

}
