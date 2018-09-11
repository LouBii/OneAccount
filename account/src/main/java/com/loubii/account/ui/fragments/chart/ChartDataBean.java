package com.loubii.account.ui.fragments.chart;

/**
 * @author luo
 * @date 2017/9/22
 */
public class ChartDataBean implements Comparable<ChartDataBean> {
    //分类名称
    private String name;
    //图片资源位置
    private int imgRes;
    //笔数
    private int count;
    //百分比
    private float precent;
    //总钱数
    private float total;

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrecent() {
        return precent;
    }

    public void setPrecent(float precent) {
        this.precent = precent;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    /**
     * 按照记账总额倒序排序 （从高到低）
     * @param o
     * @return
     */
    @Override
    public int compareTo(ChartDataBean o) {
        if (this.total > o.total)
            return -1;
        else if (this.total < o.total)
            return 1;
        else
            return 0;
    }

}
