package com.runningsnail.androiddemo.glide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.runningsnail.androiddemo.R;

public class PriorityActivity extends Activity {

    private ImageView imageView1 , imageView2 , imageView3  ;

    String url1 = "http://o91woxvtr.bkt.clouddn.com/load3.jpg" ;
    String url2 = "http://o91woxvtr.bkt.clouddn.com/load2.jpg" ;
    String url3 = "http://o91woxvtr.bkt.clouddn.com/load1.jpg" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority);

        imageView1 = (ImageView) findViewById( R.id.image1 );
        imageView2 = (ImageView) findViewById( R.id.image2 );
        imageView3 = (ImageView) findViewById( R.id.image3 );

        Glide.with( this).load( url1).priority(Priority.LOW ).into( imageView1 ) ;
        Glide.with( this).load( url2).priority(Priority.LOW ).into( imageView2 ) ;
        Glide.with( this).load( url3).priority(Priority.HIGH ).into( imageView3 ) ;

        //   Glide.with(this).load( url3 ).thumbnail(0.1f).into( imageView3 );
    }
}
