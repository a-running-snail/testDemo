package com.runningsnail.androiddemo.anim;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.runningsnail.androiddemo.R;

/**
 * Created by admin on 2015/11/30.
 */
@SuppressLint("NewApi")
public class ViewAnimateActivity extends Activity implements View.OnClickListener {

    private ImageView example, ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showproperty);

        example = (ImageView) findViewById(R.id.example);

        ball = (ImageView) findViewById(R.id.ball);
        findViewById(R.id.p1).setOnClickListener(this);
        findViewById(R.id.p2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.p1:
                example.animate().alpha(0).y(100).rotationX(360).scaleX(0.5f).scaleY(0.5f).setDuration(1000).start();
                break;
            case R.id.p2:
                example.animate().alpha(1).start();
                break;

        }

    }
}
