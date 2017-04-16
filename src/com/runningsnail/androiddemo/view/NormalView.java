package com.runningsnail.androiddemo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

@SuppressLint("NewApi")
public class NormalView extends View {
	private Paint paint;
    
	public NormalView(Context context) {
		super(context);
		init();
		// TODO Auto-generated constructor stub
	}

	public NormalView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		// TODO Auto-generated constructor stub
	}
    private void init(){
    	paint = new Paint();
    	paint.setAntiAlias(true);
    	paint.setTextSize(10);
    	paint.setColor(Color.RED);
    	paint.setStrokeWidth(2);
    }
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawColor(Color.GREEN);
		canvas.drawText("奔跑的蜗牛", 10, 60, paint);
		canvas.drawLine(10, 90, 54, 90, paint);
		canvas.drawRect(10, 100, 54, 140,paint);
		canvas.drawRoundRect(10, 150, 54, 190, 5, 5, paint);
		canvas.drawCircle(32, 220, 20, paint);
		canvas.drawArc(10, 270, 54, 314, 0, 90, false, paint);
		canvas.drawArc(10, 360, 54, 404, 0, 90, true, paint);
	}
}
