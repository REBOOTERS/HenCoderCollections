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
        mPaint2.setStrokeWidth(30);
        mPaint2.setStrokeCap(Paint.Cap.ROUND);

        mPaint1.setColor(Color.RED);
        canvas.drawOval(200, 200, 800, 600, mPaint1);

        canvas.drawPoint(200, 200, mPaint2);
        canvas.drawPoint(800, 600, mPaint2);


        canvas.drawArc(200, 200, 800, 600, -30, 90, false, mPaint);
        canvas.drawArc(200, 200, 800, 600, 60, 90, true, mPaint);
    }
}
