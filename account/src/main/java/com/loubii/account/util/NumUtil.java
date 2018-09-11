package com.loubii.account.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author luo
 * @date 2017/8/29
 */
public class NumUtil {
    public static String getTwoPointFloat(float num) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        return decimalFormat.format(num);
    }


    /**
     * 保留几位小数
     * @param num 传float值
     * @param point 保留小数位数
     * @return
     */
    public static float getPointFloat(float num, int point) {
        BigDecimal bigDecimal = new BigDecimal(num);
        //   b.setScale(2,  BigDecimal.ROUND_HALF_UP) 表明四舍五入，保留两位小数
        return bigDecimal.setScale(point, BigDecimal.ROUND_HALF_UP).floatValue();
    }
}
