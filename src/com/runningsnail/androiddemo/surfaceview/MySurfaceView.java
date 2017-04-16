package com.runningsnail.androiddemo.surfaceview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

@SuppressLint("WrongCall")
public class MySurfaceView extends SurfaceView implements Callback,OnTouchListener{
	
	private Path path;
	private Paint paint;

	public MySurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public MySurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	private void init(){
		getHolder().addCallback(this);
		path = new Path();
		paint = new Paint();
		setOnTouchListener(this);
	}
    private void onDraw(){
    	Canvas canvas = getHolder().lockCanvas();
    	canvas.drawColor(Color.WHITE);
    	paint.setColor(Color.RED);
    	paint.setStrokeWidth(5);
    	paint.setAntiAlias(true);
    	paint.setStyle(Style.STROKE);
    	canvas.drawPath(path, paint);
    	getHolder().unlockCanvasAndPost(canvas);
    }
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		onDraw();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
            path.moveTo(event.getX(), event.getY());
            onDraw();
			break;
		case MotionEvent.ACTION_MOVE:
            path.lineTo(event.getX(), event.getY());
            onDraw();
			break;
		default:
			break;
		}
		return true;
	}
    void reset() {
		// TODO Auto-generated method stub
    	path.reset();
    	onDraw();
	}
}
