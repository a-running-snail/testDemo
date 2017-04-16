package com.runningsnail.androiddemo.popwindow;

import com.runningsnail.androiddemo.R;
import com.tandong.swichlayout.SwichLayoutInterFace;
import com.tandong.swichlayout.SwitchLayout;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity; 
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

public class PopWindows extends Activity implements SwichLayoutInterFace{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poplayout);
		Button start = (Button) findViewById(R.id.start);
		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showPopwindow();
			}

		});
		setEnterSwichLayout();
	}

	

	/**
	 * ��ʾpopupWindow
	 */
	private void showPopwindow() {
		// ����layoutInflater���View
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.popwindowlayout, null);

		// ���������ַ����õ���Ⱥ͸߶� getWindow().getDecorView().getWidth()

		PopupWindow window = new PopupWindow(view,
				WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.WRAP_CONTENT);

		// ����popWindow��������ɵ������仰������ӣ�������true
		window.setFocusable(true);

		// ����Ҫ���������������������popWindow���ⲿ�֣�popWindow������ʧ
		// window.setBackgroundDrawable(new BitmapDrawable());

		// ʵ����һ��ColorDrawable��ɫΪ��͸��
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		window.setBackgroundDrawable(dw);
		// �ڲ��յ�View�ؼ��·���ʾ
		// window.showAsDropDown(MainActivity.this.findViewById(R.id.start));

		// ����popWindow����ʾ����ʧ����
		window.setAnimationStyle(R.style.mypopwindow_anim_style);
		// �ڵײ���ʾ
		window.showAtLocation(PopWindows.this.findViewById(R.id.start),
				Gravity.BOTTOM, 0, 0);

		// �������popWindow���button�Ƿ���Ե��
		Button first = (Button) view.findViewById(R.id.first);
		first.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				System.out.println("��һ����ť�������");
			}
		});

		// popWindow��ʧ��������
		window.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				System.out.println("popWindow��ʧ");
			}
		});

	}
	@Override
	public void setEnterSwichLayout() {
		// TODO Auto-generated method stub
		SwitchLayout.RotateCenterOut(this, false, null);
	}


	@Override
	public void setExitSwichLayout() {
		// TODO Auto-generated method stub
		SwitchLayout.RotateCenterOut(this, true, null);
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {// �����ؼ�ʱ�˳�Activity��Activity��Ч����

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			setExitSwichLayout();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}

