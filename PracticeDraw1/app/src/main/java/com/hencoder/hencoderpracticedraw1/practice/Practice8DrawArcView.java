package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {

    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形

        Paint mPaint = new Paint();
        Paint mPaint1 = new Paint();
        Paint mPaint2 = new Paint();

        mPaint2.setStrokeWidth(10);
        mPaint2.setStrokeCap(Paint.Cap.ROUND);

        Paint mPaint3 = new Paint();
        mPaint3.setColor(Color.GREEN);

        mPaint1.setColor(Color.RED);
        canvas.drawRect(200, 100, 800, 500, mPaint3);
        canvas.drawOval(200, 100, 800, 500, mPaint1);

        canvas.drawPoint(200, 100, mPaint2);
        canvas.drawPoint(800, 500, mPaint2);
        canvas.drawPoint(500, 400, mPaint2);
        canvas.drawLine(200, 300, 800, 300, mPaint2);
        canvas.drawLine(500, 100, 500, 500, mPaint2);


        canvas.drawArc(200, 100, 800, 500, -110, 100, true, mPaint); // 绘制扇形
        canvas.drawArc(200, 100, 800, 500, 20, 140, false, mPaint); // 绘制弧形
        mPaint.setStyle(Paint.Style.STROKE); // 画线模式
        canvas.drawArc(200, 100, 800, 500, 180, 60, false, mPaint); // 绘制不封口的弧形
    }
}
