package com.beiing.leafchart.support;

/**
 * Created by chenliu on 2016/12/12.<br/>
 * 描述：
 * </br>
 */
public interface OnPointSelectListener {

    /**
     * @param position  x轴位置
     * @param xLabel    x轴对应刻度值
     * @param value     对应点数值
     */
    void onPointSelect(int position, String xLabel, String value);

}
