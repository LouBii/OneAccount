package com.beiing.leafchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.beiing.leafchart.bean.AxisValue;
import com.beiing.leafchart.bean.Line;
import com.beiing.leafchart.bean.PointValue;
import com.beiing.leafchart.bean.SlidingLine;
import com.beiing.leafchart.renderer.SlideSelectLineRenderer;
import com.beiing.leafchart.support.LeafUtil;
import com.beiing.leafchart.support.Logger;
import com.beiing.leafchart.support.OnChartSelectedListener;
import com.beiing.leafchart.support.OnPointSelectListener;
import com.beiing.leafchart.support.ShowMode;

import java.util.List;

/**
 * Created by chenliu on 2016/12/13.<br/>
 * 描述：滑动选择手指竖直方向最近的点
 * </br>
 */

public class SlideSelectLineChart extends AbsLeafChart {
    private Line line;
    private SlidingLine slidingLine;

    private float moveX;
    private float moveY;
    private boolean isDrawMoveLine;
    private OnPointSelectListener onPointSelectListener;

    float downX;
    float downY;
    int scaledTouchSlop;

    private boolean isCanSelected;

    SlideSelectLineRenderer slideRenderer;

    private OnChartSelectedListener mOnChartSelectedListener;
    /**
     * 选中位置
     */
    private int mSelectedPosition = -1;

    private boolean mIsCanMove = false;

    public SlideSelectLineChart(Context context) {
        this(context, null, 0);
    }

    public SlideSelectLineChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideSelectLineChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initDefaultSlidingLine();

