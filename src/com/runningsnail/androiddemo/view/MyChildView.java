package com.runningsnail.androiddemo.view;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;

public class MyChildView extends BaseView{
	
	private Paint paint = new Paint();
	private RectF rectF = new RectF(0, 200, 200, 400);
	private Random rand = new Random();
    private float x = 0;
    private float radius = 0;
	public MyChildView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyChildView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawview(Canvas canvas) {
		// TODO Auto-generated method stub
		Log.e("tag", "-------draw-------x="+x+"   radius="+radius);
		paint.setTextSize(80);
		canvas.drawColor(Color.WHITE);
		canvas.drawText("奔跑的蜗牛", x, 80, paint);
		canvas.drawArc(rectF, 0, radius, true, paint);
	}

	@Override
	public void logic() {
		// TODO Auto-generated method stub
		x+=3;
		if (x >= getWidth()) {
			x = 0 - paint.measureText("奔跑的蜗牛");
		}
		radius++;
		if (radius >= 360) {
			radius = 0;
		}
		Log.e("tag", "-------logic--------x="+x+"   radius="+radius);
		int r = rand.nextInt(256);
		int g = rand.nextInt(256);
		int b = rand.nextInt(256);
		
		paint.setARGB(255, r, g, b);
	}

}
