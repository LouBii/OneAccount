package com.beiing.leafchart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.beiing.leafchart.bean.Line;
import com.beiing.leafchart.renderer.LeafLineRenderer;

import java.util.List;

/**
 * Created by chenliu on 2016/7/15.<br/>
 * 描述：折线图
 * </br>
 */
public class LeafLineChart extends AbsLeafChart {

    private List<Line> lines;

    private LeafLineRenderer leafChartRenderer;

    public LeafLineChart(Context context) {
        this(context, null, 0);
    }

    public LeafLineChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeafLineChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initRenderer() {
        leafChartRenderer = new LeafLineRenderer(mContext, this);
    }

    @Override
    protected void setRenderer() {
         super.setRenderer(leafChartRenderer);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 设置点所占比重
     */
    @Override
    protected void resetPointWeight() {
        if (lines != null) {
            for (int i = 0, size = lines.size(); i < size; i++) {
                super.resetPointWeight(lines.get(i));
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (lines != null && lines.size() > 0) {
            Line line;
            for (int i = 0, size = lines.size(); i < size; i++) {
                line = lines.get(i);
                if(line != null){
                    if(line.isCubic()) {
                        leafChartRenderer.drawCubicPath(canvas, line);
                    } else {
                        leafChartRenderer.drawLines(canvas, line);
                    }
                    if(line.isFill()){
                        //填充
                        leafChartRenderer.drawFillArea(canvas, line, axisX);
                    }

                    leafChartRenderer.drawPoints(canvas, line);
                }

                if (line != null && line.isHasLabels()) {
                    leafChartRenderer.drawLabels(canvas, line, axisY);
                }
            }
        }
    }

    /**
     * 带动画的绘制
     * @param duration
     */
    public void showWithAnimation(int duration){
        leafChartRenderer.showWithAnimation(duration);
    }

    public void show(){
        showWithAnimation(0);
    }

    public void setChartData(List<Line> chartDatas) {
        lines = chartDatas;
        resetPointWeight();
    }

    public List<Line> getChartData() {
        return lines;
    }

}