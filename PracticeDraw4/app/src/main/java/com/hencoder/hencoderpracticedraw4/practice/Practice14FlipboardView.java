package com.hencoder.hencoderpracticedraw4.practice;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice14FlipboardView extends View {
    private static final String TAG = "Practice14FlipboardView";

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Camera camera = new Camera();


    int mDegreeRight;
    int mDegreeLeft;
    int mDegreeUp;
    int mDegreeDown;


    ObjectAnimator mRightAnim = ObjectAnimator.ofInt(this, "degreeRight", 0, -40, 0);

    ObjectAnimator mLeftAnim = ObjectAnimator.ofInt(this, "degreeLeft", 0, 40, 0);

    ObjectAnimator mUpAnim = ObjectAnimator.ofInt(this, "degreeUp", 0, -40, 0);

    ObjectAnimator mDownAnim = ObjectAnimator.ofInt(this, "degreeDown", 0, 40);






    AnimatorSet mAnimatorSet = new AnimatorSet();


    private int centerX;
    private int centerY;
    private int drawX;
    private int drawY;
    private int viewWidth;
    private int viewHeight;


    //
    private Rect mLeftRect, mRightRect, mUpRect, mDownRect;


    public Practice14FlipboardView(Context context) {
        super(context);
    }

    public Practice14FlipboardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice14FlipboardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.setDuration(3000);


        mAnimatorSet.playSequentially(mRightAnim, mUpAnim, mLeftAnim, mDownAnim);


    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
//        mAnimatorSet.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAnimatorSet.end();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!mAnimatorSet.isRunning()) {
            mAnimatorSet.start();
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.save();
        canvas.clipRect(mRightRect);
        camera.save();
        camera.rotateY(mDegreeRight);
        canvas.translate(centerX, centerY);
        camera.applyToCanvas(canvas);
        canvas.translate(-centerX, -centerY);
        camera.restore();
        canvas.drawBitmap(bitmap, drawX, drawY, paint);
        canvas.restore();


        canvas.save();
        canvas.clipRect(mUpRect);
        camera.save();
        camera.rotateX(mDegreeUp);
        canvas.translate(centerX, centerY);
        camera.applyToCanvas(canvas);
        canvas.translate(-centerX, -centerY);
        camera.restore();
        canvas.drawBitmap(bitmap, drawX, drawY, paint);
        canvas.restore();

        canvas.save();
        canvas.clipRect(mLeftRect);
        camera.save();
        camera.rotateY(mDegreeLeft);
        canvas.translate(centerX, centerY);
        camera.applyToCanvas(canvas);
        canvas.translate(-centerX, -centerY);
        camera.restore();
        canvas.drawBitmap(bitmap, drawX, drawY, paint);
        canvas.restore();
//
        canvas.save();
        canvas.clipRect(mDownRect);
        camera.save();
        camera.rotateX(mDegreeDown);
        canvas.translate(centerX, centerY);
        camera.applyToCanvas(canvas);
        canvas.translate(-centerX, -centerY);
        camera.restore();
        canvas.drawBitmap(bitmap, drawX, drawY, paint);
        canvas.restore();


    }

    public void setDegreeRight(int degreeRight) {
        mDegreeRight = degreeRight;
        mUpRect.set(0, 0, 0, 0);
        mDownRect.set(0, 0, 0, 0);
        mLeftRect = new Rect(0, 0, centerX, viewHeight);
        mRightRect = new Rect(centerX, 0, viewWidth, viewHeight);
        invalidate();
    }

    public void setDegreeLeft(int degreeLeft) {
        mDegreeLeft = degreeLeft;
        mUpRect.set(0, 0, 0, 0);
        mDownRect.set(0, 0, 0, 0);
        mLeftRect = new Rect(0, 0, centerX, viewHeight);
        mRightRect = new Rect(centerX, 0, viewWidth, viewHeight);
        invalidate();
    }

    public void setDegreeUp(int degreeUp) {
        mDegreeUp = degreeUp;
        mLeftRect.set(0, 0, 0, 0);
        mRightRect.set(0, 0, 0, 0);
        mUpRect = new Rect(0, 0, viewWidth, centerY);
        mDownRect = new Rect(0, centerY, viewWidth, viewHeight);
        invalidate();
    }

    public void setDegreeDown(int degreeDown) {
        mDegreeDown = degreeDown;
        mLeftRect.set(0, 0, 0, 0);
        mRightRect.set(0, 0, 0, 0);
        mUpRect = new Rect(0, 0, viewWidth, centerY);
        mDownRect = new Rect(0, centerY, viewWidth, viewHeight);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
        int mBitmapWidth = bitmap.getWidth();
        int mBitmapHeight = bitmap.getHeight();
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        drawX = centerX - mBitmapWidth / 2;
        drawY = centerY - mBitmapHeight / 2;

        mLeftRect = new Rect(0, 0, centerX, viewHeight);
        mRightRect = new Rect(centerX, 0, viewWidth, viewHeight);
        mUpRect = new Rect(0, 0, viewWidth, centerY);
        mDownRect = new Rect(0, centerY, viewWidth, viewHeight);

        mAnimatorSet.start();

    }


}
