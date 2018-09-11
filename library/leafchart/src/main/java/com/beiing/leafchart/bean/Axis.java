package com.beiing.leafchart.bean;

import android.graphics.Color;
import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenliu on 2016/7/14.<br/>
 * 描述：坐标轴
 * </br>
 */
public class Axis {
    public static final int DEFAULT_TEXT_SIZE_SP = 12;

    public static final float DEFAULT_AXIS_WIDTH_DP = 1;
    public static final int DEFAULT_SCALE_LENGTH = 0;

    /**
     * 刻度集合
     */
    private List<AxisValue> values = new ArrayList<>();

    /**
     * 是否画平行于x、y坐标轴
     */
    private boolean hasLines = true;

    /**
     * 刻度值字体
     */
    private Typeface typeface;

    /**
     * 刻度字体颜色
     */
    private int textColor = Color.LTGRAY;

    /**
     * 刻度值字体大小
     */
    private int textSize = DEFAULT_TEXT_SIZE_SP;

    /**
     * 坐标轴颜色 - x y 轴
     */
    private int axisColor = Color.LTGRAY;

    /**
     * 坐标轴宽度
     */
    private float axisWidth = DEFAULT_AXIS_WIDTH_DP;


    /**
     * 平行于 x 或 y 轴 的坐标轴颜色
     */
    private int axisLineColor = Color.LTGRAY;


    private float  axisLineWidth = DEFAULT_AXIS_WIDTH_DP;


    // 坐标轴起点终点位置
    private float startX;
    private float startY;
    private float stopX;
    private float stopY;

    /**
     * 是否显示刻度
     */
    private boolean isShowText = true;

    /**
     * 刻度尺长度
     */
    private int scaleLength = DEFAULT_SCALE_LENGTH;

    public int getScaleLength() {
        return scaleLength;
    }

    public Axis setScaleLength(int scaleLength) {
        this.scaleLength = scaleLength;
        return this;
    }

    public boolean isShowText() {
        return isShowText;
    }

    public Axis setShowText(boolean showText) {
        isShowText = showText;
        return this;
    }

    public int getAxisLineColor() {
        return axisLineColor;
    }

    public Axis setAxisLineColor(int axisLineColor) {
        this.axisLineColor = axisLineColor;
        return this;
    }

    public float getAxisLineWidth() {
        return axisLineWidth;
    }

    public Axis setAxisLineWidth(float axisLineWidth) {
        this.axisLineWidth = axisLineWidth;
        return this;
    }

    public float getStartX() {
        return startX;
    }

    public Axis setStartX(float startX) {
        this.startX = startX;
        return this;
    }

    public float getStartY() {
        return startY;
    }

    public Axis setStartY(float startY) {
        this.startY = startY;
        return this;
    }

    public float getStopX() {
        return stopX;
    }

    public Axis setStopX(float stopX) {
        this.stopX = stopX;
        return this;
    }

    public float getStopY() {
        return stopY;
    }

    public Axis setStopY(float stopY) {
        this.stopY = stopY;
        return this;
    }

    public Axis(List<AxisValue> values) {
        this.values = values;
    }

    public List<AxisValue> getValues() {
        return values;
    }

    public void setValues(List<AxisValue> values) {
        this.values = values;
    }

    public boolean isHasLines() {
        return hasLines;
    }

    public Axis setHasLines(boolean hasLines) {
        this.hasLines = hasLines;
        return this;
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }

    public int getTextColor() {
        return textColor;
    }

    public Axis setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public int getTextSize() {
        return textSize;
    }

    public Axis setTextSize(int textSize) {
        this.textSize = textSize;
        return this;
    }

    public int getAxisColor() {
        return axisColor;
    }

    public Axis setAxisColor(int axisColor) {
        this.axisColor = axisColor;
        return this;
    }

    public float getAxisWidth() {
        return axisWidth;
    }

    public Axis setAxisWidth(float axisWidth) {
        this.axisWidth = axisWidth;
        return this;
    }
}
