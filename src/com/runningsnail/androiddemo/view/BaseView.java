package com.runningsnail.androiddemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public abstract class BaseView extends View {
	
	private MyThread thread;
	private boolean running = true;
	public BaseView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public BaseView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public abstract void drawview(Canvas canvas);
	public abstract void logic();
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//super.onDraw(canvas);
		if (thread == null) {
			Log.e("tag", "-------onDraw----thread == null----running="+running);
			thread = new MyThread();
			thread.start();
		}
		drawview(canvas);
	}
	class MyThread  extends Thread {

		@Override
		public void run() {
			Log.e("tag", "-------run()--------running="+running);
			// TODO Auto-generated method stub
			while (running) {
				logic();
				postInvalidate();
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	@Override
	protected void onDetachedFromWindow() {
		running = false;
		super.onDetachedFromWindow();
	}
}
