package com.runningsnail.androiddemo.glide;

import com.runningsnail.androiddemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GlideActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        findViewById(R.id.circle).setOnClickListener(this);
        findViewById(R.id.activity2).setOnClickListener(this);
        findViewById(R.id.priorityActivity).setOnClickListener(this);
        findViewById(R.id.thumbnailActivity).setOnClickListener(this);
        findViewById(R.id.gifActivity).setOnClickListener(this);
        findViewById(R.id.videoActivity).setOnClickListener(this);
        findViewById(R.id.glideModuleActivity).setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.circle:
                startActivity( new Intent( GlideActivity.this , CircleImageActivity.class));
                break;
            case R.id.activity2 :
                startActivity( new Intent( GlideActivity.this , Activity2.class));
                break;
            case R.id.priorityActivity :
                startActivity( new Intent( GlideActivity.this , PriorityActivity.class));
                break;
            case R.id.thumbnailActivity :
                startActivity( new Intent( GlideActivity.this , ThumbnailActivity.class));
                break;
            case R.id.gifActivity :
                startActivity( new Intent( GlideActivity.this , GifActivity.class));
                break;
            case R.id.videoActivity :
                startActivity( new Intent( GlideActivity.this , VideoActivity.class));
                break;
            
        }
    }
}
