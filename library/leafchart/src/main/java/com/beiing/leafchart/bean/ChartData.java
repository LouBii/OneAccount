package com.beiing.leafchart.bean;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenliu on 2016/7/17 0017<br/>.
 * 描述：不同类型图表相同属性
 */
public class ChartData {

    protected List<PointValue> values = new ArrayList<>();

    protected boolean hasLabels = false;// 是否画标签

    protected int labelColor = Color.DKGRAY;//标签背景色

    protected float labelRadius = 3; //dp

    protected int showMode = 0;//显示模式

    protected float leftModePaddingY = -1f; //如果为padding模式时最左边y坐标（比值）

    protected float rightModePaddingY = -1f;

    protected float leftModePointY; //如果为padding模式时最左边y坐标（真实）

    protected float rightModePointY;

    protected float leftModePointX; //如果为padding模式时最左边x坐标（真实）

    protected float rightModePointX;

    public float getLeftModePointX() {
        return leftModePointX;
    }

    public void setLeftModePointX(float leftModePointX) {
        this.leftModePointX = leftModePointX;
    }

    public float getRightModePointX() {
        return rightModePointX;
    }

    public void setRightModePointX(float rightModePointX) {
        this.rightModePointX = rightModePointX;
    }

    public float getLeftModePointY() {
        return leftModePointY;
    }

    public ChartData setLeftModePointY(float leftModePointY) {
        this.leftModePointY = leftModePointY;
        return this;
    }

    public float getRightModePointY() {
        return rightModePointY;
    }

    public ChartData setRightModePointY(float rightModePointY) {
        this.rightModePointY = rightModePointY;
        return this;
    }

    public int getShowMode() {
        return showMode;
    }

    public ChartData setShowMode(int showMode) {
        this.showMode = showMode;
        return this;
    }

    public float getRightModePaddingY() {
        return rightModePaddingY;
    }

    public ChartData setRightModePaddingY(float rightModePaddingY) {
        this.rightModePaddingY = rightModePaddingY;
        return this;
    }

    public float getLeftModePaddingY() {
        return leftModePaddingY;
    }

    public ChartData setLeftModePaddingY(float leftModePaddingY) {
        this.leftModePaddingY = leftModePaddingY;
        return this;
    }

    public List<PointValue> getValues() {
        return values;
    }

    public ChartData setValues(List<PointValue> values) {
        this.values = values;
        return this;
    }

    public boolean isHasLabels() {
        return hasLabels;
    }

    public ChartData setHasLabels(boolean hasLabels) {
        this.hasLabels = hasLabels;
        return this;
    }

    public int getLabelColor() {
        return labelColor;
    }

    public ChartData setLabelColor(int labelColor) {
        this.labelColor = labelColor;
        return this;
    }

    public float getLabelRadius() {
        return labelRadius;
    }

    public ChartData setLabelRadius(float labelRadius) {
        this.labelRadius = labelRadius;
        return this;
    }
}


