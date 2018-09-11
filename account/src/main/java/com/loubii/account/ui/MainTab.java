package com.loubii.account.ui;

import com.loubii.account.R;
import com.loubii.account.ui.fragments.FragmentBill;
import com.loubii.account.ui.fragments.FragmentCard;
import com.loubii.account.ui.fragments.FragmentCenter;
import com.loubii.account.ui.fragments.FragmentChart;

public enum MainTab {


    BILL(0, R.string.tab_name_bill, R.drawable.tab_bill_selector,
         FragmentBill.class),

    CHART(1, R.string.tab_name_chart, R.drawable.tab_chart_selector,
          FragmentChart.class),

    ADD(2, R.string.tab_name_add, R.drawable.tab_add_selector,
           null),

    CARD(3, R.string.tab_name_card, R.drawable.tab_card_selector,
            FragmentCard.class),

    ME(4, R.string.tab_name_me, R.drawable.tab_me_selector,
       FragmentCenter.class);

    private int idx;
    private int resName;
    private int resIcon;
    private Class<?> clz;

    private MainTab(int idx, int resName, int resIcon, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;

    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }

}
