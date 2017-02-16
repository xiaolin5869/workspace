package com.example.test2;

import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PixelFormat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private Button mFloatButton;
    WindowManager.LayoutParams mLayoutParams;
    WindowManager mWindowManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.mButton);

        mWindowManager=(WindowManager) getSystemService(Context.WINDOW_SERVICE);

        mFloatButton=new Button(this);
        mFloatButton.setText("Button");

        mLayoutParams=new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                0,0, PixelFormat.TRANSPARENT
                );
        mLayoutParams.flags= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;

        mLayoutParams.gravity= Gravity.LEFT | Gravity.TOP;
        mLayoutParams.x=100;
        mLayoutParams.y=300;

        mWindowManager.addView(mFloatButton,mLayoutParams);

    }

    public void performAnimata(final View target,final int start,final int end) {
//        ViewWrapper wrapper=new ViewWrapper(mButton);
//        ObjectAnimator.ofInt(wrapper,"width",500)
//                .setDuration(2000)
//                .start();

        ValueAnimator valueAnimator=ValueAnimator.ofInt(1,100);//1--100的差值器
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private IntEvaluator mEvaluator=new IntEvaluator();
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
              int currentValue= (int) animation.getAnimatedValue();
                Log.d("tag","current Value="+currentValue);
                //获得当前进度占整个动画过程的比例 浮点型 0--1之间
                float fraction = animation.getAnimatedFraction();
                target.getLayoutParams().width=mEvaluator.evaluate(fraction,start,end);
                target.requestLayout();

            }
        });

        valueAnimator.setDuration(5000).start();
    }

    public void onclick(View view){
        if(view==mButton)
        {
            performAnimata(view,mButton.getWidth(),500);
        }
    }

    private static class ViewWrapper{
        private View mTarget;

        public ViewWrapper(View target) {
            mTarget = target;
        }

        public int getWidth(){
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width){
            mTarget.getLayoutParams().width=width;
            mTarget.requestLayout();
        }
    }


}
