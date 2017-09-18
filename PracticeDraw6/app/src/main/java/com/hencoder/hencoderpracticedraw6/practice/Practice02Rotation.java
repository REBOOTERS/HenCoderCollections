package com.hencoder.hencoderpracticedraw6.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hencoder.hencoderpracticedraw6.R;

public class Practice02Rotation extends RelativeLayout {
    private static final String TAG = "Practice02Rotation";
    Button animateBt;
    ImageView imageView;
    TextView actionTv;

    int animState;

    public Practice02Rotation(Context context) {
        super(context);
    }

    public Practice02Rotation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice02Rotation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();


        animateBt = (Button) findViewById(R.id.animateBt);
        imageView = (ImageView) findViewById(R.id.imageView);
        actionTv = (TextView) findViewById(R.id.action);

        int left = imageView.getLeft();
        int top = imageView.getTop();

        Log.e(TAG, "onSizeChanged: left==" + left);
        Log.e(TAG, "onSizeChanged: top==" + top);

        animateBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                // // TODO 在这里处理点击事件，通过 View.animate().rotation/X/Y() 来让 View 旋转

                switch (animState) {
                    case 0:
                        imageView.animate().rotation(180);
                        actionTv.setText("rotation(100)");
                        break;
                    case 1:
                        imageView.animate().rotation(0);
                        actionTv.setText("rotation(0)");
                        break;
                    case 2:
                        imageView.animate().rotationX(180);
                        actionTv.setText("rotationX(180)");
                        break;
                    case 3:
                        imageView.animate().rotationX(0);
                        actionTv.setText("rotationX(0)");
                        break;
                    case 4:
                        imageView.animate().rotationY(180);
                        actionTv.setText("rotationY(180)");
                        break;
                    case 5:
                        imageView.animate().rotationY(0);
                        actionTv.setText("rotationY(0)");
                        break;


                }
                animState++;
                animState = animState == 6 ? 0 : animState;

            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


    }


}