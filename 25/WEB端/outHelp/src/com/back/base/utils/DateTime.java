/**
 */
package com.back.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTime {
	 private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	//当前时间增加天，月，年
	 public static String dateAdd(String flag,String dateStr,int amount) throws ParseException
	 {
		if(dateStr!=null && dateStr.length()>=7)
		{
			GregorianCalendar calendar = new GregorianCalendar();
			if(dateStr.length()==7){dateStr = dateStr+"-01";}
			calendar.set(Integer.parseInt(dateStr.substring(0,4)),Integer.parseInt(dateStr.substring(5,7))-1, Integer.parseInt(dateStr.substring(8,10)), 0, 0, 0);
			if(flag.equals("Y"))calendar.add(calendar.YEAR, amount);
			else if(flag.equals("M"))calendar.add(calendar.MONTH, amount);
			else if(flag.equals("D"))calendar.add(calendar.DATE, amount);
			String curDateTime = dateFormat.format(calendar.getTime());
			return curDateTime;
		}
		return "";
	}
	//将时间字符串转换成需要的格式
	 public static String getFormatDate(String dateStr,String format) throws ParseException
	 {
		if(dateStr!=null && dateStr.length()>=10)
		{
			GregorianCalendar calendar = new GregorianCalendar();  
			calendar.set(Integer.parseInt(dateStr.substring(0,4)),Integer.parseInt(dateStr.substring(5,7))-1, Integer.parseInt(dateStr.substring(8,10)), 0, 0, 0);
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			String curDateTime = formatter.format(calendar.getTime());
			return curDateTime;
		}
		return "";
	}
	 /** 
     * 判断当前日期是星期几
     * @param pTime 修要判断的时间
     * @return dayForWeek 判断结果
     * @Exception 发生异常
     * @author DZZ，2011-07-07
     */ 
	public static String dayNameForWeek(String pTime) throws Exception { 
	  
	  int dayForWeek = dayForWeek(pTime);
	  String dayName="";
	  switch(dayForWeek){
	   case 1:
		   dayName="星期一";
		   break;
	   case 2:
		   dayName="星期二";
		   break;
	   case 3:
		   dayName="星期三";
		   break;
	   case 4:
		   dayName="星期四";
		   break;
	   case 5:
		   dayName="星期五";
		   break;
	   case 6:
		   dayName="星期六";
		   break;
	   case 7:
		   dayName="星期天";
		   break;
	   default:
		   dayName="";
		   break;
	  }
	  return dayName; 
	}
	//得到当前月之后的所有月份
	 public static String[] getMonthByDate(String startDay,int mcount) throws Exception{
		 String[] months = new String[mcount];
		
		 months[0] = startDay;
		 startDay +="-01";
		 
		 for(int i=0;i<mcount-1;i++){
			 months[i+1] = formatStrDateTime(dateAdd("M",startDay,i+1),"yyyy-MM-dd","yyyy-MM");
		 }
		 return months;
	 }
	
	
	
	
	//取得当前日期所在周的每一天
	 public static String[] getWeekDaysByDate(String dateStr) throws Exception
	 {
		 String[] weekDays = new String[7];
		 if(dateStr!=null && dateStr.length()>0)
		 {
			 if(dayForWeek(dateStr)==7){dateStr=dateAdd("D",dateStr,-1);}
			 /* set the date */
			 GregorianCalendar calendar = new GregorianCalendar();  
			 calendar.set(Integer.parseInt(dateStr.substring(0,4)),Integer.parseInt(dateStr.substring(5,7))-1, Integer.parseInt(dateStr.substring(8,10)), 0, 0, 0);
			 //calendar.setGregorianChange(date);  
			 int diff = GregorianCalendar.SUNDAY - calendar.get(GregorianCalendar.DAY_OF_WEEK);
			  /* start from sunday */  
		    calendar.add(GregorianCalendar.DAY_OF_MONTH, diff);  
		    for (int i = GregorianCalendar.SUNDAY; i <= GregorianCalendar.SATURDAY; i++)  
		    {  
		        //System.out.println(calendar.getTime());  
		       calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
		       weekDays[i-1] = dateFormat.format(calendar.getTime());
		    }
		 }
		return weekDays;
	}
	 public static String[] getWeekBetweenDaysByDate(String dateStr) throws Exception
	 {
		 String[] weekDays = new String[2];
		 if(dateStr!=null && dateStr.length()>0)
		 {
			 if(dayForWeek(dateStr)==7){dateStr=dateAdd("D",dateStr,-1);}
			 /* set the date */
			 GregorianCalendar calendar = new GregorianCalendar();  
			 calendar.set(Integer.parseInt(dateStr.substring(0,4)),Integer.parseInt(dateStr.substring(5,7))-1, Integer.parseInt(dateStr.substring(8,10)), 0, 0, 0);
			 //calendar.setGregorianChange(date);  
			 int diff = GregorianCalendar.SUNDAY - calendar.get(GregorianCalendar.DAY_OF_WEEK);
			  /* start from sunday */  
		    calendar.add(GregorianCalendar.DAY_OF_MONTH, diff);  
		    for (int i = GregorianCalendar.SUNDAY; i <= GregorianCalendar.SATURDAY; i++)  
		    {  
		        //System.out.println(calendar.getTime());  
		       calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
		       if(i==GregorianCalendar.SATURDAY){
		    	   weekDays[1] = dateFormat.format(calendar.getTime());
		    	   
		       }
		       if(i==GregorianCalendar.SUNDAY){
		    	   weekDays[0] = dateFormat.format(calendar.getTime());
		       }
		      
		    }
		 }
		return weekDays;
	}
	public static String addDate(long day){
		 Date d = new Date(); 
		 long time = d.getTime(); 
		 day = day*24*60*60*1000; 
		 time+=day; 
		 String addDate =  dateFormat.format(new Date(time));
		 return addDate;
	}
//求两个时间的相隔时间（不含周末）
	public   static   int   getDiffDaysNoWeekend(Date   start,   Date   end)   { 
		int   rtn   =   Integer.MIN_VALUE; 

		//   Total   days 
		int   days   =   getDiffDaysWithWeekend(start,   end); 

		//   Monday   1   --   Sunday   7 
		int   weekStart   =   getWeek(start); 

		//   Week   count 
		int   intg   =   days   /   7; 

		//   left   days 
		int   mod1   =   days   %   7; 

		//   left   days   modified 
		int   mod2   =   (weekStart+mod1)%7; 

		int   factor   =   0; 
		if(mod1!=0   &&   mod2 <weekStart){ 

		//   Start   date   is   from   Monday   1   --   Saturday   6. 
		if(weekStart!=7){ 
		factor   =   mod2+1; 
		//   Max   value   is   2 
		if(factor> 2)   factor   =   2; 
		}else   { 
		factor   =   1; 
		} 
		} 
		rtn   =   days   -   intg*2-factor; 
		return   rtn; 
	}
	//求两个时间的相隔时间
		public   static   int   getDiffDays(Date   start,   Date   end)   { 
		int   rtn   =   Integer.MIN_VALUE; 
		if   (start   !=   null   &&   end   !=   null)   { 
			long   lngMinMilSec   =   start.getTime(); 
			long   lngMaxMilSec   =   end.getTime(); 
			rtn   =   (int)   ((lngMaxMilSec   -   lngMinMilSec)   /   (1000   *   60   *   60   *   24)); 
		} 
		return   rtn; 
	} 
		//求两个时间的相隔时间（含周末）
		public   static   int   getDiffDaysWithWeekend(Date   start,   Date   end)   { 
			int   rtn   =   getDiffDays(start,   end); 
			return   rtn   !=   Integer.MIN_VALUE   ?   rtn   +   1   :   Integer.MIN_VALUE; 
		}
		//取得星期几
		public   static   int   getWeek(Date   dt)   { 
			int   rtn   =   Integer.MIN_VALUE; 
			Calendar   cd   =   Calendar.getInstance(); 
			cd.setTime(dt); 
			int   week   =   cd.get(Calendar.DAY_OF_WEEK); 
			if   (week   ==   1)   { 
				rtn   =   7; 
			}   else   { 
				rtn   =   week   -   1; 
			} 
			return   rtn; 
		} 

	 /** 
     * 比较时间大小
     * @param pTime 修要判断的时间
     * @return date1>=date2返回true;否则返回false
     * @Exception 发生异常
     * @author DZZ，2011-07-07
     */
	public static boolean CompareDate(String time1,String time2) throws Exception     
    {     
	    boolean time=false;
	    if(time1!=null && time1.length()>0 && time2!=null && time2.length()>0)
	    { 
		    if(time1.length()>10){time1=time1.substring(0,10);}
		    if(time1.length()==7){time1=time1+"-01";}
		    if(time2.length()>10){time2=time2.substring(0,10);}
		    if(time2.length()==7){time2=time2+"-01";}
		    Date   d   =   dateFormat.parse(time1);   
		    Date   c  =   dateFormat.parse(time2);   
	
		    if(d.after(c)||d.equals(c))
		    {
		    	time=true;
		    }
	    }  
	    return time; 
    }
	public static boolean CompareDateTime (String nowDate,String nowTime,String firstTime,String endTime) throws Exception     
    {     
		  boolean time=false;
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  Date ndate = sdf.parse(nowDate+" "+nowTime);
		  Date fdate = sdf.parse(nowDate+" "+firstTime);
		  Date edate = sdf.parse(nowDate+" "+endTime);
		  if((fdate.before(ndate) || fdate.equals(ndate)) && (edate.after(ndate) || edate.equals(ndate))){
			  time = true;
		  }
		  return time; 
    }
	public static boolean CompareDateTime (String nowDate,String nowTime,String compareTime) throws Exception     
    {     
		  boolean time=false;
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  Date ndate = sdf.parse(nowDate+" "+nowTime);
		  Date fdate = sdf.parse(nowDate+" "+compareTime);
		  if((fdate.before(ndate) || fdate.equals(ndate))){
			  time = true;
		  }
		  return time; 
    }
	public static boolean CompareMonth(String time1,String time2) throws Exception     
    {     
	    boolean time=false;
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
	    if(time1!=null && time1.length()>0 && time2!=null && time2.length()>0)
	    {
		    if(time1.length()>7){time1=time1.substring(0,7);}
		    if(time2.length()>7){time2=time2.substring(0,7);}
		    Date   d   =   sdf.parse(time1);
		    Date   c  =   sdf.parse(time2);
		    if(d.after(c)||d.equals(c))
		    {
		    	time=true;
		    }
	    }
	   
	    return time; 
    }
	 /** 
     * 判断当前日期是星期几
     * @param pTime 修要判断的时间
     * @return dayForWeek 判断结果
     * @Exception 发生异常
     * @author DZZ，2011-07-07
     */ 
	public static int dayForWeek(String pTime) throws Exception { 
	  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	  Calendar c = Calendar.getInstance(); 
	  c.setTime(df.parse(pTime)); 
	  int dayForWeek = 0; 
	  if(c.get(Calendar.DAY_OF_WEEK) == 1){ 
	   dayForWeek = 7; 
	  }else{ 
	   dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1; 
	  } 
	  return dayForWeek; 
	}
public static String getYearFirstDay(int year)
	{
    	return year+"-01-01";
	}
	public static String getYearLastDay(int year)
	{
    	return year+"-12-31";
	}
	public static String getHalfYearFirstDay(int year,String flag)
	{
    	if(flag.equals("S"))//上半年
    	{
    		return year+"-01-01";
    	}else{
    		return year+"-07-01";
    	}
	}
	public static String getHalfYearLastDay(int year,String flag)
	{
		if(flag.equals("S"))//上半年
    	{
    		return year+"-06-30";
    	}else{
    		return year+"-12-31";
    	}
	}
	public static String getMonthFirstDay(String date)
	{
		if(date!=null &&date.length()>=7)
		{
			return date.substring(0, 7)+"-01";
		}else
    	return "";
	}
	//取得季度的第一天
public static String getQuarterFirstDay(int year,String quarter)
	{
		if(quarter!=null &&quarter.length()>0)
		{
			String date = "";
			if(quarter.equals("1")){
				date = year+"-01-01";
			}else if(quarter.equals("2")){
				date = year+"-04-01";
			}
			else if(quarter.equals("3")){
				date = year+"-07-01";
			}
			else if(quarter.equals("4")){
				date = year+"-10-01";
			}
			return date;
		}else
    	return "";
	}
//取得季度的最后一天
	public static String getQuarterLastDay(int year,String quarter)
	{
		if(quarter!=null &&quarter.length()>0)
		{
			String date = "";
			if(quarter.equals("1")){
				date = year+"-03-31";
			}else if(quarter.equals("2")){
				date = year+"-06-30";
			}
			else if(quarter.equals("3")){
				date = year+"-09-30";
			}
			else if(quarter.equals("4")){
				date = year+"-12-31";
			}
			return date;
		}else
    	return "";
	}
		public static String getMonthLastDay(String date)
	{
		if(date!=null &&date.length()>=7)
		{
			int year = Integer.parseInt(date.substring(0,4));
			int month = Integer.parseInt(date.substring(5,7));
			int mongthEnd=0;
			 if (month == 1 || month == 3 || month == 5 || month== 7 || month== 8 || month == 10 || month == 12) {
				 mongthEnd = 31;
		        }
		        if (month == 4 || month == 6 || month == 9 || month == 11) {
		        	mongthEnd = 30;
		        }
		        if (month == 2) {
		            boolean leap = leapYear(year);
		            if (leap) {
		            	mongthEnd = 29;
		            }
		            else {
		            	mongthEnd = 28;
		            }
		        }
			return year+"-"+(month>9?month:"0"+month)+"-"+mongthEnd;
		}else
    	return "";
	}
	/**
	 * @Description 取得当前月份的截止日期
	 * @param date：时间字符串；
	 * @return int
	 * @author DZZ，2011-07-07
	 */
	public static int getMonthEnd(String date)
	{
		int mongthEnd=0;
		if(date.length()>=7)
		{
			int year = Integer.parseInt(date.substring(0,4));
			int month = Integer.parseInt(date.substring(5,7));
			 if (month == 1 || month == 3 || month == 5 || month== 7 || month== 8 || month == 10 || month == 12) {
				 mongthEnd = 31;
		        }
		        if (month == 4 || month == 6 || month == 9 || month == 11) {
		        	mongthEnd = 30;
		        }
		        if (month == 2) {
		            boolean leap = leapYear(year);
		            if (leap) {
		            	mongthEnd = 29;
		            }
		            else {
		            	mongthEnd = 28;
		            }
		        }
		}
    	return mongthEnd;
	}
	 /**
     * 功能：判断输入年份是否为闰年<br>
     * @param year
     * @return 是：true  否：false
     * @author dzz
     */
    public static boolean leapYear(int year) {
        boolean leap;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) leap = true;
                else leap = false;
            }
            else leap = true;
        }
        else leap = false;
        return leap;
    }
	/**
	 * @Description 取得两个时间的相隔年限
	 * @param date1，String类型;date2，String类型;flag:1 取得满一年算为一年；0：半年算为一年；
	 * @return String
	 * @author DZZ，2011-04-17
	 */
	public static int getDefYear(String date1,String date2,String flag)
	{
		int def=0;
		if(date1.length()>=4 && date2.length()>=4)
		{
			def=Integer.parseInt(date1.substring(0,4))-Integer.parseInt(date2.substring(0,4))+1;
			if(date1.length()>=7 && date2.length()>=7)
			{
				int month1=Integer.parseInt(date1.substring(5,7))-Integer.parseInt(date2.substring(5,7))+1;
				if(flag.equals("0"))
				{
					if(def>1&&month1<-6){def--;}
					else if(def<=1&&month1<6){def--;}
				}
				else if(flag.equals("1"))
				{
					if(def==1){def--;}
					else if(month1<1){def--;}
				}
			}
		}
    	return def;
	}
	public static  int getDefMonth(String date1,String date2) throws ParseException
	{
		int   rtn   =   Integer.MIN_VALUE; 
		if   (date1   !=   null   &&   date2   !=   null && date1.length()>0  &&   date2.length()>0)   
		{ 
			if(date2.length()<=7 && date1.length()>7){date2=date2+date1.substring(7);}
			if(date1.length()<=7 && date2.length()>7){date1=date1+date2.substring(7);}
			Date date11= dateFormat.parse(date1);
			Date date21= dateFormat.parse(date2);
			long   lngMinMilSec   =   date11.getTime(); 
			long   lngMaxMilSec   =   date21.getTime(); 
			rtn   =   (int)   ((lngMaxMilSec   -   lngMinMilSec)   /   (1000   *   60   *   60   *   24*30)); 
		}
		return rtn;
	}
	/**
	 * @Description 取得给定日期的下一月初信息（默认是当前时间）
	 * @param date，String类型
	 * @return String
	 * @author DZZ，2011-04-15
	 */
	public String getOtherMonth(String date,int monthNum)
	{
		String nextmonth="";
		if(date==null||date.trim().length()<=0){date=getCurDateTime("yyyy-MM-dd");}
		if(date.length()>=7)
		{
			String year=date.substring(0,4);
			int month1=Integer.parseInt(date.substring(5,7))+monthNum;
			if(month1>12)
			{
				year=String.valueOf(Integer.parseInt(year)+month1/12);
				month1=month1%12;
			}
			String month=month1 >= 10 ? String.valueOf(month1) : ("0" + month1);
			nextmonth=year+"-"+month+"-01";
		}
    	return nextmonth;
	}
	/**
	 * @Description 取得给定日期的下一月初信息（默认是当前时间）
	 * @param date，String类型
	 * @return String
	 * @author DZZ，2011-04-15
	 */
	public String getNextMonth(String date)
	{
		String nextmonth="";
		if(date==null||date.trim().length()<=6){date=getCurDateTime("yyyy-MM-dd");}
		if(date.length()>=7)
		{
			String year=date.substring(0,4);
			int month1=Integer.parseInt(date.substring(5,7))+1;
			if(month1==13)
			{
				month1=1;
				year=String.valueOf(Integer.parseInt(year)+1);
			}
			
			String month=month1 >= 10 ? String.valueOf(month1) : ("0" + month1);
			nextmonth=year+"-"+month+"-01";
		}
    	return nextmonth;
	}
	
	/**
	 * @Description 检查14位内无格式时间串是否是合法的时间值，输入格式如：yyyyMMdd、yyyyMMddHHmmss(24小时制)等
	 * @param strDateTime，String类型，不支持12小时制校验
	 * @return boolean
	 * @author KFC，2007.5.9
	 */
	public static boolean checkUnfmtDate(String strDateTime){
		boolean bOk = false;
		int n, y, r, h, m, s;
		
		int len = strDateTime.length();
		if (len == 4) {
			n = Integer.parseInt(strDateTime);
			bOk = n > 1850 && n < 2200;
		} else if (len == 6) {
			n = Integer.parseInt(strDateTime.substring(0, 4));
			y = Integer.parseInt(strDateTime.substring(4, 6));
			bOk = (n > 1850 && n < 2200) && (y > 0 && y < 13);
		} else if (len >= 6 && len <= 14) {
			n = Integer.parseInt(strDateTime.substring(0, 4));
			y = Integer.parseInt(strDateTime.substring(4, 6));
			r = Integer.parseInt(strDateTime.substring(6, 8));
			bOk = (n > 1850 && n < 2200) && (y > 0 && y < 13);
			if (bOk) {
				bOk = r > 0 && r <= 31;
				if (bOk) {
					if (y == 4 || y == 6 || y == 9 || y == 11)
						bOk = r <= 30;
					else if (y == 2) {
						if (n % 100 != 0 && n % 4 == 0 || n % 400 == 0)						
							bOk = r <= 29;
						else
							bOk = r <= 28;
					}
				}
			}
			
			if (bOk) {
				switch (len) {
					case 8:
						break;
					case 10:
						h = Integer.parseInt(strDateTime.substring(8, 10));
						bOk = h >= 0 && h < 24;
					case 12:
						h = Integer.parseInt(strDateTime.substring(8, 10));
						m = Integer.parseInt(strDateTime.substring(10, 12));
						bOk = (h > 0 && h < 24) && (m >= 0 && m < 60 );
					case 14:
						h = Integer.parseInt(strDateTime.substring(8, 10));
						m = Integer.parseInt(strDateTime.substring(10, 12));
						s = Integer.parseInt(strDateTime.substring(10, 14));
						bOk = (h > 0 && h < 24) && (m >= 0 && m < 60 ) && (s <= 0 && s < 60);
					default:
						bOk = false;
						break;
				}
			}
		}
		return bOk;
	}
	
	/**
	 * @Description 检查带格式时间串是否是合法的时间值，输入格式如：yyyy-MM-dd、yyyy/MM/dd HH:mm，（根据系统需要再补充实现）
	 * @param strDateTime，String类型
	 * @return boolean
	 * @author KFC，2007.5.9
	 */
	public static boolean checkFmtDate(String strDateTime){
		boolean bOk = false;
			
		return bOk;
	}

	/**
	 * @Description 时间字符串的日期格式转换，遇到非法时间时自动调整（通用，支持所有转换），例如："yyyy.MM.dd" 转换为"yyyy-MM-dd"等
	 * @param strDate，String类型
	 * @param srcFormat，源格式，指strDate的格式
	 * @param desFormat，目标格式
	 * @return String
	 * @author KFC，2007.6.12（原名：transFmtDateTime）
	 */
	public static String formatStrDateTime(String strDate, String srcFormat, String desFormat){
		try{
			
		  SimpleDateFormat formatter = new SimpleDateFormat(srcFormat);
		  Date d=formatter.parse(strDate);
		  formatter.applyPattern(desFormat);

		  return formatter.format(d);
		} catch (ParseException e) {
			return strDate;
		}
	}

	/**
	 * @Description 把8位无格式时间串转换成带日期格式的时间串（特定转换）：输入串"yyyyMMdd" 转换成 "yyyy-MM-dd"
	 * @param strDate，String类型，只针对8位串"yyyyMMdd"
	 * @return String
	 * @author KFC，2007.6.12（原名：transToFmtDate_yyyyMMdd）
	 */
	public static String transToDate_yyyyMMdd(String strDate){
		return formatStrDateTime(strDate, "yyyyMMdd", "yyyy-MM-dd");
	}

	/**
	 * @Description 把14位无格式时间串转换成带日期格式的时间串（特定转换）：输入串"yyyyMMddHHmmss" 转换成 "yyyy-MM-dd HH:mm:ss"
	 * @param strDate，String类型，只针对14位串"yyyyMMddHHmmss"
	 * @return String
	 * @author KFC，2007.6.12（原名：transToFmtDate_yyyyMMddHHmmss）
	 */
	public static String formatStrDate_yyyyMMddHHmmss(String strDate){
		return formatStrDateTime(strDate, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @Description 根据长度，把14位之内无格式时间串转换成常用的日期格式的时间串（不带时间合法性校验），如：输入串"yyyyMMddHHmm" 转换成 "yyyy-MM-dd HH:mm"
	 * @param strDateTime，String类型
	 * @return String
	 * @author KFC，2007.5.9（原名：transToFmtDateTime）
	 */
	public static String formatStrDateTime(String strDateTime){
		//先已经是格式化的字符
		strDateTime.replaceAll("-", "");
		strDateTime.replaceAll(":", "");
		strDateTime.replaceAll(" ", "");
		StringBuffer str=new StringBuffer();
		int len=strDateTime.length();
		if(len>=4){
			str.append(strDateTime.substring(0,4)+"-"); 
		}
		if(len>=6){
			str.append(strDateTime.substring(4,6)+"-");  
		}
		if(len>=8){
		  str.append(strDateTime.substring(6,8)+"");   
		}
		if(len>=10){
		  str.append(" "+strDateTime.substring(8,10));   
		}
		if(len>=12){
		  str.append(":"+strDateTime.substring(10,12));   
		}
		if(len>=14){
		  str.append(":"+strDateTime.substring(12,14));   
		}
		return str.toString();
	}

	/**
	 * @Description 字符串的日期格式转换，把无格式的时间字符串"yyyyMMdd"转换为中文时间格式"yyyy年MM月dd日"
	 * @param strDate，String类型
	 * @return String
	 * @author KFC，2007.6.14（原名：transToFmtDate_yyyyMMddForCs）
	 */
	public static String formatStrDate_yyyyMMddForCs(String strDate){
		 return formatStrDateTime(strDate, "yyyyMMdd", "yyyy年MM月dd日");
	}
	
	/**
	 * @Description 字符串的日期格式转换，把无格式的时间字符串"yyyyMMddHHmmss"转换为中文时间格式"yyyy年MM月dd日 HH时mm分ss秒"
	 * @param strDate，String类型
	 * @return String
	 * @author KFC，2007.6.14（原名：transToFmtDate_yyyyMMddHHmmssForCs）
	 */
	public static String formatStrDate_yyyyMMddHHmmssForCs(String strDate){
		 return formatStrDateTime(strDate, "yyyyMMddHHmmss", "yyyy年MM月dd日 HH时mm分ss秒");
	}

	/**
	 * @Description 把时间格式字符串转化成Date类型
	 * @param strDate，String类型
	 * @param pattern，String类型，日期格式样式
	 * @return Date
	 * @author KFC，2007.6.17
	 */
	public static Date getDateFromStr(String strDate, String pattern){
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		try {
			Date d=formatter.parse(strDate);
			return d;
		} catch (ParseException e) {
			System.out.println(">>>转化字符串为Date类型出错:"+e.getMessage());
		}
		return null;
	}

	/**
	 * @Description 把时间格式字符串转化成Calendar类型
	 * @param strDate，String类型
	 * @param pattern，String类型，日期格式样式
	 * @return Calendar
	 * @author KFC，2007.6.17
	 */
	public static Calendar getCalendarFromStr(String strDate, String pattern){
		Date d=getDateFromStr(strDate, pattern);
		Calendar cal=Calendar.getInstance();
		if(d!=null){
		 cal.setTime(d);
		}
		return cal;
	}

	/**
	 * @Description 得到格式化的当前时间字符串（通用方法）
	 * @param format，String类型，格式样式
	 * 格式样式有："yyyyMMdd"，"yyyyMMddHHmm"，"yyyyMMddHHmmss"(24小时制)，"yyyy-MM-dd HH:mm:ss"，
	 * 			 "yyyy年MM月dd日"，"yyyy年MM月dd日 HH时mm分ss秒"，"yyyy年MM月dd日 HH:mm:ss"，
	 *           "yyyyMMddhhmmss"(12小时制)。。。
	 * @return String
	 * @author KFC，2007.5.9
	 */
	public static String getCurDateTime(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String curDateTime = formatter.format(cal.getTime());
		return curDateTime;
	}

	/**
	 * @Description 得到格式化的当前时间字符串（特定方法）：返回格式"yyyyMMdd"
	 * @return String
	 * @author KFC，2007.6.1
	 */
	public static String getCurDate_yyyyMMdd() {
		return getCurDateTime("yyyyMMdd");
	}
	
	/**
	 * @Description 得到格式化的当前时间字符串（特定方法）：返回格式"yyyy-MM-dd"
	 * @return String
	 * @author KFC，2007.6.1
	 */
	public static String getCurDate_yyyy_MM_dd() {
		return getCurDateTime("yyyy-MM-dd");
	}

	/**
	 * @Description 得到格式化的当前时间字符串（特定方法）：返回格式"yyyyMMddHHmmss"
	 * @return String
	 * @author KFC，2007.6.1
	 */
	public static String getCurDate_yyyyMMddHHmmss() {
		return getCurDateTime("yyyyMMddHHmmss");
	}
	
	
	/**
	 * @Description 得到格式化的当前时间字符串（特定方法）：返回格式"yyyy-MM-dd HH:mm:ss"
	 * @return String
	 * @author KFC，2007.6.1
	 */
	public static String getCurDate_yyyyMMddHHmmssRe() {
		return getCurDateTime("yyyy-MM-dd HH:mm:ss");
	}

	/**
	* @Description 将数字日期全部转换位汉字表达式，例如：将2003年02月13日这样的日期格式转换成 二ＯＯ三年二月十三日
	* @param date，String类型，输入格式必须是yyyy年MM月dd日
	* @return String
	* @author KFC，2007.6.17
	*/
	public static String transStrDateToCs(String date) {
		String day = null;
		String mouth = null;
		String year = null;

		if (date.length() == 11) {
			year = getMapCsForNum(date.substring(0, 4));
			mouth = transMouthToCs(date.substring(5, 7));
			day = transDayToCs(date.substring(8, 10));

			return year + "年" + mouth + "月" + day + "日";
		}
		return date;
	}

	/**
	* @Description 数字日把转换成汉字表达式
	* @param day，String
	* @return String
	* @author KFC，2007.6.17
	*/
	private static String transDayToCs(String day) {
		int l = day.length();
		String tmp = "";
		String rlt = "";
		if (l == 2) {
			String s1 = day.substring(0, 1);
			String s2 = day.substring(1, 2);
			int p1 = Integer.parseInt(s1);
			int p2 = Integer.parseInt(s2);
			switch (p1) {
			case 0:
				s1 = "";
				break;
			case 1:
				s1 = "十";
				break;
			case 2:
				s1 = "二十";
				break;
			case 3:
				s1 = "三十";
				break;
			}

			tmp = getMapCsForNum(s2);
			if (p1 > 0 && p2 == 0) {
				tmp = "";
			}
			rlt = s1 + tmp;
		}
		return rlt;
	}

	/**
	* @Description 数字月份把转换成汉字表达式
	* @param mouth，String
	* @return String
	* @author KFC，2007.6.17
	*/
	private static String transMouthToCs(String mouth) {
		int l = mouth.length();
		String tmp = "";
		String rlt = "";
		if (l == 2) {
			String s1 = mouth.substring(0, 1);
			String s2 = mouth.substring(1, 2);
			int p1 = Integer.parseInt(s1);
			int p2 = Integer.parseInt(s2);
			switch (p1) {
			case 0:
				s1 = "";
				break;
			case 1:
				s1 = "十";
				break;
			}

			tmp = getMapCsForNum(s2);
			if (p1 > 0 && p2 == 0) {
				tmp = "";
			}
			rlt = s1 + tmp;
		}
		return rlt;
	}
public static String comptime(String beginTime,String endTime){
        java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Calendar c1=java.util.Calendar.getInstance();
        java.util.Calendar c2=java.util.Calendar.getInstance();
        try
        {
            c1.setTime(df.parse(beginTime));
            c2.setTime(df.parse(endTime));
        }catch(java.text.ParseException e){
            System.err.println("转换错误");
        }
        int result=c1.compareTo(c2);
        if(result==0)
            return "0";
        else if(result<0)
        	return "-1";
        else
        	return "1";
		
	}
	/**
	* @Description 数字字符转换成中文汉字字符，例如：“1”转换成“一”
	* @param numchar，String
	* @return String
	* @author KFC，2007.6.17
	*/
public static String getMapCsForNum(String numchar){
		int len = numchar.length();
		int p;
		String ch = "";
		String rlt = "";
		String tmp = "";
		for (int i=0; i<len; i++){
			ch = numchar.substring(i, i+1);
			p = Integer.parseInt(ch);
			switch (p) {
			case 0:
				tmp = "○";
				break;
			case 1:
				tmp = "一";
				break;
			case 2:
				tmp = "二";
				break;
			case 3:
				tmp = "三";
				break;
			case 4:
				tmp = "四";
				break;
			case 5:
				tmp = "五";
				break;
			case 6:
				tmp = "六";
				break;
			case 7:
				tmp = "七";
				break;
			case 8:
				tmp = "八";
				break;
			case 9:
				tmp = "九";
				break;
			}
			rlt += tmp;
		}
		return rlt;
	}
	public static String getDateBetw(String time){
		String year = formatStrDateTime(time,"yyyy-MM-dd","yyyy");
		try {
			if(CompareDate(time,year+"-02-01")){
				
				return "create_dt>='"+(year+"-02-01")+"' and create_dt<'"+((Integer.parseInt(year)+1)+"-02-01")+"'";
			}else{
				
				return "create_dt<'"+(year+"-02-01")+"' and create_dt>='"+((Integer.parseInt(year)-1)+"-02-01")+"'";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
