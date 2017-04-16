package com.runningsnail.androiddemo.glide;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.runningsnail.androiddemo.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class CircleImageActivity extends Activity implements OnClickListener{

	private RequestManager glideRequest;
	private ImageView imageView;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_circleimage);
		imageView = (ImageView) findViewById(R.id.imageView);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        glideRequest = Glide.with(this);
        context = this ;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
        case R.id.button:
            glideRequest.load("https://www.baidu.com/img/bdlogo.png").placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into(imageView);
            break;
        case R.id.button2:
            glideRequest.load("https://www.baidu.com/img/bdlogo.png").transform(new GlideRoundTransform( context)).into(imageView);
            break;
        case R.id.button3:
            glideRequest.load("https://www.baidu.com/img/bdlogo.png").transform(new GlideRoundTransform(context, 16 )).into(imageView);
            break;
        case R.id.button4:
            glideRequest.load("https://www.baidu.com/img/bdlogo.png").transform(new GlideCircleTransform(context)).into(imageView);
            break;
    }
	}

}
