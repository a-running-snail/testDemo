package com.runningsnail.androiddemo.glide;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.runningsnail.androiddemo.R;

public class Activity2 extends Activity {

    String url = "http://o7rvuansr.bkt.clouddn.com/big1.jpg" ;
    ImageView imageView1 , imageView2 , imageView3 ;

    private SimpleTarget target ;
    private ViewGroup.LayoutParams params ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        imageView1 = (ImageView) findViewById( R.id.image1 );
        imageView2 = (ImageView) findViewById( R.id.image2 );
        imageView3 = (ImageView) findViewById( R.id.image3 );
        params = imageView2.getLayoutParams() ;


        //加载第二张图
        findViewById( R.id.bt1 ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Glide.with(Activity2.this).load(R.drawable.wel3).into(imageView1);
            }
        });

        //加载第二张图
        findViewById( R.id.bt2 ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Glide.with(Activity2.this).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView1);
            }
        });

        //加载第三张图
        findViewById( R.id.bt3 ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Glide.with(Activity2.this).load(url).into(target);
            }
        });
        target = new SimpleTarget<Bitmap >(params.width,params.height) {

			@Override
			public void onResourceReady(Bitmap arg0,GlideAnimation<? super Bitmap> arg1) {
				// TODO Auto-generated method stub
				imageView2.setImageBitmap(arg0);
			}
		};
    }
}
