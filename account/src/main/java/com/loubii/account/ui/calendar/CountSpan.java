package com.loubii.account.ui.calendar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.TextPaint;
import android.text.style.LineBackgroundSpan;

import com.loubii.account.constants.ColorParms;
import com.loubii.account.util.DensityUtil;
import com.loubii.account.util.NumUtil;

import java.util.List;

/**
 * 日历每日账单数目显示
 */
public class CountSpan implements LineBackgroundSpan {
    private List<CountBean> countList;

    public CountSpan( List<CountBean> countList) {
        this.countList = countList;
    }

    @Override
    public void drawBackground(Canvas c, Paint p, int left, int right, int top, int baseline, int bottom, CharSequence text, int start, int end, int lnum) {

        TextPaint paint = new TextPaint();
        paint.setAntiAlias(true); //消除锯齿
        paint.setTextSize(DensityUtil.dip2px(9));
        int day = Integer.parseInt(text.toString());
        float count = countList.get(day - 1).getCount();
        if (count < 0f)
            paint.setColor(ColorParms.COLOR_CALENDAR_COUNT_MINUS);
        else
            paint.setColor(ColorParms.COLOR_CALENDAR_SELECT);
        if (countList.get(day - 1).isSelect())
            paint.setColor(Color.WHITE);
        float textWidth =  Layout.getDesiredWidth(NumUtil.getTwoPointFloat(count), paint);
        c.drawText(NumUtil.getTwoPointFloat(count), (right - left) / 2 - textWidth/2, (bottom - top) / 2 + DensityUtil.dip2px(16), paint);
    }
}