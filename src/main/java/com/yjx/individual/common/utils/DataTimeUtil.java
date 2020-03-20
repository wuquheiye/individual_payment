package com.yjx.individual.common.utils;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public abstract class DataTimeUtil {
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormat.forPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_PATTERN = DateTimeFormat.forPattern("HH:mm");

    /**
     * 在指定日期的基础中增加或减少指定的天数
     * @param currentDate 当前时间
     * @param amount 天数，可以为负数
     * @return
     * @author yjx
     */
    public static Date plus(Date currentDate, int amount) {
        return new DateTime(currentDate).plusDays(amount).toDate();
//		Calendar instance = Calendar.getInstance();
//		// 设置当前时间
//		instance.setTime(currentDate);
//		// 增加天数
//		instance.add(Calendar.DATE, amount);
        // 返回增加后的天数
//		return instance.getTime();
    }

    /**
     * 获取下一天
     * @param currentDate
     * @return
     * @author yjx
     */
    public static Date getNextDay(Date currentDate) {
        return plus(currentDate, 1);
    }

    public static Date getNextDay(String dateStr) {
        DateTime dateTime = DateTime.parse(dateStr, DATE_PATTERN);
        return plus(dateTime.toDate(), 1);
    }

    /**
     * 获取上一天
     * @param currentDate
     * @return
     * @author yjx
     */
    public static Date getPrevDay(Date currentDate) {
        return plus(currentDate, -1);
    }

    public static Date getDay(String dateStr) {
        return DateTime.parse(dateStr, DATE_PATTERN).toDate();
    }

    public static Date getCurrentDay() {
        return LocalDate.fromDateFields(new Date()).toDate();
    }

    public static Date plus(Date date, String timeStr) {
        DateTime _date = new DateTime(date);
        DateTime _time = DateTime.parse(timeStr, TIME_PATTERN);
        return _date.plus(_time.getMillisOfDay()).toDate();
    }

    public static Date plus(String dateStr, String timeStr) {
        DateTime date = DateTime.parse(dateStr, DATE_PATTERN);
        DateTime time = DateTime.parse(timeStr, TIME_PATTERN);
        return date.plus(time.getMillisOfDay()).toDate();
    }




    public static void main(String[] args) {
        Date date = getCurrentDay();
        System.out.println(date);
    }
}
