package com.yjx.individual.common.utils;




import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * 袁君选
 * 处理LocalDate工具类
 */
public class LocalDateUtil {

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmmss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter DATETIME_FORMATTER =  DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /**
     * 获取当前系统时间
     * @return
     */
    public static LocalTime getLocalTime() {
        return LocalTime.now();
    }



    /**
     * 获取当前系统日期
     * @return
     */
    public static LocalDate getLocalDate() {
        return LocalDate.now();
    }

    /**
     * 获取当前系统日期时间
     * @return
     */
    public static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 获取当前系统时间  几个小时  几分钟之前
     */
    public static LocalDateTime getLocalDateTime(Integer hour,Integer Minute) {
        return LocalDateTime.now().minusHours(hour).minusMinutes(Minute);
    }

    /**
     * 获取当前系统时间字符串
     * @return
     */
    public static String getLocalTimeString() {
        return LocalTime.now().format(TIME_FORMATTER);
    }

    /**
     * 获取当前系统日期字符串
     * @return
     */
    public static String getLocalDateString() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    /**
     * 获取当前系统日期时间字符串
     * @return
     */
    public static String getLocalDateTimeString() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }

    /**
     * 字符串转LocalTime
     * @param time
     * @return
     */
    public static LocalTime  string2LocalTime(String time) {
        return LocalTime.parse(time, TIME_FORMATTER);
    }

    /**
     * 字符串转LocalDate
     * @param date
     * @return
     */
    public static LocalDate  string2LocalDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    /**
     * 字符串转LocalDateTime
     * @param dateTime
     * @return
     */
    public static LocalDateTime string2LocalDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DATETIME_FORMATTER);
    }

    /**
     * Date转LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return localDateTime;
    }

    /**
     * Date转LocalDate
     * @param date
     * @return
     */
    public static LocalDate date2LocalDate(Date date) {
        Instant instant = date.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return localDate;
    }

    /**
     * Date转LocalDate
     * @param date
     * @return
     */
    public static LocalTime date2LocalTime(Date date) {
        Instant instant = date.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
        LocalTime localTime = instant.atZone(zoneId).toLocalTime();
        return localTime;
    }

    /**
     * LocalDateTime转换为Date
     * @param localDateTime
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    /**
     * LocalDate转Date
     * @param localDate
     * @return
     */
    public static Date localDate2Date(LocalDate localDate) {
        if (null == localDate) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());

    }

    /**
     * localDateTime比较大小
     * @param s
     * @param e
     * @return
     */
    public static boolean localDateTimeCompare(LocalDateTime s ,LocalDateTime e){
        long l = Instant.from(s.atZone(ZoneId.systemDefault())).toEpochMilli();
        long ll = Instant.from(e.atZone(ZoneId.systemDefault())).toEpochMilli();
        System.out.println("判断结果"+(l>ll)  +"  进入时间s   "+s +"   输出时间e"+e);
        return  l>ll;
    }






    public static void main(String[] args) {

        getLocalDateTime();
        getLocalDateTime(0,5);
        boolean b = localDateTimeCompare(getLocalDateTime(), getLocalDateTime(0, 5));
        System.out.println("判断"+b);

        /** 开始时间 */
         String START_TIME = "2017-06-22 00:00:00";
        /** 结束时间 */
        String END_TIME = "2017-06-29 23:59:59";

        /**格式化时间*/
        DateTimeFormatter DATE_FOMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

        // before 5 hours and 30 minutes
        LocalDateTime startDate = LocalDateTime.parse(START_TIME, DATE_FOMAT);

        LocalDateTime endDate = LocalDateTime.parse(END_TIME, DATE_FOMAT);
        if(System.currentTimeMillis() < Instant.from(startDate.atZone(ZoneId.systemDefault())).toEpochMilli() || System.currentTimeMillis() >= Instant.from(endDate.atZone(ZoneId.systemDefault())).toEpochMilli()){
            System.out.println("当前时间小于开始时间 或者 当前时间大于结束时间");
        }
        System.out.println("startDate=="+startDate);
        System.out.println("endDate=="+endDate);
        System.out.println("Instant.from(startDate.atZone(ZoneId.systemDefault())).toEpochMilli()"+Instant.from(startDate.atZone(ZoneId.systemDefault())).toEpochMilli());
        System.out.println("Instant.from(endDate.atZone(ZoneId.systemDefault())).toEpochMilli()"+Instant.from(endDate.atZone(ZoneId.systemDefault())).toEpochMilli());
        LocalDateTime dateTime = LocalDateTime.now().minusHours(5).minusMinutes(30);
//        System.out.println(dateTime);
//        System.out.println(getLocalDateTime(0,5));
        long l = System.currentTimeMillis(); //系统当前时间

        System.out.println(l);


    }

}
