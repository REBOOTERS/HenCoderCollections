package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

public class Practice11PieChartView extends View {

    private Paint redPaint, yellowPaint, bluePaint, greenPaint, grayPaint, purplePaint;
    private TextPaint mTextPaint;

    //
    private Paint helpPaint;


    public Practice11PieChartView(Context context) {
        super(context);
        initPaint();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        //
        helpPaint = new Paint();
        helpPaint.setStyle(Paint.Style.STROKE);
        helpPaint.setStrokeWidth(5);


        redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setColor(Color.RED);
        yellowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        yellowPaint.setColor(Color.YELLOW);
        greenPaint = new Paint();
        greenPaint.setColor(Color.GREEN);
        bluePaint = new Paint();
        bluePaint.setColor(Color.BLUE);
        purplePaint = new Paint();
        purplePaint.setColor(Color.CYAN);
        grayPaint = new Paint();
        grayPaint.setColor(Color.GRAY);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图

        canvas.drawRect(120, 120, 700, 700, helpPaint);

        //
        canvas.drawArc(100, 100, 680, 680, -180, 120, true, redPaint);
        canvas.drawArc(120, 120, 700, 700, -60, 60, true, yellowPaint);
        canvas.drawArc(120, 120, 700, 700, 0, 20, true, purplePaint);
        canvas.drawArc(120, 120, 700, 700, 20, 20, true, grayPaint);
        canvas.drawArc(120, 120, 700, 700, 40, 40, true, greenPaint);
        canvas.drawArc(120, 120, 700, 700, 80, 100, true, bluePaint);

    }
}
