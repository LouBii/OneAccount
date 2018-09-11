package com.beiing.leafchart.renderer;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;

import com.beiing.leafchart.bean.Axis;
import com.beiing.leafchart.bean.AxisValue;
import com.beiing.leafchart.bean.ChartData;
import com.beiing.leafchart.bean.Line;
import com.beiing.leafchart.bean.PointValue;
import com.beiing.leafchart.support.LeafUtil;

import java.util.List;

/**
 * Created by chenliu on 2017/1/12.<br/>
 * 描述：
 * </br>
 */

public class OutsideLineRenderer extends AbsRenderer {

    /**填充画笔**/
    private Paint fillPaint;

    private PathMeasure measure;

    /**
     * 动画结束标志
     */
    private boolean isAnimateEnd;

    /**
     * 是否开始绘制，防止动画绘制之前绘制一次
     */
    private boolean isShow;

    private float phase;

    private LinearGradient fillShader;

    public OutsideLineRenderer(Context context, View view) {
        super(context, view);
    }

    @Override
    protected void initPaint() {
        super.initPaint();
        fillPaint = new Paint();
        fillPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * 画坐标轴 刻度值
     * @param canvas
     */
    public void drawCoordinateText(Canvas canvas, Axis axisX, Axis axisY, int moveX) {
        if(axisX != null && axisY != null){
            //////// X 轴
            // 1.刻度
            coordPaint.setColor(axisX.getTextColor());
            coordPaint.setTextSize(LeafUtil.sp2px(mContext, axisX.getTextSize()));

            Paint.FontMetrics fontMetrics = coordPaint.getFontMetrics(); // 获取标题文字的高度（fontMetrics.descent - fontMetrics.ascent）
            float textH = fontMetrics.descent - fontMetrics.ascent;

            List<AxisValue> valuesX = axisX.getValues();
            if(axisX.isShowText()){
                for (int i = 0; i < valuesX.size(); i++) {
                    AxisValue value = valuesX.get(i);
                    if(value.isShowLabel()){
                        float textW = coordPaint.measureText(value.getLabel());
                        canvas.drawText(value.getLabel(), value.getPointX() - textW / 2 + moveX,  value.getPointY() - textH / 2, coordPaint);
                    }
                }
            }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            /////// Y 轴
            coordPaint.setColor(axisY.getTextColor());
            coordPaint.setTextSize(LeafUtil.sp2px(mContext, axisY.getTextSize()));

            List<AxisValue> valuesY = axisY.getValues();
            if(axisY.isShowText()){
                for (AxisValue value : valuesY){
                    float textW = coordPaint.measureText(value.getLabel());
                    float pointx = value.getPointX() - 1.1f * textW;
                    canvas.drawText(value.getLabel(), pointx , value.getPointY(), coordPaint);
                }
            }
        }
    }

    /**
     * 画折线
     *
     * @param canvas
     */
    public void drawLines(Canvas canvas, Line line, Axis axisY, int moveX) {
        if(line != null && isShow){
            linePaint.setColor(line.getLineColor());
            linePaint.setStrokeWidth(LeafUtil.dp2px(mContext, line.getLineWidth()));
            linePaint.setStyle(Paint.Style.STROKE);
            List<PointValue> values = line.getValues();
            Path path = line.getPath();
            int size = values.size();
            for (int i = 0; i < size; i++) {
                PointValue point = values.get(i);
                if(i == 0)  path.moveTo(point.getOriginX() + moveX, point.getOriginY());
                else  path.lineTo(point.getOriginX() + moveX, point.getOriginY());
            }

            measure = new PathMeasure(path, false);
            linePaint.setPathEffect(createPathEffect(measure.getLength(), phase, 0.0f));
            canvas.save(Canvas.CLIP_SAVE_FLAG);
            canvas.clipRect(axisY.getStartX(), 0, mWidth, mHeight);
            canvas.drawPath(path, linePaint);
            canvas.restore();
        }
    }


    /**
     * 填充
     * @param canvas
     */
    public void drawFillArea(Canvas canvas, Line line, Axis axisX, int moveX) {
        //继续使用前面的 path
        if(line != null && line.getValues().size() > 1 && isShow){
            List<PointValue> values = line.getValues();
            PointValue firstPoint = values.get(0);
            float firstX = firstPoint.getOriginX();

            Path path = line.getPath();
            PointValue lastPoint = values.get(values.size() - 1);
            float lastX = lastPoint.getOriginX();
            path.lineTo(lastX + moveX, axisX.getStartY());
            path.lineTo(firstX + moveX, axisX.getStartY());
            path.close();

            if(fillShader == null){
                fillShader = new LinearGradient(0, 0, 0, mHeight, line.getFillColor(), Color.TRANSPARENT, Shader.TileMode.CLAMP);
                fillPaint.setShader(fillShader);
            }

            if(line.getFillColor() == 0)
                fillPaint.setAlpha(100);
            else
                fillPaint.setColor(line.getFillColor());

            canvas.save(Canvas.CLIP_SAVE_FLAG);
            canvas.clipRect(firstX, 0, phase * (lastX - firstX) + firstX + moveX, mHeight);
            canvas.drawPath(path, fillPaint);
            canvas.restore();
            path.reset();
        }
    }

    /**
     * 画圆点
     * @param canvas
     */
    public void drawPoints(Canvas canvas, Line line, Axis axisY, int moveX) {
        if (line != null && line.isHasPoints() && isShow) {
            List<PointValue> values = line.getValues();
            float radius = LeafUtil.dp2px(mContext, line.getPointRadius());
            float strokeWidth = LeafUtil.dp2px(mContext, 1);
            PointValue point;
            canvas.save(Canvas.CLIP_SAVE_FLAG);
            canvas.clipRect(axisY.getStartX(), 0, mWidth, mHeight);
            for (int i = 0, size = values.size(); i < size; i++) {
                point = values.get(i);
                labelPaint.setStyle(Paint.Style.FILL);
                labelPaint.setColor(line.getPointColor());
                canvas.drawCircle(point.getOriginX() + moveX, point.getOriginY(),
                        radius , labelPaint);
                labelPaint.setStyle(Paint.Style.STROKE);
                labelPaint.setColor(Color.WHITE);
                labelPaint.setStrokeWidth(strokeWidth);
                canvas.drawCircle(point.getOriginX() + moveX, point.getOriginY(),
                        radius , labelPaint);
            }
            canvas.restore();
        }
    }

    public void drawLabels(Canvas canvas, ChartData chartData, Axis axisY, int moveX) {
        if(isAnimateEnd){
            if (chartData != null) {
                if(chartData.isHasLabels()){
                    labelPaint.setTextSize(LeafUtil.sp2px(mContext, 12));
                    List<PointValue> values = chartData.getValues();
                    int size = values.size();
                    canvas.save(Canvas.CLIP_SAVE_FLAG);
                    canvas.clipRect(axisY.getStartX(), 0, mWidth, mHeight);
                    for (int i = 0; i < size; i++) {
                        PointValue point = values.get(i);
                        String label = point.getLabel();
                        Rect bounds = new Rect();
                        int length = label.length();
                        labelPaint.getTextBounds(label, 0, length, bounds);

                        float textW = bounds.width();
                        float textH = bounds.height();
                        float left, top, right, bottom;
                        if(length == 1){
                            left = point.getOriginX() - textW * 2.2f;
                            right = point.getOriginX() + textW * 2.2f;
                        }  else if(length == 2){
                            left = point.getOriginX() - textW * 1.0f;
                            right = point.getOriginX() + textW * 1.0f;
                        } else {
                            left = point.getOriginX() - textW * 0.6f;
                            right = point.getOriginX() + textW * 0.6f;
                        }
                        top = point.getOriginY() - 2.5f*textH;
                        bottom = point.getOriginY() - 0.5f*textH;

                        //控制位置
                        if(left < axisY.getStartX()){
                            left = axisY.getStartX();
                            right += left;
                        }
                        if(top < 0){
                            top = topPadding;
                            bottom += topPadding;
                        }
                        if(right > mWidth){
                            right -= rightPadding;
                            left -= rightPadding;
                        }


                        RectF rectF = new RectF(left + moveX, top, right + moveX, bottom);
                        float labelRadius = LeafUtil.dp2px(mContext,chartData.getLabelRadius());
                        labelPaint.setColor(chartData.getLabelColor());
                        labelPaint.setStyle(Paint.Style.FILL);
                        canvas.drawRoundRect(rectF, labelRadius, labelRadius, labelPaint);

                        //drawText
                        labelPaint.setColor(Color.WHITE);
                        float xCoordinate = left + (right - left - textW) / 2 + moveX;
                        float yCoordinate = bottom - (bottom - top - textH) / 2 ;
                        canvas.drawText(point.getLabel(), xCoordinate, yCoordinate, labelPaint);
                    }
                    canvas.restore();
                }
            }
        }
    }

    /**
     * 带动画的绘制
     * @param duration
     */
    public void showWithAnimation(int duration){
        isAnimateEnd = false;
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "phase", 0.0f, 1.0f);
        animator.setDuration(duration);
        animator.start();
        isShow = true;

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                phase = (float) animation.getAnimatedValue();
            }
        });

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimateEnd = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    //showWithAnimation动画开启后会调用该方法
    public void setPhase(float phase) {
        chartView.invalidate();
    }

    private PathEffect createPathEffect(float pathLength, float phase, float offset) {
        return new DashPathEffect(new float[] { phase * pathLength, pathLength }, 0);
    }


}
