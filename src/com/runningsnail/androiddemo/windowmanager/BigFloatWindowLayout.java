package com.runningsnail.androiddemo.windowmanager;

import com.runningsnail.androiddemo.R;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class BigFloatWindowLayout extends LinearLayout{

	
	//static int viewWidth;
	//static int viewHeight;
	public BigFloatWindowLayout(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public BigFloatWindowLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public BigFloatWindowLayout(final Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		View view = LayoutInflater.from(context).inflate(R.layout.float_big, null);
		//viewWidth = view.getLayoutParams().width;
		//viewHeight = view.getLayoutParams().height;
		Button remove = (Button) view.findViewById(R.id.float_remove);
		remove.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MyWindowManager.removeBigWindow(context);
				MyWindowManager.removeSmallWindow(context);
				Intent itIntent = new Intent(context, FloatWindowService.class);
				context.stopService(itIntent);
			}
		});
		Button back = (Button) view.findViewById(R.id.float_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MyWindowManager.removeBigWindow(context);
				MyWindowManager.createSmallWindow(context);
			}
		});
		this.addView(view);
	}
}
