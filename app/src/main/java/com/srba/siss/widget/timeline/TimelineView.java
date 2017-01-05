package com.srba.siss.widget.timeline;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.srba.siss.R;

/**
 * 作者:  曾维俊
 * 版本:  1.0
 * 日期:  2016/11/23 17:31
 * 描述:  自定义时间轴的view
 * 修改历史:
 * 日期         	修改人        		版本        	      描述
 * -----------------------------------------------------------------------------------
 * 2016/11/23       曾维俊               1.0                   1.0
 * 修改原因以及修改内容:
 */
public class TimelineView extends View {
    /**
     * 实线
     */
    public static final int LINE_TYPE_SOLID = 1;
    /**
     * 虚线
     */
    public static final int LINE_TYPE_DASH = 2;
    /**
     * 圆点
     */
    private Drawable mMarker;

    private int mMarkerSize;
    private int mLineSize;
    private int mLineOrientation;
    private boolean mMarkerInCenter;

    private int lineColor;
    /**
     * view的类型
     */
    private int viewType = -1;
    private Rect mBounds;
    private Context mContext;
    private Paint linepaint;
    private Path linepath;

    public TimelineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.timeline_style);
        mMarker = typedArray.getDrawable(R.styleable.timeline_style_marker);
        //  mStartLine = typedArray.getDrawable(R.styleable.timeline_style_line);
        //  mEndLine = typedArray.getDrawable(R.styleable.timeline_style_line);
        mMarkerSize = typedArray.getDimensionPixelSize(R.styleable.timeline_style_marker_size, 25);
        mLineSize = typedArray.getDimensionPixelSize(R.styleable.timeline_style_line_size, 2);
        mLineOrientation = typedArray.getInt(R.styleable.timeline_style_line_orientation, 1);
        mMarkerInCenter = typedArray.getBoolean(R.styleable.timeline_style_markerInCenter, true);
        viewType = typedArray.getInt(R.styleable.timeline_style_lineType, 1);
        lineColor = typedArray.getColor(R.styleable.timeline_style_lineColor, 3344);
        typedArray.recycle();

        if (mMarker == null) {
            mMarker = mContext.getResources().getDrawable(R.drawable.marker);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //Width measurements of the width and height and the inside view of child controls
        int w = mMarkerSize + getPaddingLeft() + getPaddingRight();
        int h = mMarkerSize + getPaddingTop() + getPaddingBottom();

        // Width and height to determine the final view through a systematic approach to decision-making
        int widthSize = resolveSizeAndState(w, widthMeasureSpec, 0);
        int heightSize = resolveSizeAndState(h, heightMeasureSpec, 0);

        setMeasuredDimension(widthSize, heightSize);
        initDrawable();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // When the view is displayed when the callback
        // Positioning Drawable coordinates, then draw
        initDrawable();
    }

    private void initDrawable() {
        int pLeft = getPaddingLeft();
        int pRight = getPaddingRight();
        int pTop = getPaddingTop();
        int pBottom = getPaddingBottom();

        int width = getWidth();// Width of current custom view
        int height = getHeight();

        int cWidth = width - pLeft - pRight;// Circle width
        int cHeight = height - pTop - pBottom;

        int markSize = Math.min(mMarkerSize, Math.min(cWidth, cHeight));

        if (mMarkerInCenter) { //圆点在中间

            if (mMarker != null) {
                mMarker.setBounds((width / 2) - (markSize / 2), (height / 2) - (markSize / 2), (width / 2) + (markSize / 2), (height / 2) + (markSize / 2));
                mBounds = mMarker.getBounds();
            }

        } else { //圆点不在中间

            if (mMarker != null) {
                mMarker.setBounds(pLeft, pTop, pLeft + markSize, pTop + markSize);
                mBounds = mMarker.getBounds();
            }
        }

        int centerX = mBounds.centerX();
        int lineLeft = centerX;

     /*   if (mLineOrientation == 0) {
            //Horizontal Line

            if (mStartLine != null) {
                mStartLine.setBounds(0, pTop + (mBounds.height() / 2), mBounds.left, (mBounds.height() / 2) + pTop + mLineSize);


            }

            if (mEndLine != null) {
                mEndLine.setBounds(mBounds.right, pTop + (mBounds.height() / 2), width, (mBounds.height() / 2) + pTop + mLineSize);
            }

        } else {

            //Vertical Line

            //  if(mStartLine != null) {
            //       mStartLine.setBounds(lineLeft, 0, mLineSize + lineLeft, mBounds.top);

            //   }

                  if(mEndLine != null) {
                mEndLine.setBounds(lineLeft, mBounds.bottom, mLineSize + lineLeft, height);
            }
        }*/
        switch (viewType) {

            case ViewType.NORMAL:
                linepaint = new Paint();
                linepaint.setStyle(Paint.Style.STROKE);
                linepaint.setColor(lineColor);
                linepaint.setAntiAlias(true);
                linepaint.setStrokeWidth(mLineSize);
                linepath = new Path();
                linepath.moveTo(lineLeft, 0);
                linepath.lineTo(lineLeft, mBounds.top);
                linepath.moveTo(lineLeft, mBounds.bottom);
                linepath.lineTo(lineLeft, height);

                break;
            case ViewType.BEGIN:
                linepaint = new Paint();
                linepaint.setStyle(Paint.Style.STROKE);
                linepaint.setAntiAlias(true);
                linepaint.setStrokeWidth(mLineSize);
                linepaint.setColor(lineColor);
                linepath = new Path();
                linepath.moveTo(lineLeft, mBounds.bottom);
                linepath.lineTo(lineLeft, height);
                break;
            case ViewType.END:
                linepaint = new Paint();
                linepaint.setAntiAlias(true);
                linepaint.setStrokeWidth(mLineSize);
                linepaint.setStyle(Paint.Style.STROKE);
                linepaint.setColor(lineColor);
                linepath = new Path();
                linepath.moveTo(lineLeft, 0);
                linepath.lineTo(lineLeft, mBounds.top);

                break;
            case ViewType.ONLYONE:
                break;
            case ViewType.NORMAL_DASH:
                linepaint = new Paint();
                linepaint.setStyle(Paint.Style.STROKE);
                linepaint.setColor(lineColor);
                linepaint.setAntiAlias(true);
                linepaint.setStrokeWidth(mLineSize);
                linepath = new Path();
                linepath.moveTo(lineLeft, 0);
                linepath.lineTo(lineLeft, mBounds.top);

                linepath.moveTo(lineLeft, mBounds.bottom);
                linepath.lineTo(lineLeft, height);

                PathEffect effects1 = new DashPathEffect(new float[]{5, 5}, 0);
                linepaint.setPathEffect(effects1);

                break;
            case ViewType.BEGIN_DASH:
                linepaint = new Paint();
                linepaint.setStyle(Paint.Style.STROKE);
                linepaint.setAntiAlias(true);
                linepaint.setStrokeWidth(mLineSize);
                linepaint.setColor(lineColor);
                linepath = new Path();

                linepath.moveTo(lineLeft, mBounds.bottom);
                linepath.lineTo(lineLeft, height);

                PathEffect effects2 = new DashPathEffect(new float[]{5, 5}, 0);
                linepaint.setPathEffect(effects2);

                break;
            case ViewType.END_DASH:
                linepaint = new Paint();
                linepaint.setAntiAlias(true);
                linepaint.setStrokeWidth(mLineSize);
                linepaint.setStyle(Paint.Style.STROKE);
                linepaint.setColor(lineColor);
                linepath = new Path();

                linepath.moveTo(lineLeft, 0);
                linepath.lineTo(lineLeft, mBounds.top);
                PathEffect effects3 = new DashPathEffect(new float[]{5, 5}, 0);
                linepaint.setPathEffect(effects3);

                break;
            case ViewType.ONLYONE_DASH:
                break;
        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mMarker != null) {
            mMarker.draw(canvas);
        }

        if (linepaint != null) {
            canvas.drawPath(linepath, linepaint);
        }
    }

    /**
     * 设置圆点
     * @param marker
     */
    public void setMarker(Drawable marker) {
        mMarker = marker;
        initDrawable();
    }

    /**
     * 设置中心圆点尺寸
     *
     * @param markerSize
     */
    public void setMarkerSize(int markerSize) {
        mMarkerSize = markerSize;
        initDrawable();
    }

    /**
     * 设置时间线尺寸
     * @param lineSize
     */
    public void setLineSize(int lineSize) {
        mLineSize = lineSize;
        initDrawable();
    }

    /**
     *  初始化时间线类型
     * @param type
     */
    public void initLine(int type) {
        switch (type){
            case  ViewType.BEGIN:
                viewType = ViewType.BEGIN;
                break;
            case  ViewType.END:
                viewType = ViewType.END;
                break;
            case  ViewType.ONLYONE:
                viewType = ViewType.ONLYONE;
                break;
            case  ViewType.NORMAL:
                viewType = ViewType.NORMAL;
                break;
            case  ViewType.BEGIN_DASH:
                viewType = ViewType.BEGIN_DASH;
                break;
            case  ViewType.END_DASH:
                viewType = ViewType.END_DASH;
                break;
            case  ViewType.ONLYONE_DASH:
                viewType = ViewType.ONLYONE_DASH;
                break;
            case  ViewType.NORMAL_DASH:
                viewType = ViewType.NORMAL_DASH;
                break;
        }
        initDrawable();
    }

    /**
     * 获取时间线类型
     * @param position
     * @param total_size
     * @param lineType
     * @return
     */
    public static int getTimeLineViewType(int position, int total_size, int lineType) {
        if (lineType == LINE_TYPE_SOLID) {
            if (total_size == 1) {
                return ViewType.ONLYONE;
            } else if (position == 0) {
                return ViewType.BEGIN;
            } else if (position == total_size - 1) {
                return ViewType.END;
            } else {
                return ViewType.NORMAL;
            }
        } else if (lineType == LINE_TYPE_DASH) {
            if (total_size == 1) {
                return ViewType.ONLYONE_DASH;
            } else if (position == 0) {
                return ViewType.BEGIN_DASH;
            } else if (position == total_size - 1) {
                return ViewType.END_DASH;
            } else {
                return ViewType.NORMAL_DASH;
            }
        } else {
            return ViewType.NORMAL;
        }
    }
}
