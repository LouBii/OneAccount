package com.beiing.leafchart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.beiing.leafchart.bean.ChartData;
import com.beiing.leafchart.bean.Square;
import com.beiing.leafchart.renderer.LeafSquareRenderer;
import com.beiing.leafchart.support.LeafUtil;


/**
 * Created by chenliu on 2016/9/10.<br/>
 * 描述：直方图
 * </br>
 */
public class LeafSquareChart extends AbsLeafChart {

    private Square square;

    LeafSquareRenderer leafSquareRenderer;

    public LeafSquareChart(Context context) {
        this(context, null, 0);
    }

    public LeafSquareChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeafSquareChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if(startMarginX == 0) startMarginX = (int) LeafUtil.dp2px(context, 20);
    }

    @Override
    protected void initRenderer() {
        leafSquareRenderer = new LeafSquareRenderer(mContext, this);
    }

    @Override
    protected void setRenderer() {
        super.setRenderer(leafSquareRenderer);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        leafSquareRenderer.drawSquares(canvas, square, axisX);

        if (square != null && square.isHasLabels()) {
            leafSquareRenderer.drawLabels(canvas, square, axisY);
        }
    }

    @Override
    protected void resetPointWeight() {
        super.resetPointWeight(square);
    }

    public void setChartData(ChartData chartData) {
        this.square = (Square) chartData;
        resetPointWeight();
    }

    public ChartData getChartData() {
        return square;
    }
}
