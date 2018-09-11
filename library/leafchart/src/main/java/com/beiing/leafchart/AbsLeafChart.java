package com.beiing.leafchart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.beiing.leafchart.bean.Axis;
import com.beiing.leafchart.bean.AxisValue;
import com.beiing.leafchart.bean.ChartData;
import com.beiing.leafchart.bean.PointValue;
import com.beiing.leafchart.renderer.AbsRenderer;
import com.beiing.leafchart.support.Chart;
import com.beiing.leafchart.support.LeafUtil;
import com.beiing.leafchart.support.AsixMode;
import com.beiing.leafchart.support.ShowMode;

import java.util.List;

/**
 * Created by chenliu on 2016/7/15.<br/>
 * 描述：
 * </br>
 */
public abstract class AbsLeafChart extends View implements Chart {

    static final String TAG = SlideSelectLineChart.class.getName();

    /**
     * 坐标轴相交模式
     */
    protected int coordinateMode;

    /**
     * 第一个点x轴方向起始位置
     */
    protected int startMarginX = 0;
    /**
     * 第一个点y轴方向起始位置
     */
    protected int startMarginY = 0;

    protected Axis axisX;
    protected Axis axisY;

    protected float mWidth;//控件宽度
    protected float mHeight;//控件高度
    protected float leftPadding, topPadding, rightPadding, bottomPadding;//控件内部间隔

    protected Context mContext;

    private AbsRenderer absRenderer;
    /**
     * 展示模式
     */
    protected int mShowMode;
    protected float mModePadding = 0f;

    public AbsLeafChart(Context context) {
        this(context, null, 0);
    }

    public AbsLeafChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbsLeafChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        initRenderer();

        setRenderer();

