package yanzhikai.ruler.InnerRulers;

import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.OverScroller;

import yanzhikai.ruler.BooheeRuler;
import yanzhikai.ruler.RulerCallback;

/**
 * 内部尺子抽象类
 */

public abstract class InnerRuler extends View {
    protected Context mContext;
    protected BooheeRuler mParent;

    protected Paint mSmallScalePaint, mBigScalePaint, mTextPaint, mOutLinePaint;
    //当前刻度值
    protected float mCurrentScale = 0;
    //最大刻度数
    protected int mMaxLength = 0;
    //长度、最小可滑动值、最大可滑动值
    protected int mLength, mMinPosition = 0, mMaxPosition = 0;
    //控制滑动
    protected OverScroller mOverScroller;
    //一格大刻度多少格小刻度
    protected int mCount = 10;
    //提前刻画量
    protected int mDrawOffset = 0;
    //速度获取
    protected VelocityTracker mVelocityTracker;
    //惯性最大最小速度
    protected int mMaximumVelocity, mMinimumVelocity;
    //回调接口
    protected RulerCallback mRulerCallback;

    public InnerRuler(Context context, BooheeRuler booheeRuler) {
        super(context);
        mParent = booheeRuler;
        init(context);
    }

    private void init(Context context){
        mContext = context;

        mMaxLength = mParent.getMaxScale() - mParent.getMinScale();
        mCurrentScale = mParent.getCurrentScale();
        mCount = mParent.getCount();
        mDrawOffset = mCount * mParent.getInterval() / 2;

        initPaints();

        mOverScroller = new OverScroller(mContext);

//        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        //配置速度
        mVelocityTracker = VelocityTracker.obtain();
        mMaximumVelocity = ViewConfiguration.get(context)
                .getScaledMaximumFlingVelocity();
        mMinimumVelocity = ViewConfiguration.get(context)
                .getScaledMinimumFlingVelocity();

        //第一次进入，跳转到设定刻度
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                goToScale(mCurrentScale);
            }
        });
        checkAPILevel();
    }

    //初始化画笔
    private void initPaints() {
        mSmallScalePaint = new Paint();
        mSmallScalePaint.setStrokeWidth(mParent.getSmallScaleWidth());
        mSmallScalePaint.setColor(mParent.getScaleColor());
        mSmallScalePaint.setStrokeCap(Paint.Cap.ROUND);;

        mBigScalePaint = new Paint();
        mBigScalePaint.setColor(mParent.getScaleColor());
        mBigScalePaint.setStrokeWidth(mParent.getBigScaleWidth());
        mBigScalePaint.setStrokeCap(Paint.Cap.ROUND);;

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mParent.getTextColor());
        mTextPaint.setTextSize(mParent.getTextSize());
        mTextPaint.setTextAlign(Paint.Align.CENTER);
//        mTextPaint.setStrokeJoin(Paint.Join.ROUND);
        mOutLinePaint = new Paint();
        mOutLinePaint.setStrokeWidth(0);
        mOutLinePaint.setColor(mParent.getScaleColor());
    }


    //API小于18则关闭硬件加速，否则setAntiAlias()方法不生效
    private void checkAPILevel(){
        if (Build.VERSION.SDK_INT < 18){
            setLayerType(LAYER_TYPE_NONE,null);
        }
    }

    public abstract void goToScale(float scale);

    //设置尺子当前刻度
    public void setCurrentScale(float currentScale) {
        this.mCurrentScale = currentScale;
        goToScale(mCurrentScale);
    }

    public void setRulerCallback(RulerCallback RulerCallback) {
        this.mRulerCallback = RulerCallback;
    }

    public float getCurrentScale() {
        return mCurrentScale;
    }
}
