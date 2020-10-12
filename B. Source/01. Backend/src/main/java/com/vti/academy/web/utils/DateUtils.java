
package com.vti.academy.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {

	private static final Logger LOG = LoggerFactory.getLogger(DateUtils.class);
	public static final String DDMMYYYY = "dd/MM/yyyy";
	public static final String YYYYMMDD = "yyyy/MM/dd";
	public static final String DDMMYYYYHHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYYMMDD_MINUS = "yyyy-MM-dd";

	private DateUtils() {

	}

	/**
	 * Get Date Now Insert SQL
	 * 
	 * @return
	 */
	public static String getDateTimeNow(String format) {
		final SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(new Date());
	}
	
	
	/**
	 * Convert Date to String
	 * 
	 * @return
	 */
	public static String convertDateToString(Date date, String format) {
		if(null==date) {
			return "";
		}
		final SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * Get Date Now Insert SQL
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String format, String strDate) {
		final SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			LOG.error("Exception! Error parse string Date to Date");
		}
		return null;
	}
	
	/**
	 * Get Date Now Insert SQL
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date convertStringToDateWithTimeZone(String strDate) {
		final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssX");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			LOG.error("Exception! Error parse string Date to Date");
		}
		return null;
	}
}
