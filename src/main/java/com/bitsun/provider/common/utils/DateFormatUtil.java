package com.bitsun.provider.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期格式化工具
 *
 * @author rico 2016年5月7日
 */
public class DateFormatUtil {

    // 时间格式化格式
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_MONTH_PATTERN = "yyyy-MM";
    public static final String DATE_YEAR_PATTERN = "yyyy";
    public static final String DATE_PATTERN_CN = "yyyy年MM月dd日";
    public static final String DATE_PATTERN_DAY = "yyyyMMdd";

    // 周天数
    private static final int DAYS_OF_WEEK = 7;

    /**
     * 日期转换字符串
     *
     * @param date    日期
     * @param pattern 时间格式
     * @return
     */
    public static String toString(Date date, String pattern) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 字符串转换为日期
     *
     * @param date    字符串格式日期
     * @param pattern 转换类型
     * @return
     */
    public static Date toDate(String date, String pattern) throws ParseException {
        if (StringUtils.isBlank(date)) {
            throw new IllegalArgumentException("The date must not be null");
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(date);
    }

    /**
     * 获取一天开始时间
     *
     * @param date
     * @return
     */
    public static Date getFirstOfDateTime(Date date) {
        return DateFormatUtil.convertDateToStartOrEnd(date, true);
    }

    /**
     * 获取一天结束时间
     *
     * @param date
     * @return
     */
    public static Date getLastOfDateTime(Date date) {
        return DateFormatUtil.convertDateToStartOrEnd(date, false);
    }

    /**
     * 时间格式化，获取一天开始、结束时间
     *
     * @param date
     * @param isBegin
     * @return
     */
    private static Date convertDateToStartOrEnd(Date date, boolean isBegin) {
        return convertDateToStartOrEnd(null, date, isBegin);
    }

    /**
     * 时间格式化，获取一天开始、结束时间
     *
     * @param date    格式化时间
     * @param isBegin true:格式为开始时间，false:格式化为结束时间
     * @return
     */
    private static Date convertDateToStartOrEnd(Calendar cal, Date date, boolean isBegin) {
        if (cal == null) {
            cal = Calendar.getInstance();
        }
        cal.setTime(date);

        if (isBegin) {
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
        } else {
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
        }

        return cal.getTime();
    }

    /**
     * 获取一周开始时间 00:00:00
     *
     * @param date
     * @return
     */
    public static Date getFirstDateTimeOfWeek(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        Calendar cal = Calendar.getInstance();
        convertDateToStartOrEnd(cal, date, true);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, 1 - dayOfWeek);

        return cal.getTime();
    }

    /**
     * 获取一周结束时间 23:23:59
     *
     * @param date
     * @return
     */
    public static Date getLastDateTimeOfWeek(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        Calendar cal = Calendar.getInstance();
        convertDateToStartOrEnd(cal, date, false);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, DAYS_OF_WEEK - dayOfWeek);

        return cal.getTime();
    }

    /**
     * 获取一个月开始时间
     *
     * @param date
     * @return
     */
    public static Date getFirstDateTimeOfMonth(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        Calendar cal = Calendar.getInstance();
        convertDateToStartOrEnd(cal, date, true);
        cal.set(Calendar.DATE, 1);

        return cal.getTime();
    }

    /**
     * 获取一个月结束时间
     *
     * @param date
     * @return
     */
    public static Date getLastDateTimeOfMonth(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        Calendar cal = Calendar.getInstance();
        convertDateToStartOrEnd(cal, date, false);

        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);

        return cal.getTime();
    }

    /**
     * 根据年月输出每月最后一天.
     *
     * @param year  the year
     * @param month the month
     * @return the CN last date time of month
     */
    public static String getLastDateTimeOfMonth(int year, int month, String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        Date date = cal.getTime();
        return toString(getLastDateTimeOfMonth(date), pattern);
    }

    /**
     * 获取年份
     *
     * @return
     */
    public static int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        return getYearOfDate(cal);
    }

    /**
     * 获取年份
     *
     * @param date
     * @return
     */
    public static int getYearOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getYearOfDate(cal);
    }

    /**
     * 获取年份
     *
     * @param cal
     * @return
     */
    public static int getYearOfDate(Calendar cal) {
        return cal.get(Calendar.YEAR);
    }

    /**
     * 读取月份
     * @param date
     * @return
     */
    public static int getMonthOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getMonthOfDate(cal);
    }

    /**
     *
     * @param date
     * @return
     */
    public static int getMonthOfDate2(Date date) {
        return 1 + getMonthOfDate(date);
    }

    /**
     * 读取月份
     * @param cal
     * @return
     */
    public static int getMonthOfDate(Calendar cal) {
        return cal.get(Calendar.MONTH);
    }



    /**
     * 默认日期格式化格式
     *
     * @return
     */
    public static Map<String, String> defaultDateSeparators() {
        Map<String, String> separators = new HashMap<String, String>();

        separators.put("year", "年");
        separators.put("month", "月");
        separators.put("date", "日");

        separators.put("hour", ":");
        separators.put("minute", ":");
        separators.put("second", ":");

        return separators;
    }


    /**
     * 计算2个时间的分钟差
     */
    public static Integer getDateMin(Date startDate,Date nowDate){
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;

        // 获得两个时间的毫秒时间差异
        long diff = nowDate.getTime() - startDate.getTime();

        // 计算差多少分钟
        long min = diff / nm;

        return NumberUtil.toInteger(min);
    }

    public static void main(String[] args) throws Exception{
        System.out.print(getDateMin(toDate("2019-07-21 10:22:45","yyyy-MM-dd HH:mm:ss"),toDate("2019-07-23 10:26:45","yyyy-MM-dd HH:mm:ss")));
    }

}