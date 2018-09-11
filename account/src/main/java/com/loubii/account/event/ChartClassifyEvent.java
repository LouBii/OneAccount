package com.loubii.account.event;

/**
 * 图表分类选中条目
 * @author luo
 * @date 2017/11/3
 */
public class ChartClassifyEvent extends BaseEvent{
    private String classify;

    public ChartClassifyEvent(String classify) {
        this.classify = classify;
    }

    public String getMessage() {
        return classify;
    }

    public void setMessage(String classify) {
        this.classify = classify;
    }
}
