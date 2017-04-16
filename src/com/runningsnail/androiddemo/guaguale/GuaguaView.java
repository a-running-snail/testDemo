package com.runningsnail.androiddemo.guaguale;

import com.runningsnail.androiddemo.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GuaguaView extends View implements OnTouchListener{

	private Path path;
	private Paint textpaint;
	private Paint backpaint;
	private String str = "恭喜中了100万";
	private Rect rect = new Rect();
	private Bitmap bitmap;
	private Canvas mCanvas;
	private boolean isComplete = false;
	private FinishListener listener;
	
	public GuaguaView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public GuaguaView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		inittextpaint();
		initbackpaint();
		setOnTouchListener(this);
	}

	public GuaguaView(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}
	public void setListener(FinishListener listener) {
		this.listener = listener;
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = getMeasuredWidth();
		int height = getMeasuredHeight();
		bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		mCanvas = new Canvas(bitmap);
		//mCanvas.drawColor(Color.DKGRAY);
		backpaint.setStyle(Style.FILL);
		backpaint.setColor(Color.DKGRAY);
		mCanvas.drawRoundRect(new RectF(0, 0, width, height), 20, 20, backpaint);
		mCanvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.s_title), 
				null, new RectF(0, 0, width, height), backpaint);
		
		
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawText(str, getWidth()/2-rect.width()/2,
				getHeight()/2-rect.height(), textpaint);
		if (!isComplete) {
			drawpath();
			canvas.drawBitmap(bitmap, 0, 0, null);
		}else {
			if (listener != null) {
				listener.finish();
			}
		}
		
	}

	private void drawpath() {
		// TODO Auto-generated method stub
		backpaint.setStyle(Style.STROKE);
		backpaint.setColor(Color.WHITE);
		backpaint.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
		mCanvas.drawPath(path, backpaint);
	}

	private void inittextpaint() {
		path = new Path();
		textpaint = new Paint();
		textpaint.setAntiAlias(true);
		textpaint.setColor(Color.BLACK);
		textpaint.setStyle(Style.FILL);
		textpaint.setTextSize(30);
		textpaint.getTextBounds(str, 0, str.length(), rect);
	}
	private void initbackpaint() {
		backpaint = new Paint();
		backpaint.setAntiAlias(true);
		backpaint.setColor(Color.WHITE);
		backpaint.setStyle(Style.STROKE);
		backpaint.setDither(true);
		backpaint.setStrokeCap(Paint.Cap.ROUND);
		backpaint.setStrokeJoin(Paint.Join.ROUND);
		backpaint.setStrokeWidth(20);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
            path.moveTo(event.getX(), event.getY());
			break;
		case MotionEvent.ACTION_MOVE:
			 path.lineTo(event.getX(), event.getY());
			break;
		case MotionEvent.ACTION_UP:
            new Thread(mRunnable).start();
			break;

		default:
			break;
		}
		invalidate();
		return true;
	}
	private Runnable mRunnable = new Runnable()
	{
		private int[] mPixels;

		@Override
		public void run()
		{

			int w = getWidth();
			int h = getHeight();

			float wipeArea = 0;
			float totalArea = w * h;

			Bitmap bm = bitmap;

			mPixels = new int[w * h];

			/**
			 * 拿到所有的像素信息
			 */
			bm.getPixels(mPixels, 0, w, 0, 0, w, h);

			/**
			 * 遍历统计擦除的区域
			 */
			for (int i = 0; i < w; i++)
			{
				for (int j = 0; j < h; j++)
				{
					int index = i + j * w;
					if (mPixels[index] == 0)
					{
						wipeArea++;
					}
				}
			}

			/**
			 * 根据所占百分比，进行一些操作
			 */
			if (wipeArea > 0 && totalArea > 0)
			{
				int percent = (int) (wipeArea * 100 / totalArea);

				if (percent > 70)
				{
					Log.e("TAG", "清除区域达到70%，下面自动清除");
					isComplete = true;
					postInvalidate();
				}
			}
		}

	};
	
	public interface FinishListener{
		void finish();
	}
	
}
