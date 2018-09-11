package com.beiing.leafchart.bean;

import android.graphics.Color;
import android.graphics.Path;

import java.util.List;

/**
 * Created by chenliu on 2016/7/14.<br/>
 * 描述：线
 * </br>
 */
public class Line extends ChartData {
    private int lineColor = Color.GRAY; //折线颜色
    private float lineWidth = 1; // 折线的宽度dp
    private boolean hasPoints = true; //是否画圆点

    private boolean hasLines = true; // 是否画线条

    private int pointColor = Color.GRAY;//圆点颜色

    private int pointRadius = 1;//圆点半径dp

    private boolean isCubic; //是否是曲线

    private boolean isFill; // 是否填充

    private int fillColor = 0; // 填充色

    private Path path = new Path();//折线路径



    public Line(List<PointValue> values) {
        this.values = values;
    }

    public boolean isCubic() {
        return isCubic;
    }

    public Line setCubic(boolean cubic) {
        isCubic = cubic;
        return this;
    }

    public boolean isFill() {
        return isFill;
    }

    public Line setFill(boolean fill) {
        isFill = fill;
        return this;
    }

    public int getFillColor() {
        return fillColor;
    }

    public Line setFillColor(int fillColor) {
        this.fillColor = fillColor;
        return this;
    }

    public float getLabelRadius() {
        return labelRadius;
    }

    public int getPointColor() {
        return pointColor;
    }

    public Line setPointColor(int pointColor) {
        this.pointColor = pointColor;
        return this;
    }

    public float getPointRadius() {
        return pointRadius;
    }

    public Line setPointRadius(int pointRadius) {
        this.pointRadius = pointRadius;
        return this;
    }

    public int getLineColor() {
        return lineColor;
    }

    public Line setLineColor(int lineColor) {
        this.lineColor = lineColor;
        return this;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public Line setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
        return this;
    }

    public List<PointValue> getValues() {
        return values;
    }

    public Line setValues(List<PointValue> values) {
        this.values = values;
        return this;
    }

    public boolean isHasPoints() {
        return hasPoints;
    }

    public Line setHasPoints(boolean hasPoints) {
        this.hasPoints = hasPoints;
        return this;
    }

    public boolean isHasLines() {
        return hasLines;
    }

    public Line setHasLines(boolean hasLines) {
        this.hasLines = hasLines;
        return this;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }


}




















