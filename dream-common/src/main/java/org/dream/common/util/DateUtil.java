package org.dream.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {

	/*24 小时制*/
	private static final String DEFAILT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	private static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";

	private static final String DEFAILT_DATE_PATTERN = "yyyy-MM-dd";

	/* 获取默认的 date patten */
	public static String getDefaultPatten() {
		return DEFAILT_DATE_TIME_PATTERN;
	}

	public static Date addMinutes(Date date, int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);

		return calendar.getTime();
	}

	public static Date addSeconds(Date date, int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, seconds);
		return calendar.getTime();
	}

	public static Date addHours(Date date, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hours);
		return calendar.getTime();
	}

	public static String formateDate(Date d) {
		return new SimpleDateFormat(DEFAILT_DATE_PATTERN).format(d);
	}

	public static String formateDateTime(Date d) {
		return new SimpleDateFormat(DEFAILT_DATE_TIME_PATTERN).format(d);
	}

	public static String formateTime(Date d) {
		return new SimpleDateFormat(DEFAULT_TIME_PATTERN).format(d);
	}

	public static Date parse(String strDate, String pattern) throws ParseException {
		return StringUtils.isNotBlank(strDate) ? new SimpleDateFormat(pattern).parse(strDate) : null;
	}

	public static Date parse(String strDate) throws ParseException
	{
		return StringUtils.isNotBlank(strDate)? new SimpleDateFormat(getDefaultPatten()).parse(strDate):null;
	}
}
