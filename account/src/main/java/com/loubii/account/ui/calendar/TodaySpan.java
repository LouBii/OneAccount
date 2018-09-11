package com.loubii.account.ui.calendar;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

import com.loubii.account.constants.ColorParms;
import com.loubii.account.util.DensityUtil;

/**
 * 今天选中圆环背景
 */
public class TodaySpan implements LineBackgroundSpan {
    @Override
    public void drawBackground(Canvas c, Paint p, int left, int right, int top, int baseline, int bottom, CharSequence text, int start, int end, int lnum) {
        Paint paint = new Paint();
        paint.setAntiAlias(true); //消除锯齿
        paint.setStyle(Paint.Style.STROKE);//绘制空心圆或 空心矩形
        int ringWidth = DensityUtil.dip2px(1);//圆环宽度
        //绘制圆环
        paint.setColor(ColorParms.COLOR_CALENDAR_SELECT);
        paint.setStrokeWidth(ringWidth);
        c.drawCircle((right - left) / 2, (bottom - top) / 2 /*+ CircleBackGroundSpan.dip2px(4)*/,
                /*CircleBackGroundSpan.dip2px(20),*/
                (int) (bottom / 0.85),
                paint);
    }
}
