package com.loubii.account.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author luo
 * @date 2017/8/21
 */
public class TimeUtil {
    public static String[] WEEK = {"周天", "周一", "周二", "周三", "周四", "周五", "周六"};
    public static final int WEEKDAYS = 7;

    /**
     * 获取前n天日期、后n天日期
     *
     * @param distanceDay 前几天 如获取前7天日期则传-7即可；如果后7天则传7
     * @return
     */
    public static String getLastDateStr(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("MM月dd日");
        Date beginDate = new Date();
        Calendar calendar = Calendar.getInstance();
        //calendar.set(2017, 0, 7);
        calendar.setTime(beginDate);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate) + "      " + getWeekByDate(endDate);
    }

    public static Date getLastDate(int distanceDay) {
        Date beginDate = new Date();
        Calendar calendar = Calendar.getInstance();
        //calendar.set(2017, 0, 7);
        calendar.setTime(beginDate);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + distanceDay);
        Date endDate = calendar.getTime();
        return endDate;
    }

    /**
     * 根据date获取前几天或后几天
     *
     * @param date
     * @param distanceDay
     * @return
     */
    public static Date getDistanceDate(Date date, int distanceDay) {
        //Date beginDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + distanceDay);
        Date endDate = calendar.getTime();
        return endDate;
    }

    /**
     * 获取当前月几个月之前或之后的月份
     *
     * @param date
     * @param distanceDay 如一个月之前传-1，一个月之后传1
     * @return
     */
    public static Date getMonthAgo(Date date, int distanceDay) {
        Calendar calendar = Calendar.getInstance();
        //calendar.set(2017, 0, 7);
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, distanceDay);
        Date endDate = calendar.getTime();
        return endDate;
    }


    //获取某个日期的开始时间
    public static Date getDayStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    //获取某个日期的结束时间
    public static Date getDayEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取当前周第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }

    /**
     * 获取当前周最后一天
     *
     * @param date
     * @return
     */
    public static Date getEndDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getFirstDayOfWeek(date));
        cal.add(Calendar.DAY_OF_WEEK, 5);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }


