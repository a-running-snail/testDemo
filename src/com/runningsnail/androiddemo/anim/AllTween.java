package com.runningsnail.androiddemo.anim;

import com.runningsnail.androiddemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


/**
 * Created by admin on 2015/11/15.
 */
public class AllTween extends Activity {
    private ImageView example;

    private Button go;
    private AnimationSet animationSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alltween);
        findview();
    }

    private void findview() {
        example = (ImageView) this.findViewById(R.id.example);

        go = (Button) this.findViewById(R.id.go);

        animationSet = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.animset);

        Animation animation = new ThreeDRotateAnimation(0, 360, 0, 0, 100, 1);
        animation.setDuration(3000);
        animation.setStartOffset(6000);
        animationSet.addAnimation(animation);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                example.startAnimation(animationSet);
                v.startAnimation(animationSet);
            }
        });
    }
}
