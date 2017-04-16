package com.runningsnail.androiddemo.slitemenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

import com.runningsnail.androiddemo.R;

public class SliteMenu extends ViewGroup implements OnClickListener{
   private final int LT = 0;
   private final int LB = 1;
   private final int RT = 2;
   private final int RB = 3;
   private int position;
   private int dimes;
   private View centerView;
   private boolean isClose = true;
   private OnMenuItemClickListener listener;
   
	public SliteMenu(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SliteMenu);
	    position = array.getInt(R.styleable.SliteMenu_position, 0);
	    dimes = (int) array.getDimension(R.styleable.SliteMenu_radius, 100);
	    array.recycle();
	    Log.e("tag", "-------------------position="+position+" dimes="+dimes);
	}

	public SliteMenu(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
	}

	public SliteMenu(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		if (changed) {
			layoutCButton();
			int count = getChildCount();
			for (int i = 0; i < count -1; i++) {
				View view = getChildAt(i+1);
				view.setVisibility(View.GONE);
				int cl = 0;
				int ct = 0;
				int wid = view.getMeasuredWidth();
				int hei = view.getMeasuredHeight();
				int left =(int) (dimes*Math.sin(Math.PI/2/(count-2)*i));
				int top = (int) (dimes*Math.cos(Math.PI/2/(count-2)*i));
				
				switch (position) {
				case LT:
		            cl = left;
		            ct = top;
					break;
				case LB:
					cl = left;
		            ct = getMeasuredHeight()-top-hei;
					break;
				case RT:
					cl = getMeasuredWidth()-left-wid;
					ct = top;
					break;
				case RB:
					cl = getMeasuredWidth()-left-wid;
					ct = getMeasuredHeight()-top-hei;
					break;
				default:
					break;
				}
				view.layout(cl, ct, cl+wid, ct+hei);
			}
			
		}
	}
    
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	   Animation an = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
	   an.setDuration(300);
	   an.setFillAfter(true);
	   v.startAnimation(an);
	   int count = getChildCount();
	   for ( int i = 0; i < count -1; i++) {
		    final View view = getChildAt(i+1);
			view.setVisibility(View.VISIBLE);
			int x = 0;
			int y = 0;
			int left =(int) (dimes*Math.sin(Math.PI/2/(count-2)*i));
			int top = (int) (dimes*Math.cos(Math.PI/2/(count-2)*i));
			
			switch (position) {
			case LT:
	            x = -left;
	            y = -top;
				break;
			case LB:
				x = -left;
	            y = top;
				break;
			case RT:
				x = left;
				y = -top;
				break;
			case RB:
				x = left;
				y = top;
				break;
			default:
				break;
			}
			AnimationSet set = new AnimationSet(true);
			Animation tran;
		    if (isClose) {//去打开
		    	tran = new TranslateAnimation(x, 0, y, 0);
			} else {//去关闭
			    tran = new TranslateAnimation(0, x, 0, y);
			}
		    tran.setDuration(300);
		    tran.setFillAfter(true);
		    tran.setStartOffset(100*i);
		    tran.setAnimationListener(new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationEnd(Animation animation) {
					// TODO Auto-generated method stub
					Log.e("tag", "---------onAnimationEnd---------isClose="+isClose);
					if (isClose) {
						view.setVisibility(View.GONE);
					}
				}
			});
		 // 旋转动画
			RotateAnimation rotateAnim = new RotateAnimation(0, 360,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			rotateAnim.setDuration(300);
			rotateAnim.setFillAfter(true);
			//rotateAnim.setStartOffset(50*i);
			set.addAnimation(rotateAnim);
			set.addAnimation(tran);
			view.startAnimation(set);
			final int pos = i + 1;
			view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (listener != null) {
						listener.onClick(v, pos);
						isClose = isClose?false:true;
						 int count = getChildCount();
						for (int j = 0; j < count-1; j++) {
							View view = getChildAt(j+1);
							AlphaAnimation alphaAnim = new AlphaAnimation(1f, 0.0f);
							alphaAnim.setDuration(300);
							alphaAnim.setFillAfter(true);
							view.startAnimation(alphaAnim);
						}
					}
				}
			});
	   }
	   Log.e("tag", "---------final---------isClose="+isClose);
	   isClose = isClose?false:true;
	   
	}
	private void layoutCButton(){
		centerView = getChildAt(0);
		centerView.setOnClickListener(this);
		int cl=0;
		int ct=0;
		switch (position) {
		case LT:
              cl = 0;
              ct = 0;
			break;
		case LB:
			cl = 0;
            ct = getMeasuredHeight()-centerView.getMeasuredHeight();
			break;
		case RT:
			cl = getMeasuredWidth()-centerView.getMeasuredWidth();
            ct = 0;
			break;
		case RB:
			cl = getMeasuredWidth()-centerView.getMeasuredWidth();
            ct = getMeasuredHeight()-centerView.getMeasuredHeight();
			break;
		default:
			break;
		}
		centerView.layout(cl, ct, cl+centerView.getMeasuredWidth(), ct+centerView.getMeasuredHeight());
	}
	public interface OnMenuItemClickListener{
		void onClick(View view, int pos);
	}
	public void setOnMenuItemClickListener(
			OnMenuItemClickListener mMenuItemClickListener)
	{
		this.listener = mMenuItemClickListener;
	}
}