//    /**
//     * 获取当前周第一天
//     * @param date
//     * @return
//     */
//    public static Date getFirstDayOfWeek(Date date) {
//        Calendar cDay = Calendar.getInstance();
//        cDay.setTime(date);
//        cDay.set(Calendar.HOUR, 0);
//        cDay.set(Calendar.MINUTE, 0);
//        final int lastDay = cDay.getActualMinimum(Calendar.DAY_OF_WEEK);
//        Date lastDate = cDay.getTime();
//        lastDate.setDate(lastDay);
//        return lastDate;
//    }
//
//    /**
//     * 获取当前周最后一天
//     * @param date
//     * @return
//     */
//    public static Date getEndDayOfWeek(Date date) {
//        Calendar cDay = Calendar.getInstance();
//        cDay.setTime(date);
//        cDay.set(Calendar.HOUR, 23);
//        cDay.set(Calendar.MINUTE, 59);
//        final int lastDay = cDay.getActualMaximum(Calendar.DAY_OF_WEEK);
//        Date lastDate = cDay.getTime();
//        lastDate.setDate(lastDay);
//        return lastDate;
//    }

    /**
     * 获取当前月第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        cDay.set(Calendar.HOUR_OF_DAY, 0);
        cDay.set(Calendar.MINUTE, 0);
        final int lastDay = cDay.getActualMinimum(Calendar.DAY_OF_MONTH);
        Date lastDate = cDay.getTime();
        lastDate.setDate(lastDay);
        return lastDate;
    }

    /**
     * 获取当前月最后一天
     *
     * @param date
     * @return
     */
    public static Date getEndDayOfMonth(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        cDay.set(Calendar.HOUR_OF_DAY, 23);
        cDay.set(Calendar.MINUTE, 59);
        final int lastDay = cDay.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date lastDate = cDay.getTime();
        lastDate.setDate(lastDay);
        return lastDate;
    }

    /**
     * 获取当前月第一天
     *
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(int month) {
        Calendar cDay = Calendar.getInstance();
        cDay.set(Calendar.MONTH, month);
        cDay.set(Calendar.HOUR_OF_DAY, 0);
        cDay.set(Calendar.MINUTE, 0);
        final int lastDay = cDay.getActualMinimum(Calendar.DAY_OF_MONTH);
        Date lastDate = cDay.getTime();
        lastDate.setDate(lastDay);
        return lastDate;
    }

    /**
     * 获取当前月最后一天
     *
     * @param month
     * @return
     */
    public static Date getEndDayOfMonth(int month) {
        Calendar cDay = Calendar.getInstance();
        cDay.set(Calendar.MONTH, month);
        cDay.set(Calendar.HOUR_OF_DAY, 23);
        cDay.set(Calendar.MINUTE, 59);
        final int lastDay = cDay.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date lastDate = cDay.getTime();
        lastDate.setDate(lastDay);
        return lastDate;
    }

    /**
     * 获取当前年第一天
     *
     * @param year
     * @return
     */
    public static Date getFirstDayOfYear(int year) {
        Calendar cDay = Calendar.getInstance();
        cDay.set(Calendar.YEAR, year);
        cDay.set(Calendar.MONTH, 0);
        cDay.set(Calendar.HOUR_OF_DAY, 0);
        cDay.set(Calendar.MINUTE, 0);
        final int lastDay = cDay.getActualMinimum(Calendar.DAY_OF_MONTH);
        Date lastDate = cDay.getTime();
        lastDate.setDate(lastDay);
        return lastDate;
    }

    /**
     * 获取当前年最后一天
     *
     * @param year
     * @return
     */
    public static Date getEndDayOfYear(int year) {
        Calendar cDay = Calendar.getInstance();
        cDay.set(Calendar.YEAR, year);
        cDay.set(Calendar.MONTH, 11);
        cDay.set(Calendar.HOUR_OF_DAY, 23);
        cDay.set(Calendar.MINUTE, 59);
        final int lastDay = cDay.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date lastDate = cDay.getTime();
        lastDate.setDate(lastDay);
        return lastDate;
    }

    /**
     * 获取date在一年中的天数
     * @param date
     * @return
     */
    public static int getDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取date在一月中的天数
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前时间
     * yyyy-MM-dd日   HH:mm
     *
     * @return
     */
    public static String getNowDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd日   HH:mm");
        Date curDate = new Date();//获取当前时间
        return formatter.format(curDate);
    }

    public static int getNowDateHour() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getNowDateMinute() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MINUTE);
    }

    public static int getWeekOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 通过一年中的第几周获取date
     *
     * @param week
     * @return
     */
    public static Date getDateByWeek(int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        return calendar.getTime();
    }


    /**
     * calendar从0开始计算月份，所以加1
     *
     * @param date
     * @return
     */
    public static int getMonthOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 日期转星期
     *
     * @param date
     * @return
     */
    public static String getWeekByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayIndex < 1 || dayIndex > WEEKDAYS) {
            return null;
        }

        return WEEK[dayIndex - 1];
    }

    /**
     * 获取本年的开始时间
     *
     * @param date
     * @return
     */
    public static Date getBeginDayOfYear(Date date) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(date);
        cDay.set(Calendar.YEAR, cDay.get(Calendar.YEAR));
        cDay.set(Calendar.MONTH, Calendar.JANUARY);
        cDay.set(Calendar.DATE, 1);
        cDay.set(Calendar.HOUR_OF_DAY, 0);
        cDay.set(Calendar.MINUTE, 0);
        return cDay.getTime();
    }

    /**
     * 把符合日期格式的字符串转换为日期类型
     *
     * @param dateStr
     * @return
     */
    public static java.util.Date string2Date(String dateStr, String format) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            formater.setLenient(false);// 严格解析
            d = formater.parse(dateStr);
        } catch (Exception e) {
            d = null;
        } finally {
            formater = null;
        }
        return d;
    }

    /**
     * 把日期转换为字符串
     *
     * @param date
     * @param format exsample: yyyy-MM-dd日   HH:mm
     * @return
     */
    public static String date2String(java.util.Date date, String format) {
        if (date == null) {
            return "";
        }
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            result = formater.format(date);
        } catch (Exception e) {
            result = "";
        } finally {
            formater = null;
        }
        return result;
    }

}
