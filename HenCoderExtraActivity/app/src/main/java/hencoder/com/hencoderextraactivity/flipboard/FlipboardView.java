package hencoder.com.hencoderextraactivity.flipboard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import hencoder.com.hencoderextraactivity.R;


public class FlipboardView extends View {


    public FlipboardView(Context context) {
        super(context);
        init();
    }


    public FlipboardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlipboardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private int centerX;
    private int centerY;
    private int drawX;
    private int drawY;
    private int degreeY;
    private int degreeZ;
    private int degreeY1;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Camera camera = new Camera();


    //起点
    Flipboard start = new Flipboard(0, 0);
    // y轴逆时针旋转40,z轴逆时针旋转270
    Flipboard middle = new Flipboard(-40, -270);
    // 最后x轴旋转30
    Flipboard end = new Flipboard(20, 0);

    // y轴-40度旋转
    ValueAnimator animatorY = ValueAnimator.ofObject(new IntEvaluator(),
            start.degreeY, middle.degreeY);
    //z 轴-270旋转
    ValueAnimator animatorZ = ValueAnimator.ofObject(new IntEvaluator(),
            start.degreeZ, middle.degreeZ);
    // x轴旋转30
    ValueAnimator animatorY1 = ValueAnimator.ofObject(new IntEvaluator(),
            start.degreeY, end.degreeY);
    // 重置所有点的值，动画恢复初始
    ValueAnimator animatorReset = ValueAnimator.ofInt(0);


    AnimatorSet mAnimatorSet = new AnimatorSet();


    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        invalidate();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);

        animatorY.setDuration(1000);
        animatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degreeY = (int) animation.getAnimatedValue();
                invalidate();
            }
        });

        animatorZ.setDuration(800);
        animatorZ.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorZ.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degreeZ = (int) animation.getAnimatedValue();
                invalidate();
            }
        });

        animatorY1.setDuration(300);
        animatorY1.setStartDelay(300);
        animatorY1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degreeY1 = (int) animation.getAnimatedValue();
                invalidate();
            }
        });


        animatorReset.setStartDelay(500);
        animatorReset.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                degreeY = 0;
                degreeZ = 0;
                degreeY1 = 0;
                invalidate();
            }
        });

        mAnimatorSet.playSequentially(animatorY, animatorZ, animatorY1, animatorReset);
        mAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mAnimatorSet.setStartDelay(300);
                mAnimatorSet.start();
            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mAnimatorSet.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAnimatorSet.end();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        centerX = w / 2;
        centerY = h / 2;
        drawX = centerX - bitmapWidth / 2;
        drawY = centerY - bitmapHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(0xff1E90FF);

        canvas.save();
        camera.save();
        canvas.translate(centerX, centerY);
        canvas.rotate(degreeZ);
        canvas.clipRect(-centerX, -centerY, 0, centerY);
        camera.rotateY(degreeY1);
        camera.applyToCanvas(canvas);
        canvas.rotate(-degreeZ);
        canvas.translate(-centerX, -centerY);
        camera.restore();
        canvas.drawBitmap(bitmap, drawX, drawY, paint);
        canvas.restore();

        canvas.save();
        camera.save();
        canvas.translate(centerX, centerY);
        canvas.rotate(degreeZ);
        camera.rotateY(degreeY);
        camera.applyToCanvas(canvas);
        canvas.clipRect(0, -centerY, centerX, centerY);
        canvas.rotate(-degreeZ);
        canvas.translate(-centerX, -centerY);
        camera.restore();
        canvas.drawBitmap(bitmap, drawX, drawY, paint);
        canvas.restore();
    }
}
