package com.yst;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class HandleDate {

    /**
     * 工具方法：获取昨日的日期字符串 yyyyMMdd
     * @param handle 传入当前日期
     * @return
     */
    public static String getYesterday(String handle) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date currentDay = sdf.parse(handle);
        Date yesterday = DateUtil.getDateByIntervalDays(currentDay,-1);
        String yesterdayInString = sdf.format(yesterday);

        return yesterdayInString;
    }

    /**
     * 工具方法：判断日期是否跨周
     * @param startTime,endTime 传入当前日期
     * @return
     */
    public static boolean acrossWeek(String startTime,String endTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date startDate = sdf.parse(startTime);
        Date endDate = sdf.parse(endTime);
        if(getThisWeekMonday(startDate).equals(getThisWeekMonday(endDate))){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 工具方法：判断日期是否跨月
     * @param startTime,endTime 传入当前日期
     * @return
     */
    public static boolean acrossMonth(String startTime,String endTime) throws ParseException {
        String startMonth = startTime.substring(startTime.length()-4,startTime.length()-2);
        String endMonth = endTime.substring(endTime.length()-4,endTime.length()-2);

        return !startMonth.equals(endMonth);
    }

    /**
     * 工具方法：获取本周的周一的日期
     * @param date 传入当前日期
     * @return
     */
    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    /**
     * 工具方法：获取本周的周日的日期
     * @param date 传入当前日期
     * @return
     */
    public static Date getThisWeekSunday(Date date) {

        //获取周一时间
        Date monday = getThisWeekMonday(date);
        Date sunday = DateUtil.getDateByIntervalDays(monday, 6);

        return sunday;
    }

    /**
     * 工具方法：获取当前日期上周日的日期，返回yyyyMMdd
     * @param handle 传入当前日期
     * @return
     */
    public static String getSundayBeforeThisWeek(String handle) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(handle);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day-1);
        String str = DateUtil.date2String(cal.getTime(),"yyyyMMdd");
        return str;
    }

    /**
     * 工具方法：获取当前月的上一个月，返回yyyyMM
     * @param handle 传入当前日期
     * @return
     */
    public static String getMonthBeforeThisMonth(String handle) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(handle);
        Date dateByIntervalMonths = DateUtil.getDateByIntervalMonths(date, -1);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMM");
        String lastMonth = sdf2.format(dateByIntervalMonths);
        return lastMonth;
    }

    /**
     * 判断入参代表的日期是否是当前周
     * @param handle
     * @return
     * @throws ParseException
     */
    public static boolean isThisWeek(String handle)throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //获取本周一
        Date d = new Date();
        String format = sdf.format(d);
        Date date1 = sdf.parse(format);
        Date monday = getThisWeekMonday(date1);
        Date sunday = DateUtil.getDateByIntervalDays(monday,6);

        Date date = sdf.parse(handle);
        if(monday.equals(date)||sunday.equals(date)){
            return true;
        }else{
            if(monday.before(date)&&sunday.after(date)){
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * 判断入参代表的日期是否是当前月
     * @param handle
     * @return
     * @throws ParseException
     */
    public static boolean isThisMonth(String handle)throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

        String handleFormat = DateUtil.changeDateStrFormat(handle,"yyyyMMdd","yyyyMM");
        Date date = new Date();
        String thisMonth = sdf.format(date);

        return handleFormat.equals(thisMonth);
    }

    /**
     * 工具方法：判断某日期是否为周日
     * @param handle 传入当前日期
     * @return
     */
    public static boolean isSunday(String handle) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(handle);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 工具方法：判断某日期是否为周一
     * @param handle 传入当前日期
     * @return
     */
    public static boolean isMonday(String handle) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(handle);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 工具方法：判断某日期是否为每月最后一天
     * @param handle 传入当前日期
     * @return
     */
    public static boolean isLastdayOfMonth(String handle) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,Integer.parseInt(handle.substring(0,4)));

        cal.set(Calendar.MONTH,Integer.parseInt(handle.substring(4,6))-1);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        String str1 = DateUtil.date2String(cal.getTime(),"yyyyMMdd");
        return str1.equals(handle);
    }

    /**
     * 滑动时间窗口的工具方法，即根据起止日期，得到上一相同时间周期的起止时间
     * 例如，输入20210514-20210525，返回20210503-20210514
     * @param param
     * @return
     */
    public static void slideTimeWindow(Map<String,String> param) throws ParseException {
        String startTime = param.get("startTime");
        String endTime = param.get("endTime");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date startTimeInDate = sdf.parse(startTime);
        Date endTimeInDate = sdf.parse(endTime);

        //得到日期差值
        int intervalDays = DateUtil.getIntervalDays(startTimeInDate, endTimeInDate);

        Date newStartTimeInDate = DateUtil.getDateByIntervalDays(startTimeInDate, -intervalDays);
        String newStartTime = sdf.format(newStartTimeInDate);
        param.put("startTime",newStartTime);
        param.put("endTime",startTime);

    }

}
