package com.loubii.account.ui.calendar;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.loubii.account.util.Logger;

import java.util.List;

/**
 *
 */
public class CountDecorator implements DayViewDecorator {
    private List<CountBean> countList;

    public CountDecorator(List<CountBean> countList) {
        this.countList = countList;
    }

    /**
     * 这个方法是先于 decorate() 执行的
     *
     * @param day
     * @return
     */
    @Override
    public boolean shouldDecorate(CalendarDay day) {
        Logger.e(countList.size() + "");
        if (countList.get(day.getDay() - 1).getCount() != 0f)
            return true;
        else
            return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new CountSpan(countList));
    }

    public void setCount(List<CountBean> countList) {
        this.countList = countList;
    }

}