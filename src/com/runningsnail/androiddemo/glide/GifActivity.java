package com.runningsnail.androiddemo.glide;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.runningsnail.androiddemo.R;

public class GifActivity extends Activity {

    String url = "http://o91woxvtr.bkt.clouddn.com/liuyan.gif" ;
    private ImageView imageView1 , imageView2  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        imageView1 = (ImageView) findViewById( R.id.image_gif1 ) ;
        imageView2 = (ImageView) findViewById( R.id.image_gif2 ) ;

        Glide.with( this ).load( R.drawable.welcome ).asBitmap().into( imageView1 ) ;
        Glide.with( this ).load( R.drawable.welcome ).asGif().into( imageView2 ) ;
    }
}
