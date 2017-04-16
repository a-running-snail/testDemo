package com.runningsnail.androiddemo.glide;

import java.io.File;

import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.runningsnail.androiddemo.R;

/**
 * 只能加载本地视频，只能加载一个图片，不能播放
 * 网络视频无法加载
 */

public class VideoActivity extends FragmentActivity {
    private ImageView imageView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        imageView = (ImageView) findViewById( R.id.image_video );
        String path = Environment.getExternalStoragePublicDirectory(    
				Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
		File file = new File(path+File.separator+"test.mp4");
        Glide.with( this ).load( file ).into( imageView ) ;
    }
}
