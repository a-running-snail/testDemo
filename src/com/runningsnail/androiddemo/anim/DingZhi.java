package com.runningsnail.androiddemo.anim;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.runningsnail.androiddemo.R;

/**
 * Created by admin on 2015/11/29.
 */
@SuppressLint("NewApi")
public class DingZhi extends Activity implements View.OnClickListener {

    private ImageView example, ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showproperty);

        example = (ImageView) findViewById(R.id.example);

        ball = (ImageView) findViewById(R.id.ball);
        findViewById(R.id.p1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        {
            ObjectAnimator anim;
            switch (v.getId()) {
                case R.id.p1:
                    //TODO implement
                    ObjectAnimator.ofFloat(ball, "x", 0, 500)//
                            .setDuration(3000).start();
                    anim = ObjectAnimator.ofFloat(ball, "y", 0, 500)//
                            .setDuration(3000);
                    anim.setEvaluator(new TypeEvaluator<Float>() {
                        @Override
                        public Float evaluate(float fraction, Float startValue, Float endValue) {
                            return startValue + 0.008f * fraction * (endValue - startValue) * fraction * (endValue - startValue);
                        }

                    });
                    anim.start();
                    break;

            }
        }
    }
}
