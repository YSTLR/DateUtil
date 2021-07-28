/*
 * Copyright (c) 2009 AsiaInfo Software Foundation. All Rights Reserved.
 * This software is published under the terms of the AsiaInfo Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 */

package com.yst;


import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间工具类
 */
public class DateUtil {

	private static final String defaultFormat = "yyyy-MM-dd";

	/**
	 * 查询系统当前时间，时区为中国标准时间,返回类型为String
	 * 
	 * @param format
	 *            格式如：yyyy-MM-dd HH:mm:ss
	 * @return 返回类型为String
	 */
	public static String getCurrentDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE);
		Date date = new Date();
		return sdf.format(date);
	}

	/**
	 * 返回前一天日期
	 * 
	 * @param format
	 * @return
	 */
	public static String getPreDate(String format) {
		if (format == null) {
			format = "yyyy-MM-dd";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE);
		return sdf.format(calendar.getTime());
	}
	
	public static String getPreMonth(String format){
		if(format == null){
			format = "yyyy-MM";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE);
		return sdf.format(calendar.getTime());
	}

	/**
	 * 将String类型的日期转换成指定格式的Date类型
	 * 
	 * @param dateOfString
	 *            传入的时间字符串，例如2009-10-22 09:43:36
	 * @param targetFormat
	 *            目标日期格式，例如yyyy-MM-dd HH:mm:ss
	 * @return 以传入的格式返回日期类型(如yyyy-MM-dd HH:mm:ss格式)
	 */
	public static Date parseDate(String dateOfString, String targetFormat) {
		if (dateOfString == null || dateOfString.trim().equals("")) {
			return null;
		}
		if (dateOfString != null && dateOfString.length() > 19) {
			dateOfString = dateOfString.substring(0, 19);
		}
		String format = DateUtil.getPattern(targetFormat);
		if (format == null) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = simpleDateFormat.parse(dateOfString);
			simpleDateFormat = new SimpleDateFormat(targetFormat);
			date = simpleDateFormat.parse(simpleDateFormat.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 将一个日期字符串转化成另一种日期格式的字符串
	 * 
	 * @param dateOfString
	 *            传入的日期字符串，例如2009-10-22 09:43:36
	 * @param targetFormat
	 *            目标日期格式，例如yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String parseDateToString(String dateOfString, String targetFormat) {
		if (dateOfString == null || dateOfString.trim().equals("")) {
			return null;
		}
		if (dateOfString != null && dateOfString.length() > 19) {
			dateOfString = dateOfString.substring(0, 19);
		}
		String format = DateUtil.getPattern(dateOfString);
		if (format == null) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = simpleDateFormat.parse(dateOfString);
			simpleDateFormat = new SimpleDateFormat(targetFormat);
			return simpleDateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将Date类型的日期转换成String类型
	 * 
	 * @param date
	 *            传入的时间
	 * @param format
	 *            格式，例如yyyy-MM-dd
	 * @return 以"yyyy-MM-dd"格式返回日期类型
	 */
	public static String date2String(Date date, String format) {
		if (date == null) {
			return null;
		}
		if (format == null || "".equals(format)) {
			format = defaultFormat;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		String dateOfStr = "";
		try {
			dateOfStr = simpleDateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateOfStr;
	}

	/**
	 * 传入两个日期，计算这两个日期的差值天数(日期值小的参数在前)
	 * 
	 * @param startDate
	 *            前一个日期值
	 * @param endDate
	 *            后一个日期值
	 * @return 传入两个日期，计算这两个日期的差值天数(日期值小的参数在前)
	 */
	public static int getIntervalDays(Date startDate, Date endDate) {
		long startdate = startDate.getTime();
		long enddate = endDate.getTime();
		long interval = enddate - startdate;
		int intervaldays = (int) (interval / (1000 * 60 * 60 * 24));
		return intervaldays;
	}

	/**
	 * 传入两个日期，计算这两个日期的差值分钟数(日期值小的参数在前)
	 * 
	 * @param startDate
	 *            前一个日期值
	 * @param endDate
	 *            后一个日期值
	 * @return 传入两个日期，计算这两个日期的差值分钟数(日期值小的参数在前)
	 */
	public static int getIntervalMins(Date startDate, Date endDate) {
		long startdate = startDate.getTime();
		long enddate = endDate.getTime();
		long interval = enddate - startdate;
		int intervalMins = (int) (interval / (1000 * 60));
		return intervalMins;
	}

	/**
	 * 根据与一个日期的相差天数得到一个新的日期
	 * 
	 * @param date
	 * @param intervalDays
	 * @return
	 */
	public static Date getDateByIntervalDays(Date date, int intervalDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, intervalDays);
		return new Date(calendar.getTime().getTime());
	}

	/**
	 * 根据与一个日期的相差月数得到一个新的日期
	 * 
	 * @param date
	 * @param intervalMonths
	 * @return
	 */
	public static Date getDateByIntervalMonths(Date date, int intervalMonths) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, intervalMonths);
		return new Date(calendar.getTime().getTime());
	}

	/**
	 * 根据与一个日期的相差年数得到一个新的日期
	 * 
	 * @param date
	 * @param intervalYears
	 * @return
	 */
	public static Date getDateByIntervalYears(Date date, int intervalYears) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, intervalYears);
		return new Date(calendar.getTime().getTime());
	}

	/**
	 * 获得当前日期的上一个月，返回格式如"200808"
	 * 
	 * @return
	 */
	public static String getLastMonth() {
		Date date = new Date();
		String year = DateUtil.getYear(date);
		String month = DateUtil.getMonth(date);
		String lastYear = String.valueOf(Integer.valueOf(year).intValue() - 1);
		String lastMonth = String.valueOf(Integer.valueOf(month).intValue() - 1);
		if (lastMonth.length() == 1) {
			lastMonth = "0" + lastMonth;
		}
		if ("01".equals(month)) {
			return (lastYear + "12");
		} else {
			return (year + lastMonth);
		}
	}

	/**
	 * 获得当前日期的上一个月，返回格式如"200808"
	 * 
	 * @return
	 */
	public static String getLastDay() {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 获得当前日期的上一个月，返回格式如"2009-07"
	 * 
	 * @return
	 */
	public static String getLastMonth2() {
		Date date = new Date();
		String opTime = "";
		String year = DateUtil.getYear(date);
		String month = DateUtil.getMonth(date);
		String lastYear = String.valueOf(Integer.valueOf(year).intValue() - 1);
		String lastMonth = String.valueOf(Integer.valueOf(month).intValue() - 1);
		if (lastMonth.length() == 1) {
			lastMonth = "0" + lastMonth;
		}
		if ("01".equals(month)) {
			opTime = lastYear + "-12";
		} else {
			opTime = year + "-" + lastMonth;
		}
		return opTime;
	}

	/**
	 * 由年份和月份构造日期对象
	 * 
	 * @return Date
	 */
	public static Date getDate(int year, int month) {
		// 如果年份或月份小于1，那么显然是不可能的，返回 null
		if (year < 1 || month < 1) {
			return null;
		}
		Date date = null;
		SimpleDateFormat yearMonthFormat = new SimpleDateFormat("yyyy-MM");
		// 按照 yearMonthFormat 定义的格式把参数传化成字符串
		String dateStr = new Integer(year).toString() + "-" + new Integer(month).toString();
		try {
			// 把字符串类型的时间转化成日期类型
			date = yearMonthFormat.parse(dateStr);
		} catch (ParseException e) {
			date = null;
		}
		return date;
	}

	/**
	 * 由年份、月份、日期构造日期对象
	 * 
	 * @param year
	 *            the year like 1900.
	 * @param month
	 *            the month between 1-12.
	 * @param day
	 *            the day of the month between 1-31.
	 * @return Date
	 */
	public static Date getDate(int year, int month, int day) {
		// 如果年份、月份或日期小于1，那么显然是不可能的，返回 null
		if (year < 1 || month < 1 || day < 1) {
			return null;
		}
		Date date = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 按照 dateFormat 定义的格式把参数传化成字符串
		String dateStr = new Integer(year).toString() + "-" + new Integer(month).toString() + "-" + new Integer(day).toString();
		try {
			// 把字符串类型的时间转化成日期类型
			date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			date = null;
		}
		return date;
	}

	/**
	 * 获得某月最后一天的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayDateOfTheMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		final int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date lastDate = calendar.getTime();
		lastDate.setTime(lastDay);
		return lastDate;
	}

	/**
	 * 获得某月的天数
	 * 
	 * @param dateOfString
	 * @return
	 */
	public static int getLastDayOfTheMonth(String dateOfString) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat yearMonthFormat = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = yearMonthFormat.parse(dateOfString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setTime(date);
		final int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return lastDay;
	}

	/**
	 * 获得某日期的年份
	 * 
	 * @param date
	 * @return
	 */
	public static String getYear(Date date) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		return yearFormat.format(date);
	}

	/**
	 * 获得某日期的月份
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonth(Date date) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat yearFormat = new SimpleDateFormat("MM");
		return yearFormat.format(date);
	}

	/**
	 * 获得某日期的年份月份
	 * 
	 * @param date
	 * @return
	 */
	public static String getYearMonth(Date date) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyyMM");
		return yearFormat.format(date);
	}

	/**
	 * 获得某日期是几号
	 * 
	 * @param date
	 * @return
	 */
	public static String getDay(Date date) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat yearFormat = new SimpleDateFormat("dd");
		return yearFormat.format(date);
	}

	/**
	 * 取得传入的日期字符串的日期格式
	 * 
	 * @param dateOfString
	 *            传入的日期字符串
	 * @return
	 */
	public static String getPattern(String dateOfString) {
		if (dateOfString == null) {
			return null;
		}
		if (dateOfString.matches("[1-9][0-9][0-9][0-9][-][0-9][0-9][-][0-9][0-9]")) {
			return "yyyy-MM-dd";
		}
		if (dateOfString.matches("[1-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
			return "yyyyMMdd";
		}
		if (dateOfString.matches("[1-9][0-9][0-9][0-9][-][0-9][0-9]")) {
			return "yyyy-MM";
		}
		if (dateOfString.matches("[1-9][0-9][0-9][0-9][0-9][0-9]")) {
			return "yyyyMM";
		}
		if (dateOfString.matches("[1-9][0-9][0-9][0-9]")) {
			return "yyyy";
		}
		if (dateOfString.matches("[1-9][0-9][0-9][0-9][-][0-9][0-9][-][0-9][0-9][\\s][0-9][0-9][:][0-9][0-9][:][0-9][0-9]")) {
			return "yyyy-MM-dd HH:mm:ss";
		}
		if (dateOfString.matches("[1-9][0-9][0-9][0-9][-][0-9][0-9][-][0-9][0-9][\\s][0-9][0-9][-][0-9][0-9][-][0-9][0-9]")) {
			return "yyyy-MM-dd HH-mm-ss";
		}
		return null;
	}

	// 取得某个时间前n天,格式为yyyy-mm-dd
	public static String getStringBeforeNDay(String str, int n) throws Exception {
		Calendar ca = parseStringToCalendar(str);
		if (ca == null) {
			return null;
		}
		ca.add(Calendar.DATE, -n);
		String strDate = ca.get(Calendar.YEAR) + "-";
		int intMonth = ca.get(Calendar.MONTH) + 1;
		if (intMonth < 10) {
			strDate += "0" + intMonth + "-";
		} else {
			strDate += intMonth + "-";
		}
		int intDay = ca.get(Calendar.DATE);
		if (intDay < 10) {
			strDate += "0" + intDay;
		} else {
			strDate += intDay;
		}
		return strDate;
	}

	// 取得某个时间后n天,格式为yyyy-mm-dd
	public static String getStringAfterNDay(String str, int n) throws Exception {
		Calendar ca = parseStringToCalendar(str);
		if (ca == null) {
			return null;
		}
		ca.add(Calendar.DATE, n);
		String strDate = ca.get(Calendar.YEAR) + "-";
		int intMonth = ca.get(Calendar.MONTH) + 1;
		if (intMonth < 10) {
			strDate += "0" + intMonth + "-";
		} else {
			strDate += intMonth + "-";
		}
		int intDay = ca.get(Calendar.DATE);
		if (intDay < 10) {
			strDate += "0" + intDay;
		} else {
			strDate += intDay;
		}
		return strDate;
	}

	// 将一个日期字符串转化成Calendar
	// 字符串格式为yyyy-MM-dd
	public static Calendar parseStringToCalendar(String strDate) throws Exception {
		Date date = parseStringToUtilDate(strDate);
		if (date == null) {
			return null;
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca;
	}

	// 将一个日期字符串转化成java.util.Date日期
	// 字符串格式如yyyy-MM-dd
	public static Date parseStringToUtilDate(String strDate) throws Exception {
		Date date = null;
		SimpleDateFormat df = null;
		if (strDate == null || strDate.length() < 6) {
			throw new Exception("日期格式不正确");
		}
		try {
			strDate = strDate.replaceAll("-", "");
			strDate = strDate.replaceAll("_", "");
			strDate = strDate.replaceAll("/", "");
			if (strDate.length() == 8) {
				df = new SimpleDateFormat("yyyyMMdd");
			} else if (strDate.length() == 6) {
				df = new SimpleDateFormat("yyyyMM");
			}
			if (df != null) {
				date = df.parse(strDate);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return date;
	}

	/**
	 * @param date
	 *            时间字符串
	 * @param n
	 *            对date 月份的操作月数 为正数 就是向后加N个月 为负数就是向前+n个月
	 * @return 返回月份格式为yyyyMM
	 */
	public static String getStringOperMonth(String date, int n) throws Exception {
		if (date == null && date.equals("")) {
			return null;
		}
		date = date.replaceAll("-", "");
		StringBuffer sb = new StringBuffer(date);
		sb.insert(4, "-");
		date = date.length() == 6 ? sb.append("-01").toString() : sb.insert(7, "-").toString();
		Calendar calendar = parseStringToCalendar(date);
		calendar.add(Calendar.MONTH, n);
		Date theDate = calendar.getTime();
		String time = new SimpleDateFormat("yyyyMM").format(theDate);
		return time;
	}

	/**
	 * 日期值小的参数在前
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getIntervalMonths(String startDate, String endDate) throws Exception {
		startDate = startDate.replaceAll("-", "");
		StringBuffer sb = new StringBuffer(startDate);
		sb.insert(4, "-");
		startDate = startDate.length() == 6 ? sb.append("-01").toString() : sb.insert(7, "-").toString();
		endDate = endDate.replaceAll("-", "");
		StringBuffer sb1 = new StringBuffer(endDate);
		sb1.insert(4, "-");
		endDate = endDate.length() == 6 ? sb1.append("-01").toString() : sb1.insert(7, "-").toString();

		Date st = parseStringToUtilDate(startDate);
		Date et = parseStringToUtilDate(endDate);

		String sy = getYear(st);
		String ey = getYear(et);

		int MonthByYear = 0;
		int reVal = Integer.valueOf(getMonth(et)).intValue() - Integer.valueOf(getMonth(st)).intValue();

		if (!sy.equals("ey")) {
			MonthByYear = (Integer.valueOf(ey).intValue() - Integer.valueOf(sy).intValue()) * 12;
		}
		return MonthByYear + reVal;
	}

	/**
	 * 根据开始时间和结束时间返回时间段内的时间集合
	 * @param beginDate
	 * @param endDate
	 * @return List
	 */
	public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) throws Exception {
		List<Date> lDate = new ArrayList<Date>();
		if(beginDate.equals(endDate)) {
			lDate.add(beginDate);
			return lDate;
		}
		SimpleDateFormat sdfd = new SimpleDateFormat("yyyyMMdd");
		lDate.add(beginDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginDate);
		boolean bContinue = true;
		while (bContinue) {
			if ((sdfd.format(beginDate)).length() == 8) {
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
			if (endDate.after(cal.getTime())) {
				lDate.add(cal.getTime());
			} else {
				break;
			}
		}
		lDate.add(endDate);
		return lDate;
	}

	public static List<Date> getDatesBetweenTwoMon(Date beginDate, Date endDate) throws Exception {
		List<Date> lDate = new ArrayList<Date>();
		if(beginDate.equals(endDate)) {
			lDate.add(beginDate);
			return lDate;
		}
		SimpleDateFormat sdfm = new SimpleDateFormat("yyyyMM");
		lDate.add(beginDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginDate);
		boolean bContinue = true;
		while (bContinue) {

			if ((sdfm.format(beginDate)).length() == 6) {
				cal.add(Calendar.MONTH, 1);
			}
			if (endDate.after(cal.getTime())) {
				lDate.add(cal.getTime());
			} else {
				break;
			}
		}
		lDate.add(endDate);
		return lDate;
	}
	
	/**
	 * 将一种格式的日期转换为另一种格式
	 * @param dataStr
	 * @param oldFormat
	 * @param newFormat
	 * @return String
	 * @throws ParseException
	 */
	public static String changeDateStrFormat(String dataStr, String oldFormat, String newFormat) throws ParseException{
		if(StringUtils.isBlank(dataStr)){
			return "";
		}
		Date date = new SimpleDateFormat(oldFormat).parse(dataStr);
		return new SimpleDateFormat(newFormat).format(date);
	}


/*	public static void main(String[] args) throws ParseException {
		// //System.out.println("1:" + DateUtil.getCurrentDate("yyyy-MM-dd
		// HH:mm:ss"));
		// //System.out.println("2:" + DateUtil.date2String(new Date(),
		// "yyyy-MM-dd HH:mm:ss"));
		// //System.out.println("3:" + DateUtil.parseDate("2009-02-18 15:10:09",
		// "yyyyMM"));
		// //System.out.println("4:" + DateUtil.parseDateToString("2009-12-03",
		// "yyyyMM"));
		// //System.out.println("----"+getStringOperMonth("20080312",-3));
		// //System.out.println("----"+getIntervalDays(DateUtil.parseStringToUtilDate("2009-08-03"),DateUtil.parseStringToUtilDate("2009-08-07")));

		// long startdate =
		// DateUtil.parseStringToUtilDate("2009-06-01").getTime();
		// long enddate =
		// DateUtil.parseStringToUtilDate("2009-08-01").getTime();
		//
		// Date date =
		// DateUtil.getDateByIntervalDays(DateUtil.parseStringToUtilDate("2017-03-01"),
		// -1);
		// //System.out.println(date);
		//
		// //System.out.println("--------"+getIntervalMonths("2008-09","2009-12"));

		// //System.out.println(DateUtil.getIntervalDays(DateUtil.parseDate("2009-05-30",
		// "yyyy-MM-dd HH:mm:ss"),DateUtil.parseDate("2009-07-30", "yyyy-MM-dd
		// HH:mm:ss")));
		
		System.out.println(DateUtil.changeDateStrFormat("20190319", "yyyyMMdd", "yyyy-MM"));
	}*/

	/**
	 * 根据某年的第n周，查询本周的起止时间
	 * 作者：闫思廷
	 * @param year
	 * @param week
	 * @return
	 */
	public static Map<String,String> weekToDayFormate(int year, int week){
		Calendar calendar = Calendar.getInstance();
		//设置该年份的开始日期：第一个月的第一天
		calendar.set(year,0,1);
		//计算出第一周还剩几天：+1是因为1号是1天
		int dayOfWeek = 7 - calendar.get(Calendar.DAY_OF_WEEK) + 1;
		//周数减去第一周再减去要得到的周
		week = week - 2;
		//计算起止日期
		calendar.add(Calendar.DAY_OF_YEAR,week * 7 + dayOfWeek);

		String startDay = new SimpleDateFormat("yyyyMMdd").format(DateUtil.getDateByIntervalDays(calendar.getTime(),1));
		calendar.add(Calendar.DAY_OF_YEAR, 7);
		String endDay = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());

		HashMap<String, String> map = new HashMap<>();
		map.put("startDay",startDay);
		map.put("endDay",endDay);
		return map;

	}
}