package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

public class Practice10HistogramView extends View {
    private Paint rectPaint;
    private Paint linePaint;
    private TextPaint textPaint;

    private int left = 100;
    private int top = 80;
    private int xLength = 900;
    private int yLength = 500;
    private int width = 100;
    private int space = 5;

    private float point = xLength / 8.0f;
    private int yBottom = top + yLength;
    private int textPos = top + yLength + 30;

    public Practice10HistogramView(Context context) {
        super(context);

        initPaint();
    }


    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        rectPaint = new Paint();
        rectPaint.setColor(Color.GREEN);
        //
        linePaint = new Paint();
        linePaint.setColor(Color.WHITE);
        linePaint.setStrokeWidth(2);
        //
        textPaint = new TextPaint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(30);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图

        //
        canvas.drawLine(left, top, left, top + yLength, linePaint);
        canvas.drawLine(left, top + yLength, left + xLength, top + yLength, linePaint);

        //
        canvas.drawText("Froyo", left / 2 + point, textPos, textPaint);
        canvas.drawText("GB", left / 2 + point * 2, textPos, textPaint);
        canvas.drawText("ICS", left / 2 + point * 3, textPos, textPaint);
        canvas.drawText("JB", left / 2 + point * 4, textPos, textPaint);
        canvas.drawText("KitKat", left / 2 + point * 5, textPos, textPaint);
        canvas.drawText("L", left / 2 + point * 6, textPos, textPaint);
        canvas.drawText("M", left / 2 + point * 7, textPos, textPaint);

        //
        canvas.drawRect(left + point - width, yBottom - 1, point + width, yBottom, rectPaint);
        canvas.drawRect(left + point * 2 - width, yBottom - 10, point * 2 + width, yBottom, rectPaint);
        canvas.drawRect(left + point * 3 - width, yBottom - 10, point * 3 + width, yBottom, rectPaint);
        canvas.drawRect(left + point * 4 - width, yBottom - 280, point * 4 + width, yBottom, rectPaint);
        canvas.drawRect(left + point * 5 - width, yBottom - 380, point * 5 + width, yBottom, rectPaint);
        canvas.drawRect(left + point * 6 - width, yBottom - 410, point * 6 + width, yBottom, rectPaint);
        canvas.drawRect(left + point * 7 - width, yBottom - 310, point * 7 + width, yBottom, rectPaint);

        //
        textPaint.setTextSize(40);
        canvas.drawText("直方图",500,yBottom+100,textPaint);

    }
}
