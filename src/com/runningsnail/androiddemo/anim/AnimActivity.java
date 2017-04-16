package com.runningsnail.androiddemo.anim;

import com.runningsnail.androiddemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AnimActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);

        findViewById(R.id.showmain).setOnClickListener(this);
        findViewById(R.id.showtween).setOnClickListener(this);
        findViewById(R.id.alltween).setOnClickListener(this);
        findViewById(R.id.showdrawableanim).setOnClickListener(this);
        findViewById(R.id.interpolatershow).setOnClickListener(this);
        findViewById(R.id.tweendrawableanim).setOnClickListener(this);
        findViewById(R.id.propertyanim).setOnClickListener(this);
        findViewById(R.id.propertydef).setOnClickListener(this);
        findViewById(R.id.viewproperty).setOnClickListener(this);
        findViewById(R.id.layoutanim).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.showmain:
                //TODO implement
                startActivity(new Intent(AnimActivity.this, ShowMain.class));
                break;
            case R.id.showtween:
                //TODO implement
                startActivity(new Intent(AnimActivity.this, ShowTween.class));
                break;
            case R.id.alltween:
                //TODO implement
                startActivity(new Intent(AnimActivity.this, AllTween.class));
                break;
            case R.id.showdrawableanim:
                startActivity(new Intent(AnimActivity.this, ShowDrawableAnim.class));
                break;
            case R.id.interpolatershow:
                startActivity(new Intent(AnimActivity.this, TestInterpolator.class));
                break;
            case R.id.tweendrawableanim:
                startActivity(new Intent(AnimActivity.this, ShowExample.class));
                break;
            case R.id.propertyanim:
                startActivity(new Intent(AnimActivity.this, ShowProperty.class));
                break;
            case R.id.propertydef:
                startActivity(new Intent(AnimActivity.this, DingZhi.class));
                break;
            case R.id.viewproperty:
                startActivity(new Intent(AnimActivity.this, ViewAnimateActivity.class));
                break;
            case R.id.layoutanim:
                startActivity(new Intent(AnimActivity.this, LayoutAnimaActivity.class));
                break;


        }
    }
}
