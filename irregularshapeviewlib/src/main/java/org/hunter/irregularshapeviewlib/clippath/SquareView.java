package org.hunter.irregularshapeviewlib.clippath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * 正方形ImangeView
 */

public class SquareView extends android.support.v7.widget.AppCompatImageView {
    private Context mContext;

    private int mWidth;
    private int mHeight;

    private int mColor;
    private String mText;

    // 四个点的顺序，从leftTop开始计算，顺时针数过去，依次四个点
    private int mPointOne[];
    private int mPointTwo[];
    private int mPointThree[];
    private int mPointFour[];

    private Path mPath;
    private Paint mPaint;

    private boolean isClicked = false; //是否被按下

    public SquareView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColor = Color.parseColor("#FFFFFF");
        mPaint.setColor(mColor);
    }

    public void setColor(int color) {
        mColor = color;
        postInvalidate();
    }
    public void setText(String text) {
        mText = text;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.clipPath(mPath, Region.Op.INTERSECT);
        super.onDraw(canvas);
        if(!isClicked) {
            mPaint.setColor(Color.argb(180, 0, 0, 0));
            canvas.drawPath(mPath, mPaint);
        }
        if(mText != null) {
            mPaint.setFakeBoldText(true);
            mPaint.setTextSize(sp2px(mContext,13));
            mPaint.setColor(mColor);
            float tw = mPaint.measureText(mText);
            float th = mPaint.measureText(mText)/mText.length();
            canvas.drawText(mText, (mWidth - tw)/2f, (mHeight+th)/2f, mPaint);
        }
    }
    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);
        mWidth = width;
        mHeight = height;
        mPointOne = new int[] { 0, 0 };
        mPointTwo = new int[] { width, 0 };
        mPointThree = new int[] { width, height };
        mPointFour = new int[] { 0, height };

        if (null != mPointOne) {
            mPath.moveTo(mPointOne[0], mPointOne[1]);
            mPath.lineTo(mPointTwo[0], mPointTwo[1]);
            mPath.lineTo(mPointThree[0], mPointThree[1]);
            mPath.lineTo(mPointFour[0], mPointFour[1]);
            mPath.close();
        }
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        RectF bounds = new RectF();
        mPath.computeBounds(bounds, true);
        Region region = new Region();
        region.setPath(mPath, new Region((int)bounds.left, (int)bounds.top,(int)bounds.right, (int)bounds.bottom));
        boolean ct =  region.contains((int)event.getX(), (int)event.getY());
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(ct){
                isClicked = true;
                invalidate();
                return true;
            }
            return false;
        }else if(event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL){
            if(isClicked){
                isClicked = false;
                invalidate();
                if (null != mOnClickListener && ct && event.getAction() != MotionEvent.ACTION_CANCEL) {
                    mOnClickListener.onClick(this);
                    return true;
                }
            }
            return false;
        }else if(event.getAction() == MotionEvent.ACTION_MOVE){
            return isClicked;
        }
        return super.onTouchEvent(event);
    }

    private OnClickListener mOnClickListener;
    public void setOnViewClickListener(OnClickListener clickListener){
        mOnClickListener = clickListener;
    }

    //将sp值转换为px值，保证文字大小不变
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
