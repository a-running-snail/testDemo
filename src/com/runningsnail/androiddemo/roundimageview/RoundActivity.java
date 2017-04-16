package com.runningsnail.androiddemo.roundimageview;

import com.runningsnail.androiddemo.R;
import com.tandong.swichlayout.SwichLayoutInterFace;
import com.tandong.swichlayout.SwitchLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView.ScaleType;

@SuppressLint("NewApi")
public class RoundActivity extends Activity implements SwichLayoutInterFace{

	
	RoundedImageView iv,iv1,iv2 ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.roundimageviewlay);
		iv=(RoundedImageView) findViewById(R.id.imageView);
        iv1=(RoundedImageView) findViewById(R.id.imageView1);
        iv2=(RoundedImageView) findViewById(R.id.imageView2);
                 //Ô²ï¿½ï¿½
        iv.setScaleType(ScaleType.CENTER_CROP);
       // iv.setCornerRadius(100);
        iv.setImageResource(R.drawable.roundview);
        //Ô²ï¿½ï¿½
        iv1.setScaleType(ScaleType.CENTER_CROP);
        //iv1.setCornerRadius(50);
        iv1.setImageResource(R.drawable.roundview);
        //ï¿½ï¿½Ô²
        //iv2.setCornerRadius(80);
        iv2.setImageResource(R.drawable.roundview);
        setEnterSwichLayout();
		
	}
	@Override
	public void setEnterSwichLayout() {
		// TODO Auto-generated method stub
		SwitchLayout.RotateCenterOut(this, false, null);
	}


	@Override
	public void setExitSwichLayout() {
		// TODO Auto-generated method stub
		SwitchLayout.RotateCenterOut(this, true, null);
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {// °´·µ»Ø¼üÊ±ÍË³öActivityµÄActivityÌØÐ§¶¯»­

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			setExitSwichLayout();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