        scaledTouchSlop = ViewConfiguration.get(mContext).getScaledTouchSlop();

//        setCanSelected(true);
//        if (null != mOnChartSelectedListener) {
//            mOnChartSelectedListener.onChartSelected(true);
//        }

    }

    @Override
    protected void initRenderer() {
        slideRenderer = new SlideSelectLineRenderer(mContext, this);
    }

    @Override
    protected void setRenderer() {
        super.setRenderer(slideRenderer);
    }

    private void initDefaultSlidingLine() {
        slidingLine = new SlidingLine();
        slidingLine.setDash(true).setSlideLineWidth(1).setSlidePointRadius(3);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 设置点所占比重
     */
    @Override
    public void resetPointWeight() {
        if (line != null) {
            super.resetPointWeight(line);
            if (mSelectedPosition != -1) {
                //Logger.e(line.getValues().get(4).getOriginX() + "XXXX");
                PointValue pointValue = line.getValues().get(mSelectedPosition);
                moveX = pointValue.getOriginX();
                moveY = pointValue.getOriginY();
                pointValue.setShowLabel(true);
                if (onPointSelectListener != null) {
                    onPointSelectListener.onPointSelect(mSelectedPosition, axisX.getValues().get(mSelectedPosition).getLabel(), pointValue.getLabel());
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (line != null) {
            if (line.isCubic()) {
                slideRenderer.drawCubicPath(canvas, line);
            } else {
                slideRenderer.drawLines(canvas, line);
            }

            if (line.isFill()) {
                //填充
                slideRenderer.drawFillArea(canvas, line, axisX);
            }

            slideRenderer.drawPoints(canvas, line);

            if (line.isHasLabels()) {
                slideRenderer.drawLabels(canvas, line, axisY);
            }

        }

        if (mShowMode == ShowMode.PADDING) {
            slideRenderer.drawPaddingLine(canvas, line);
        }

        if (slidingLine != null && slidingLine.isOpenSlideSelect()) {
            //绘制移动标尺线
            if (isDrawMoveLine) {

                slideRenderer.drawSlideLine(canvas, axisX, slidingLine, moveX, moveY);
            }
        }
    }

    /**
     * 带动画的绘制
     *
     * @param duration
     */
    public void showWithAnimation(int duration) {
        slideRenderer.showWithAnimation(duration);
    }

    public void show() {
        showWithAnimation(0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        if (!isCanSelected)
//            return super.onTouchEvent(event);

        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                float selectPositionX = line.getValues().get(mSelectedPosition).getOriginX();
                if (downX > selectPositionX - LeafUtil.dp2px(mContext, 40f) &&
                        downX < selectPositionX + LeafUtil.dp2px(mContext, 40f)) {
                    mIsCanMove = true;
                }
                setCanSelected(true);
                if (null != mOnChartSelectedListener) {
                    mOnChartSelectedListener.onChartSelected(true);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (downX - x != 0 && Math.abs(y - downY) < scaledTouchSlop) {
                    if (mIsCanMove) {
                        //Logger.e("run: " );
                        getParent().requestDisallowInterceptTouchEvent(true);
                        countRoundPoint(x,  MotionEvent.ACTION_MOVE);
                        //invalidate();
                    }

                }
                break;
            case MotionEvent.ACTION_UP:
                //滑动范围
                float range = LeafUtil.dp2px(mContext, 7f);
                Rect rect = new Rect((int) (downX - range), (int) (downY - range), (int) (downX + range), (int) (downY + range));
                //如果滑动则不移动选中条
                if (rect.contains((int) x, (int) y) || mIsCanMove == true ) {
                    countRoundPoint(x,  MotionEvent.ACTION_UP);
                }
                mIsCanMove = false;
//                isDrawMoveLine = false;
//                isCanSelected = false;
//                if (null != mOnChartSelectedListener) {
//                    mOnChartSelectedListener.onChartSelected(false);
//                }
                break;
            case MotionEvent.ACTION_CANCEL:
                isCanSelected = false;
                if (null != mOnChartSelectedListener) {
                    mOnChartSelectedListener.onChartSelected(false);
                }
                break;
        }
//        countRoundPoint(x);
//        invalidate();

        if (slidingLine != null) {
            if (slidingLine.isOpenSlideSelect()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 计算最接近的点
     *
     * @param x
     * @param actionMove
     */
    private void countRoundPoint(float x, int actionMove) {
        if (line != null) {
            List<AxisValue> axisXValues = axisX.getValues();
            int sizeX = axisXValues.size(); //几条y轴
            float xStep;
            int loc;
            if (mShowMode == ShowMode.PADDING) {
                xStep = mModePadding * 2;
                loc = Math.round((x - leftPadding - startMarginX - mModePadding) / xStep);
            } else {
                xStep = (mWidth - leftPadding - startMarginX) / (sizeX - 1);//
                loc = Math.round((x - leftPadding - startMarginX) / xStep);
            }
            List<PointValue> values = line.getValues();
            for (int i = 0, size = values.size(); i < size; i++) {
                PointValue pointValue = values.get(i);
                pointValue.setShowLabel(false);
                int ploc = Math.round(pointValue.getDiffX() / xStep);
                if (ploc == loc) {
                    pointValue.setShowLabel(true);
                    moveX = pointValue.getOriginX();
                    moveY = pointValue.getOriginY();
                    //moveY = pointValue.getOriginY() + LeafUtil.dp2px(mContext, line.getPointRadius());
                    isDrawMoveLine = true;

                    if (onPointSelectListener != null && actionMove == MotionEvent.ACTION_UP
                            && mSelectedPosition != loc) {
                        onPointSelectListener.onPointSelect(loc, axisXValues.get(loc).getLabel(), pointValue.getLabel());
                        mSelectedPosition = loc;
                    }
                    invalidate();
//                    break;
                }
            }
        }
    }

    public void setChartData(Line chartData) {
        line = chartData;
        mShowMode = chartData.getShowMode();
        resetPointWeight();
    }

    public void setSlideLine(SlidingLine slideLine) {
        this.slidingLine = slideLine;
    }

    /**
     * 设置默认选中点
     */
    public void setSelectedPoint(int position) {
        isDrawMoveLine = true;
        mSelectedPosition = position;
    }

//    public void setShowMode(int mode) {
//        this.mShowMode = mode;
//    }


    public Line getChartData() {
        return line;
    }

    public void setOnPointSelectListener(OnPointSelectListener onPointSelectListener) {
        this.onPointSelectListener = onPointSelectListener;
    }

    public void setOnChartSelectedListener(OnChartSelectedListener mOnChartSelectedListener) {
        this.mOnChartSelectedListener = mOnChartSelectedListener;
    }

    public void setCanSelected(boolean canSelected) {
        isCanSelected = canSelected;
//        Vibrator vib = (Vibrator) getContext().getSystemService(Service.VIBRATOR_SERVICE);
//        vib.vibrate(40);
    }
}
