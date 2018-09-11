package com.loubii.account.constants;

import android.graphics.Color;

import com.beiing.leafchart.bean.Line;
import com.beiing.leafchart.bean.PointValue;
import com.beiing.leafchart.bean.SlidingLine;
import com.beiing.leafchart.support.ShowMode;

import java.util.List;

/**
 * @author luo
 * @date 2017/10/30
 */
public class ChartConfig {
    /**
     * 选中线条配置
     */
    public static SlidingLine getSlideingLine() {
        SlidingLine slidingLine = new SlidingLine();
        slidingLine.setSlideLineColor(Color.parseColor("#508a74"))
                .setSlidePointColor(Color.parseColor("#11a06a"))
                .setSlidePointRadius(4);
        slidingLine.setDash(true);
        slidingLine.setOpenSlideSelect(true);
        return slidingLine;
    }

    /**
     *
     */
    public static Line getLine(List<PointValue> pointValues) {
        Line line = new Line(pointValues);
        line.setLineColor(Color.parseColor("#40846d"))
                .setLineWidth(1f)
                .setPointColor(Color.parseColor("#11a06a"))
                .setCubic(false)
                .setPointRadius(3)
                .setFill(true)
                .setHasPoints(true)
                .setFillColor(Color.parseColor("#cc40846d")) //80%不透明度
                .setHasLabels(true)
                .setLabelColor(Color.parseColor("#11a06a"))
                .setLabelRadius(12)
                .setShowMode(ShowMode.PADDING)
                .setLeftModePaddingY(0f)
                .setRightModePaddingY(0f);
        return line;
    }
}
