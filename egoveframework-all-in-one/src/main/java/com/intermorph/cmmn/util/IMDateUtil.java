/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.util;

import com.intermorph.cmmn.IMGlobals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;



import egovframework.com.utl.fcc.service.EgovDateUtil;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.util
 * @File    : IMDateUtil.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 16
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class IMDateUtil extends EgovDateUtil {
	
	/**
	 * 시스템의 오늘
	 * 
	 * @return Date
	 */
	public static Date getImToday() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * 시스템의 오늘
	 * 
	 * @return String
	 */
	public static String getImToday(String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(getImToday());
	}

	/**
	 * 시스템의 오늘(년도)
	 * 
	 * @return int
	 */
	public static int getTodayYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 시스템의 오늘(월)
	 * 
	 * @return int
	 */
	public static int getTodayMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH);
	}

	/**
	 * 시스템의 오늘(일)
	 * 
	 * @return int
	 */
	public static int getTodayDate() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DATE);
	}

	/**
	 * 시스템의 오늘(요일)
	 * 
	 * @return int
	 */
	public static int getTodayDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 날짜를 format String 으로 반환
	 * 
	 * @return String
	 */
	public static String getFormatString(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	/**
	 * 날짜를 format String 으로 반환
	 * 
	 * @return String
	 */
	public static String getFormatString(long time, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(time);
	}

	/**
	 * 날짜를 format String 으로 반환
	 * 
	 * @return String
	 * @throws ParseException
	 */
	public static String getFormatString(String date, String pattern) throws ParseException {
		if (date == null || "".equals(date)) {
			return null;
		}

		SimpleDateFormat formatter = new SimpleDateFormat(IMGlobals.IM_FORMAT_DBDATETIME);
		Date d = formatter.parse(date);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);

		SimpleDateFormat formatter1 = new SimpleDateFormat(pattern);

		return formatter1.format(calendar.getTime());
	}

	/**
	 * format String을 날짜로 반환
	 * 
	 * @return Date
	 * @throws ParseException
	 */
	public static Date getFormatDate(String date, String pattern) throws Exception {
		if (date == null || date.length() == 0) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.parse(date);
	}

	/**
	 * 날짜 더하기
	 * 
	 * @param date
	 * @param add
	 * @return
	 */
	public static Date addDate(Date date, int add) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, add);
		return calendar.getTime();
	}

	/**
	 * timezone 이 적용된 날짜만들기
	 * 
	 * @return Date
	 * @throws ParseException
	 */
	public static Date getTimezoneDate(Date date, String format, String timezone) throws Exception {
		TimeZone tz = TimeZone.getTimeZone(timezone);
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.setTimeZone(tz);
		SimpleDateFormat formatter2 = new SimpleDateFormat(format);
		return formatter2.parse(formatter.format(date));
	}

	/**
	 * timezone 이 적용된 오늘(0시 0분 0초 기준)
	 * 
	 * @return Date
	 * @throws ParseException
	 */
	public static Date getTimezoneToday(String format, String timezone) throws Exception {
		Calendar calendar = Calendar.getInstance();
		return getTimezoneDate(calendar.getTime(), format, timezone);
	}

	/**
	 * 2011.09.28 -> 20110927150000 (시스템이 GMT+00:00 이고 적용 timezone이 GMT+09:00 일 경우)
	 * 
	 * @param date
	 * @return
	 */
	public static String getTimezoneStartDate(Date date, String format, String timezone) {
		Calendar calendar = Calendar.getInstance();
		TimeZone tz = TimeZone.getTimeZone(timezone);
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		calendar.setTime(date);
		long offset = tz.getOffset(Calendar.ZONE_OFFSET) - calendar.getTimeZone().getOffset(Calendar.ZONE_OFFSET);
		return formatter.format(calendar.getTimeInMillis() - offset);
	}

	/**
	 * 
	 * 2011.09.28 -> 20110928145959 (시스템이 GMT+00:00 이고 적용 timezone이 GMT+09:00 일 경우)
	 * 
	 * @param date
	 * @return
	 */
	public static String getTimezoneEndDate(Date date, String format, String timezone) {
		Calendar calendar = Calendar.getInstance();
		TimeZone tz = TimeZone.getTimeZone(timezone);
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		calendar.add(Calendar.SECOND, -1);
		long offset = tz.getOffset(Calendar.ZONE_OFFSET) - calendar.getTimeZone().getOffset(Calendar.ZONE_OFFSET);
		return formatter.format(calendar.getTimeInMillis() - offset);
	}

	/**
	 * 달력으로 선택한 시작일 타임존 적용
	 * 
	 * @param date
	 * @param calenderFormat
	 * @param toFormat
	 * @param timezone
	 * @return
	 * @throws Exception
	 */
	public static String convertStartDate(String date, String calenderFormat, String toFormat, String timezone) throws Exception {
		return getTimezoneStartDate(getFormatDate(date, calenderFormat), toFormat, timezone);
	}

	/**
	 * 달력으로 선택한 시작일 타임존(GMT+09:00) 적용
	 * 
	 * @param date
	 * @param calenderFormat
	 * @param toFormat
	 * @return
	 * @throws Exception
	 */
	public static String convertStartDate(String date, String calenderFormat, String toFormat) throws Exception {
		return getTimezoneStartDate(getFormatDate(date, calenderFormat), toFormat, "GMT+09:00");
	}

	/**
	 * 달력으로 선택한 시작일 타임존 적용
	 * 
	 * @param date
	 * @param calenderFormat
	 * @param toFormat
	 * @param timezone
	 * @return
	 * @throws Exception
	 */
	public static String convertEndDate(String date, String calenderFormat, String toFormat, String timezone) throws Exception {
		return getTimezoneEndDate(getFormatDate(date, calenderFormat), toFormat, timezone);
	}

	/**
	 * 달력으로 선택한 시작일 타임존(GMT+09:00) 적용
	 * 
	 * @param date
	 * @param calenderFormat
	 * @param toFormat
	 * @return
	 * @throws Exception
	 */
	public static String convertEndDate(String date, String calenderFormat, String toFormat) throws Exception {
		return getTimezoneEndDate(getFormatDate(date, calenderFormat), toFormat, "GMT+09:00");
	}

	/**
	 * 두 날짜 차이 일 수
	 * 
	 * @param date1
	 * @param date2
	 * @param pattern
	 * @return
	 */
	public static long diffDate(String date1, String date2, String pattern) {
		try {
			Date d1 = IMDateUtil.getFormatDate(date1, pattern);
			Date d2 = IMDateUtil.getFormatDate(date2, pattern);
			Calendar calendar1 = Calendar.getInstance();
			Calendar calendar2 = Calendar.getInstance();
			calendar1.setTime(d1);
			calendar2.setTime(d2);
			long milliseconds1 = calendar1.getTimeInMillis();
			long milliseconds2 = calendar2.getTimeInMillis();
			return (milliseconds2 - milliseconds1) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 주어진 날짜의 달에서 시작 날짜를 구한다.
	 * 
	 * @param date
	 * @return date
	 */
	public static Date firstDateOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 주어진 날짜의 달에서 마지막 날짜를 구한다.
	 * 
	 * @param date
	 * @return date
	 */
	public static Date lastDateOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * 날짜를 오늘날짜와 비교
	 * 
	 * @return Boolean
	 * @throws ParseException
	 */
	public static Boolean getTodayCompareDate(String date) throws ParseException {
		boolean falg = false;

		if (date == null || "".equals(date)) {
			return false;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date d = formatter.parse(date);

		Calendar calendar = Calendar.getInstance();
		if (d.after(calendar.getTime())) {
			falg = true;
		}

		return falg;
	}


}
