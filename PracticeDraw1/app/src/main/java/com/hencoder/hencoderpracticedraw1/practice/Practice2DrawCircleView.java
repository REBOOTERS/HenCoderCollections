package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice2DrawCircleView extends View {

    private Paint mPaint1, mPaint2, mPaint3, mPaint4;


    public Practice2DrawCircleView(Context context) {
        super(context);
        init();
    }


    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        mPaint1 = new Paint();

        mPaint2 = new Paint();
        mPaint2.setStyle(Paint.Style.STROKE);
        mPaint2.setStrokeWidth(5);

        mPaint3 = new Paint();
        mPaint3.setColor(Color.BLUE);

        mPaint4 = new Paint();
        mPaint4.setStyle(Paint.Style.STROKE);
        mPaint4.setStrokeWidth(80);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆

        //
        canvas.drawCircle(300, 160, 160, mPaint1);
        //
        canvas.drawCircle(700, 160, 160, mPaint2);
        //
        canvas.drawCircle(300, 560, 160, mPaint3);
        //
        canvas.drawCircle(700, 560, 160, mPaint4);

    }
}