        initAttrs(attrs);
    }

    protected void initAttrs(AttributeSet attrs) {
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.AbsLeafChart);
        try {
            leftPadding = ta.getDimension(R.styleable.AbsLeafChart_lc_leftPadding, LeafUtil.dp2px(mContext, 20));
            topPadding = ta.getDimension(R.styleable.AbsLeafChart_lc_topPadding, LeafUtil.dp2px(mContext, 10));
            rightPadding = ta.getDimension(R.styleable.AbsLeafChart_lc_rightPadding, LeafUtil.dp2px(mContext, 10));
            bottomPadding = ta.getDimension(R.styleable.AbsLeafChart_lc_bottomPadding, LeafUtil.dp2px(mContext, 20));
            startMarginX = (int) ta.getDimension(R.styleable.AbsLeafChart_lc_startMarginX, 0);
            startMarginY = (int) ta.getDimension(R.styleable.AbsLeafChart_lc_startMarginY, 0);
            coordinateMode = ta.getInteger(R.styleable.AbsLeafChart_lc_coordinateMode, AsixMode.INTERSECT);
        } finally {
            ta.recycle();
        }
    }

    public void setRenderer(AbsRenderer renderer) {
        this.absRenderer = renderer;
    }

    ///////////////////子类必须重写的方法////////////////////////////
    protected abstract void initRenderer();

    protected abstract void setRenderer();

    protected abstract void resetPointWeight();

    /**
     * 不重写改方法，在布局中使用 wrap_content 显示效果是 match_parent
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension((int) LeafUtil.dp2px(mContext, 300), (int) LeafUtil.dp2px(mContext, 300));
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension((int) mWidth, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, (int) mHeight);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        initViewSize();

        resetAsixSize();

        resetPointWeight();

    }

    protected void initViewSize() {
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        absRenderer.setWH(mWidth, mHeight);
        absRenderer.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);

        if (mShowMode == ShowMode.PADDING) {
            int sizeX = axisX.getValues().size(); //几条x轴
            //+2表示左右两边padding，n个点平分成(n - 1) * 2个空间
            mModePadding = (mWidth - leftPadding - startMarginX) / ((sizeX - 1) * 2 + 2);
        }
    }

    /**
     * 设置坐标轴位置
     */
    public void resetAsixSize() {

        resetAsixX();

        resetAsixY();

    }

    protected void resetAsixY() {
        if (axisY != null) {
            List<AxisValue> values = axisY.getValues();
            int sizeY = values.size(); //几条x轴
            float yStep = (mHeight - topPadding - bottomPadding - startMarginY) / sizeY;
            for (int i = 0; i < sizeY; i++) {
                AxisValue axisValue = values.get(i);
                axisValue.setPointX(leftPadding);
                if (i == 0) {
                    axisValue.setPointY(mHeight - bottomPadding - startMarginY);
                } else {
                    axisValue.setPointY(mHeight - bottomPadding - startMarginY - yStep * i);
                }
            }
            switch (coordinateMode) {
                case AsixMode.ACROSS:
                case AsixMode.Y_ACROSS:
                    axisY.setStartY(mHeight - bottomPadding * 0.5f);
                    break;

                case AsixMode.INTERSECT:
                case AsixMode.X_ACROSS:
                    axisY.setStartY(mHeight - bottomPadding);
                    break;
            }
            axisY.setStartX(leftPadding).setStopX(leftPadding).setStopY(0);

        }
    }

    protected void resetAsixX() {
        if (axisX != null) {
            List<AxisValue> values = axisX.getValues();
            int sizeX = values.size(); //几条y轴
            float xStep;

            if (mShowMode == ShowMode.PADDING) {
                xStep = mModePadding * 2;
            } else
                xStep = (mWidth - leftPadding - startMarginX) / (sizeX - 1);//

            for (int i = 0; i < sizeX; i++) {
                AxisValue axisValue = values.get(i);
                axisValue.setPointY(mHeight);
                if (mShowMode == ShowMode.PADDING)
                    axisValue.setPointX(leftPadding + startMarginX + mModePadding + xStep * i);
                else
                    axisValue.setPointX(leftPadding + startMarginX + xStep * i);
//                if (i == 0) {
//                    if (mShowMode == ShowMode.PADDING)
//                        axisValue.setPointX(leftPadding + startMarginX + padding);
//                    else
//                        axisValue.setPointX(leftPadding + startMarginX);
//                } else {
//                    if (mShowMode == ShowMode.PADDING)
//                        axisValue.setPointX(leftPadding + startMarginX + padding + xStep * i);
//                    else
//                        axisValue.setPointX(leftPadding + startMarginX + xStep * i);
//                }
            }


            switch (coordinateMode) {
                case AsixMode.ACROSS:
                case AsixMode.X_ACROSS:
                    axisX.setStartX(leftPadding * 0.5f);
                    break;

                case AsixMode.INTERSECT:
                case AsixMode.Y_ACROSS:
                    axisX.setStartX(leftPadding);
                    break;
            }
            axisX.setStartY(mHeight - bottomPadding).setStopX(mWidth).setStopY(mHeight - bottomPadding);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        absRenderer.drawCoordinateLines(canvas, axisX, axisY);
        absRenderer.drawCoordinateText(canvas, axisX, axisY);
    }

    protected void resetPointWeight(ChartData chartData) {
        if (chartData != null && axisX != null && axisY != null) {
            List<PointValue> values = chartData.getValues();
            int size = values.size();

            List<AxisValue> axisValuesX = axisX.getValues();
            List<AxisValue> axisValuesY = axisY.getValues();
            float totalWidth = Math.abs(axisValuesX.get(0).getPointX() - axisValuesX.get(axisValuesX.size() - 1).getPointX());
            //Logger.e(axisValuesX.get(4).getPointX() + "");
            float totalHeight = Math.abs(axisValuesY.get(0).getPointY() - axisValuesY.get(axisValuesY.size() - 1).getPointY());
            if (mShowMode == ShowMode.PADDING) {
                //初始化已经设置两边pading比值
                if (chartData.getLeftModePaddingY() != -1) {
                    chartData.setLeftModePointX(leftPadding + startMarginX);
                    float diffYMode = chartData.getLeftModePaddingY() * totalHeight;
                    chartData.setLeftModePointY(mHeight - bottomPadding - diffYMode - startMarginY);
                }

                if (chartData.getRightModePaddingY() != -1) {
                    chartData.setRightModePointX(mWidth);
                    float diffYMode = chartData.getRightModePaddingY() * totalHeight;
                    chartData.setRightModePointY(mHeight - bottomPadding - diffYMode - startMarginY);
                }

            }
            for (int i = 0; i < size; i++) {
                PointValue pointValue = values.get(i);
                float diffX = pointValue.getX() * totalWidth;
//                if (i==4)
//                Log.e("chart", "" + pointValue.getX());
                pointValue.setDiffX(diffX);

                float diffY = pointValue.getY() * totalHeight;
                pointValue.setDiffY(diffY);

                float originX1;
                if (mShowMode == ShowMode.PADDING)
                    originX1 = diffX + leftPadding + startMarginX + mModePadding;
                else
                    originX1 = diffX + leftPadding + startMarginX;
                float originY1 = mHeight - bottomPadding - diffY - startMarginY;
                pointValue.setOriginX(originX1).setOriginY(originY1);
            }
        }
    }

    @Override
    public void setAxisX(Axis axisX) {
        this.axisX = axisX;
        resetAsixX();
        invalidate();
    }

    @Override
    public void setAxisY(Axis axisY) {
        this.axisY = axisY;
        resetAsixY();
        invalidate();
    }

    @Override
    public Axis getAxisX() {
        return axisX;
    }

    @Override
    public Axis getAxisY() {
        return axisY;
    }
}
