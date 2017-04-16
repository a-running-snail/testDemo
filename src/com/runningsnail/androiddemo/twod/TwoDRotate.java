package com.runningsnail.androiddemo.twod;

import com.runningsnail.androiddemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class TwoDRotate extends Activity {
	
	private ImageView iv1,iv2;
	private ScaleAnimation al0,al1;
	private FrameLayout rotate_lay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_twod);
		initView();
	}
	private void initView() {
		// TODO Auto-generated method stub
		al0 = new ScaleAnimation(1, 0, 1, 1, RotateAnimation.RELATIVE_TO_PARENT, 0.5f, RotateAnimation.RELATIVE_TO_PARENT, 0.5f);
		al1= new ScaleAnimation(0, 1, 1, 1, RotateAnimation.RELATIVE_TO_PARENT, 0.5f, RotateAnimation.RELATIVE_TO_PARENT, 0.5f); 
		al0.setDuration(500);
		al1.setDuration(500);
		iv1 = (ImageView) findViewById(R.id.rotate_1);
		iv2 = (ImageView) findViewById(R.id.rotate_2);
		rotate_lay = (FrameLayout) findViewById(R.id.rotate_lay);
		showiv1();
		rotate_lay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (iv1.getVisibility() == View.VISIBLE) {
					iv1.startAnimation(al0);
				}else {
					iv2.startAnimation(al0);
				}

			}
		});
		al0.setAnimationListener(new AnimationListener() {
			
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
				if (iv1.getVisibility() == View.VISIBLE) {
					iv1.setAnimation(null);
					showiv2();
					iv2.startAnimation(al1);
				} else {
					iv2.setAnimation(null);
					showiv1();
					iv1.startAnimation(al1);
				}
			}
		});
	}
	private void showiv1(){
		iv1.setVisibility(View.VISIBLE);
		iv2.setVisibility(View.INVISIBLE);
	}
	private void showiv2(){
		iv2.setVisibility(View.VISIBLE);
		iv1.setVisibility(View.INVISIBLE);
	}
}
