package com.back.base.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	
	public static String Date2String(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(null != date){
			return sdf.format(date);
		}
		return "";
	}
	
	public static String Time2String(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(null != date){
			return sdf.format(date);
		}
		return "";
	}
	
	public static String Date2String(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if(null != date){
			return sdf.format(date);
		}
		return "";
	}
	
	public static String formatDate(Date d, String pattern) {
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		return sf.format(d);
	}
	
	public static Date getDateByHour(Date date, int hour) {
		GregorianCalendar calendar = new GregorianCalendar();
	    calendar.add(GregorianCalendar.HOUR_OF_DAY, hour);
		return calendar.getTime();
	}

}
