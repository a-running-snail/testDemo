package com.runningsnail.androiddemo.banner;

import com.runningsnail.androiddemo.R;
import com.runningsnail.androiddemo.banner.BannerFragment.OnIvClickListener;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;
import android.widget.Toast;

public class BannerActivity extends FragmentActivity {
	private TextView content;
    private BannerFragment fragment;
    private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_banner);
		initView();
		initEvent();
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		if (fragmentManager == null) {
			fragmentManager = getSupportFragmentManager();
		}
		if (fragmentTransaction == null) {
			fragmentTransaction = fragmentManager.beginTransaction();
		}
		if (fragment != null) {
			fragmentTransaction.hide(fragment);
		}
		if (fragment == null) {
			fragment = new BannerFragment();
			fragmentTransaction.add(R.id.banner_contain, fragment);
		}else {
			fragmentTransaction.show(fragment);
		}
		fragmentTransaction.commit();
		fragment.setOnIvClickListener(new OnIvClickListener() {
			
			@Override
			public void onClick(int pos, String content) {
				// TODO Auto-generated method stub
				Toast.makeText(BannerActivity.this, content, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void display(int pos, String ct) {
				// TODO Auto-generated method stub
				content.setText(ct);
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		content = (TextView) findViewById(R.id.banner_content);
	}
	
}
