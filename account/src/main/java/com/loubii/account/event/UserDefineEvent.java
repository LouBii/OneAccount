package com.loubii.account.event;

import com.loubii.account.bean.RecycleClassifyPagerBean;

/**
 * @author luo
 * @date 2017/8/23
 */
public class UserDefineEvent {
    private RecycleClassifyPagerBean bean;

    public UserDefineEvent(RecycleClassifyPagerBean bean) {
        this.bean = bean;
    }

    public RecycleClassifyPagerBean getBean() {
        return bean;
    }

    public void setBean(RecycleClassifyPagerBean bean) {
        this.bean = bean;
    }
}
