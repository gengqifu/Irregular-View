package org.hunter.irregularshapeviewlib.clippath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import static android.graphics.Region.Op.INTERSECT;

/**
 * 三角形ImangeView
 */

public class TriangleView extends android.support.v7.widget.AppCompatImageView {
    private Context mContext;
    private int mWidth;
    private int mHeight;

    // 三角形的模式以直角点所在的点位置为模式名
    public static final int TYPE_LEFT_TOP = 0;
    public static final int TYPE_RIGHT_BOTTOM = 1;
    public static final int TYPE_RIGHT_TOP = 2;
    public static final int TYPE_RIGHT_MIDDLE = 3;
    public static final int TYPE_LEFT_BOTTOM = 4;
    public static final int TYPE_RIGHT_BOTTOM_SMAILL = 5;

    private int mMode;
    private int mColor;
    private String mText;
    // 三个点的顺序，从leftTop开始计算，顺时针数过去，依次三个点
    private int mPointOne[];
    private int mPointTwo[];
    private int mPointThree[];

    private Path mPath;
    private Path mTextPath;
    private Paint mPaint;

    private boolean isClicked = false; //是否被按下

    public TriangleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mPath = new Path();
        mTextPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColor = Color.parseColor("#FFFFFF");
        mPaint.setColor(mColor);
    }

    public void setMode(int model) {
        mMode = model;
        postInvalidate();
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
        canvas.clipPath(mPath,INTERSECT);
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
            switch (mMode) {
                case TYPE_LEFT_TOP:
                    canvas.drawText(mText, (mWidth - tw) / 5f, mHeight / 3f, mPaint);
                    break;
                case TYPE_RIGHT_BOTTOM:
                    canvas.drawText(mText, (mWidth - tw)*4/5f, mHeight*4/5f, mPaint);
                    break;
                case TYPE_RIGHT_TOP:
                    canvas.drawText(mText, (mWidth - tw)*3/4f, mHeight/3f, mPaint);
                    break;
                case TYPE_RIGHT_MIDDLE:
                    canvas.drawText(mText, (mWidth*0.92f - tw)/2f, (mHeight+th)/2f, mPaint);
                    break;
                case TYPE_LEFT_BOTTOM:
                    mTextPath.moveTo((1.4f*mHeight-tw-2*th)/2.8f,(1.4f*mHeight-tw+2*th)/2.8f);
                    mTextPath.lineTo((1.4f*mHeight+tw-2*th)/2.8f,(1.4f*mHeight+tw+2*th)/2.8f);
                    canvas.drawTextOnPath(mText,mTextPath,0, 0, mPaint);
                    break;
                case TYPE_RIGHT_BOTTOM_SMAILL:
                    mTextPath.moveTo((1.4f*mHeight-tw+2*th)/2.8f,(1.4f*mHeight+tw+2*th)/2.8f);
                    mTextPath.lineTo((1.4f*mHeight+tw+2*th)/2.8f,(1.4f*mHeight-tw+2*th)/2.8f);
                    canvas.drawTextOnPath(mText,mTextPath,0, 0, mPaint);
                    break;
            }
        }
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);
        mWidth = width;
        mHeight = height;

        switch (mMode) {
            case TYPE_LEFT_TOP:
                mPointOne = new int[] { 0, 0 };
                mPointTwo = new int[] { width, 0 };
                mPointThree = new int[] { 0, height };
                break;
            case TYPE_RIGHT_BOTTOM:
                mPointOne = new int[] { width, 0 };
                mPointTwo = new int[] { width, height };
                mPointThree = new int[] { 0, height };
                break;
            case TYPE_RIGHT_TOP:
                mPointOne = new int[] { 0, 0 };
                mPointTwo = new int[] { width, 0 };
                mPointThree = new int[] { width, height };
                break;
            case TYPE_RIGHT_MIDDLE:
                mPointOne = new int[] { 0, 0 };
                mPointTwo = new int[] { width, height / 2 };
                mPointThree = new int[] { 0, height };
                break;
            case TYPE_LEFT_BOTTOM:
                mPointOne = new int[] { 0, 0 };
                mPointTwo = new int[] { width, height};
                mPointThree = new int[] { 0, height };
                break;
            case TYPE_RIGHT_BOTTOM_SMAILL:
                mPointOne = new int[] { width, 0 };
                mPointTwo = new int[] { width, height };
                mPointThree = new int[] { 0, height };
                break;
        }

        if (null != mPointOne) {
            mPath.moveTo(mPointOne[0], mPointOne[1]);
            mPath.lineTo(mPointTwo[0], mPointTwo[1]);
            mPath.lineTo(mPointThree[0], mPointThree[1]);
            mPath.close();
        }
    }

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

    //计算两点的距离
    private int distance(PointF point1, PointF point2) {
        int disX = (int) Math.abs(point1.x - point2.x);
        int disY = (int) Math.abs(point1.y - point2.y);
        return (int) Math.sqrt(Math.pow(disX, 2) + Math.pow(disY, 2));
    }

    //将sp值转换为px值，保证文字大小不变
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
