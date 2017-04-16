package com.runningsnail.androiddemo.welcome;

import java.util.ArrayList;

import com.runningsnail.androiddemo.R;
import com.tandong.swichlayout.SwichLayoutInterFace;
import com.tandong.swichlayout.SwitchLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class GuideActivity extends Activity implements OnClickListener,
		OnPageChangeListener,SwichLayoutInterFace {

	private ViewPager vPager;
	private Button btButton;
	private ArrayList<ImageView> views;
	private ImageView[] dots;
	private static final int[] ids = new int[] { R.drawable.wel1,
			R.drawable.wel2, R.drawable.wel3, R.drawable.wel4, R.drawable.wel5 };
	private static final int[] dotids = new int[] { R.id.iv1, R.id.iv2, R.id.iv3,R.id.iv4,R.id.iv5 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
		        WindowManager.LayoutParams.FLAG_FULLSCREEN); 
		setContentView(R.layout.activity_guide);
		setEnterSwichLayout();
		init();
		initview();
		vPager.setAdapter(new Myadapter());
		vPager.setOnPageChangeListener(this);
	}


	private void initview() {
		// TODO Auto-generated method stub
		views = new ArrayList<>();
		dots  = new ImageView[ids.length];
		for (int i = 0; i < ids.length; i++) {
			ImageView view = new ImageView(getApplicationContext());
			view.setBackgroundResource(ids[i]);
			views.add(view);
			dots[i]= (ImageView)findViewById(dotids[i]);
			LayoutParams lpParams = new LinearLayout.LayoutParams(20, 20);
			//v_point.setBackgroundResource(R.drawable.unfocus);
			lpParams.leftMargin = 50;
			dots[i].setLayoutParams(lpParams);
			
		}
	}

	private void init() {
		// TODO Auto-generated method stub
		vPager = (ViewPager) findViewById(R.id.vp);
		btButton = (Button) findViewById(R.id.enter);
		btButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.enter:
			startActivity(new Intent(GuideActivity.this, LauncherActivity.class));
			finish();
			break;

		default:
			break;
		}
	}

	private class Myadapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			View view = views.get(position);
			container.addView(view);
			return view;
		}

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		if (arg0 == ids.length - 1) {
			btButton.setVisibility(View.VISIBLE);
		} else {
			btButton.setVisibility(View.GONE);
		}
		for (int i = 0; i < ids.length; i++) {
			if (arg0 == i) {
				dots[i].setImageResource(R.drawable.focus);
			} else {
				dots[i].setImageResource(R.drawable.unfocus);
			}
		}
	}


	@Override
	public void setEnterSwichLayout() {
		// TODO Auto-generated method stub
		SwitchLayout.ScaleBig(this, false, null);
	}


	@Override
	public void setExitSwichLayout() {
		// TODO Auto-generated method stub
		SwitchLayout.ScaleBig(this, true, null);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		setExitSwichLayout();
	}
}
