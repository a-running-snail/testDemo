package com.runningsnail.androiddemo.slitemenu;

import com.runningsnail.androiddemo.R;
import com.runningsnail.androiddemo.slitemenu.SliteMenu.OnMenuItemClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SliteActivity extends Activity {
	private SliteMenu lt,lb,rt,rb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slitemwnu);
		initView();
	}
	private void initView() {
		// TODO Auto-generated method stub
		lt = (SliteMenu) findViewById(R.id.lt);
		lb = (SliteMenu) findViewById(R.id.lb);
		rt = (SliteMenu) findViewById(R.id.rt);
		rb = (SliteMenu) findViewById(R.id.rb);
		lt.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public void onClick(View view, int pos) {
				// TODO Auto-generated method stub
				Toast.makeText(SliteActivity.this, "" + pos, Toast.LENGTH_SHORT)
						.show();
			}
		});
		lb.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public void onClick(View view, int pos) {
				// TODO Auto-generated method stub
				Toast.makeText(SliteActivity.this, "" + pos, Toast.LENGTH_SHORT)
						.show();
			}
		});
		rt.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public void onClick(View view, int pos) {
				// TODO Auto-generated method stub
				Toast.makeText(SliteActivity.this, "" + pos, Toast.LENGTH_SHORT)
						.show();
			}
		});
		rb.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public void onClick(View view, int pos) {
				// TODO Auto-generated method stub
				Toast.makeText(SliteActivity.this, "" + pos, Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

}
