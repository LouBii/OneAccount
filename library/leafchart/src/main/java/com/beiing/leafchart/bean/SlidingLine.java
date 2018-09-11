package com.beiing.leafchart.bean;

import android.graphics.Color;

/**
 * Created by chenliu on 2016/12/19.<br/>
 * 描述：滑动标尺线
 * </br>
 */

public class SlidingLine {

    /**是否开启滑动标尺**/
    private boolean isOpenSlideSelect = true;

    /**是否是虚线**/
    private boolean isDash = true;

    /**移动标尺线颜色**/
    private int slideLineColor = Color.YELLOW;

    /**移动标尺线宽度**/
    private float slideLineWidth = 1;

    /**移动标尺线顶端圆点半径**/
    private float slidePointRadius = 3;

    /**移动标尺线顶端圆点颜色**/
    private int slidePointColor = Color.YELLOW;

    public SlidingLine setOpenSlideSelect(boolean openSlideSelect) {
        isOpenSlideSelect = openSlideSelect;
        return this;
    }

    public SlidingLine setDash(boolean dash) {
        isDash = dash;
        return this;
    }

    public SlidingLine setSlideLineColor(int slideLineColor) {
        this.slideLineColor = slideLineColor;
        return this;
    }

    public SlidingLine setSlideLineWidth(float slideLineWidth) {
        this.slideLineWidth = slideLineWidth;
        return this;
    }

    public SlidingLine setSlidePointRadius(float slidePointRadius) {
        this.slidePointRadius = slidePointRadius;
        return this;
    }

    public SlidingLine setSlidePointColor(int slidePointColor) {
        this.slidePointColor = slidePointColor;
        return this;
    }

    public boolean isOpenSlideSelect() {
        return isOpenSlideSelect;
    }

    public boolean isDash() {
        return isDash;
    }

    public int getSlideLineColor() {
        return slideLineColor;
    }

    public float getSlideLineWidth() {
        return slideLineWidth;
    }

    public float getSlidePointRadius() {
        return slidePointRadius;
    }

    public int getSlidePointColor() {
        return slidePointColor;
    }
}
