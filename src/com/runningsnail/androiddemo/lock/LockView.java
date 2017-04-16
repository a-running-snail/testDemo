package com.runningsnail.androiddemo.lock;

import java.util.ArrayList;
import java.util.List;

import com.runningsnail.androiddemo.R;
import com.runningsnail.androiddemo.tools.SPUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class LockView extends View{
     
	
	private Point[][] pointList ;
	private Bitmap normal,press,error ;
	private Paint paint,pressPoint,errorPoint;
	private int radius;
	private int[] result;
	private boolean isFirst = true,isDraw = false;
	private float touch_x,touch_y;
	private ArrayList<Point> selectPoints;
	private ArrayList<Integer> selectIndex;
	private OnDrawFinishedListener listener;
	
	public LockView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public LockView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if (isFirst) {
			init();
			isFirst = false;
		}
		drawpoints(canvas);
		if (selectPoints.size() > 0) {
			Point a = selectPoints.get(0);
			for (int i = 1; i < selectPoints.size(); i++) {
				drawline(canvas, a, selectPoints.get(i));
				a = selectPoints.get(i);
			}
			if (isDraw) {
				drawline(canvas, a, new Point(touch_x, touch_y));
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		touch_x = event.getX();
		touch_y = event.getY();
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			resetPoints();
            result = isPoint(new Point(touch_x, touch_y));
            if(result != null){
            	//Log.e("tag", "-----------ACTION_DOWN-------result != null-----");
            	isDraw = true;
            	if (!selectPoints.contains(pointList[result[0]][result[1]])) {
            		Log.e("tag", "-----------ACTION_DOWN-------result != null----contains-");
            		pointList[result[0]][result[1]].state = Point.PRESS;
            		selectPoints.add(pointList[result[0]][result[1]]);
            		selectIndex.add(result[0]+3*result[1]);
				}
            }
			break;
		case MotionEvent.ACTION_MOVE:
			 result = isPoint(new Point(touch_x, touch_y));
			// Log.e("tag", "-----------ACTION_MOVE-------result-----");
            if(result != null && isDraw){
            	//Log.e("tag", "-----------ACTION_MOVE-------result != null-----");
            	if (!selectPoints.contains(pointList[result[0]][result[1]])) {
            		//Log.e("tag", "-----------ACTION_MOVE-------result != null----!contains-");
            		pointList[result[0]][result[1]].state = Point.PRESS;
            		selectPoints.add(pointList[result[0]][result[1]]);
            		selectIndex.add(result[0]+3*result[1]);
				}
            }
			break;
		case MotionEvent.ACTION_UP:
             if (listener != null) {
            	boolean bool =  listener.OnDrawFinished(selectIndex);
            	if (!bool) {//错误
					for (int i = 0; i < selectPoints.size(); i++) {
						selectPoints.get(i).state = Point.ERROR;
					}
				}
			}
             isDraw = false;
			break;
		default:
			break;
		}
		this.postInvalidate();
		return true;
	}
	
	private void drawpoints(Canvas canvas) {
		// TODO Auto-generated method stub
		for (int i = 0; i < pointList.length; i++) {
			for (int j = 0; j < pointList[i].length; j++) {
				if (pointList[i][j].state == Point.NORMAL) {
					canvas.drawBitmap(normal, pointList[i][j].x - radius, pointList[i][j].y - radius, paint);
				}else if (pointList[i][j].state == Point.PRESS) {
					canvas.drawBitmap(press, pointList[i][j].x - radius, pointList[i][j].y - radius, paint);
				}else if (pointList[i][j].state == Point.ERROR) {
					canvas.drawBitmap(error, pointList[i][j].x - radius, pointList[i][j].y - radius, paint);
				}
			}
		}
	}
    private void drawline(Canvas canvas,Point a,Point b){
    		if (a.state == Point.PRESS) {
    			canvas.drawLine(a.x, a.y, b.x, b.y, pressPoint);
			}else if (a.state == Point.ERROR) {
				canvas.drawLine(a.x, a.y, b.x, b.y, errorPoint);
			}
    }
	private void init() {
		// TODO Auto-generated method stub
		int width = getWidth();
		int height = getHeight();
		int space,offsetx,offsety;
		selectPoints = new ArrayList<>();
		selectIndex  = new ArrayList<>();
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		pressPoint = new Paint();
		pressPoint.setColor(Color.YELLOW);
		pressPoint.setStrokeWidth(5);
		errorPoint = new Paint();
		errorPoint.setColor(Color.RED);
		errorPoint.setStrokeWidth(5);
		normal = BitmapFactory.decodeResource(getResources(), R.drawable.normal);
		press = BitmapFactory.decodeResource(getResources(), R.drawable.press);
		error = BitmapFactory.decodeResource(getResources(), R.drawable.error);
		radius = normal.getHeight()/2;
		if (width< height) {//竖屏
			offsetx = 0;
			offsety = (height - width)/2;
			space = width/4;
		}else {//横屏
			offsety = 0;
			offsetx = (width - height)/2;
			space = height/4;
		}
		pointList = new Point[3][3];//初始化
		pointList[0][0] = new Point(offsetx+space,offsety+space);
		pointList[1][0] = new Point(offsetx+2*space,offsety+space);
		pointList[2][0] = new Point(offsetx+3*space,offsety+space);
		
		pointList[0][1] = new Point(offsetx+space,offsety+2*space);
		pointList[1][1] = new Point(offsetx+2*space,offsety+2*space);
		pointList[2][1] = new Point(offsetx+3*space,offsety+2*space);
		
		pointList[0][2] = new Point(offsetx+space,offsety+3*space);
		pointList[1][2] = new Point(offsetx+2*space,offsety+3*space);
		pointList[2][2] = new Point(offsetx+3*space,offsety+3*space);
	}
	private int[] isPoint(Point touchPoint){
		for (int i = 0; i < pointList.length; i++) {
			for (int j = 0; j < pointList[i].length; j++) {
				double dis = pointList[i][j].distance(touchPoint);
				if (dis < radius) {
					int[] result = new int[2];
					result[0] = i;
					result[1] = j;
					return result;
				}
			}
		}
		return null;
	}
	public interface  OnDrawFinishedListener{
		 boolean OnDrawFinished(List<Integer> passList);
	}
	void setOnDrawFinishedListener(OnDrawFinishedListener listener){
		this.listener = listener;
	}
	public void resetPoints()
    {
		selectPoints.clear();
		selectIndex.clear();
		//SPUtils.setLockPwd(getContext(), "");
        for (int i = 0; i < pointList.length; i++)
        {
            for (int j = 0; j < pointList[i].length; j++)
            {
            	pointList[i][j].state = Point.NORMAL;
            }
        }
        this.postInvalidate();
    }
}
