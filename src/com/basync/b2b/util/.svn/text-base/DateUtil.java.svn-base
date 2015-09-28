
/**
 * <p>Title: DateUtil.java</p>
 * <p>Description:This class include Common date functions. </p>
 * <p>Copyright: Copyright (c) 2008- 2008</p>
 * <p>Date: Oct 16, 2008 <p>
 * <p>Company: C2LBIZ </p>
 * @author: Kunal J.
 * @version: 1.0
 */
package com.basync.b2b.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;




public class DateUtil {

	/**
	 * 
	 */
	public DateUtil() {
		// TODO Auto-generated constructor stub
	}
	

	public static String getDateFormat()
	{
		String tmpFormat="dd/MM/yyyy" ;//PropertiesUtil.getPropertyValue("dateformat");
		return tmpFormat;
	}
   
	public static String getString(java.sql.Timestamp a_dtDate) {
        String strDt = "";
        if (a_dtDate != null) {
              java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(getDateFormat() + " hh:mm:ss a");
              strDt = df.format(a_dtDate);
        }
        return strDt;
  }     

	public static String getUIDateFormat()
	{
		String appDateFormat = getDateFormat();
		String dateFormat=null;
		if(appDateFormat.equals("dd/MM/yyyy"))
		{
			dateFormat="%d/%m/%Y";
		}
		else if(appDateFormat.equals("MM/dd/yyyy"))
		{
			dateFormat="%m/%d/%Y" ;
		}
		else if(appDateFormat.equals("yyyy/dd/MM"))
		{
			dateFormat="%Y/%d/%m" ;
		}
		return dateFormat;
	}
	
	public static java.sql.Date getDate(String dtStr) {
		java.sql.Date dtDate = null;
		if (dtStr != null && dtStr.trim().length() > 0) {
			java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
					getDateFormat());
			java.util.Date dtUtil = null;

			try {
				dtUtil = df.parse(dtStr);
				dtDate = new java.sql.Date(dtUtil.getTime());
			} catch (Exception ex) {
				ex.printStackTrace();	
			}
		}
		return dtDate;
	}
	
	public static java.sql.Timestamp getTimestamp(String tsStr) {
		return new Timestamp (getDate(tsStr).getTime());
	}
	
	public static java.sql.Date getDate(String dtStr, String format) {
		java.sql.Date dtDate = null;
		if (dtStr != null && dtStr.trim().length() > 0) {
			java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
					format);
			java.util.Date dtUtil = null;

			try {
				dtUtil = df.parse(dtStr);
				dtDate = new java.sql.Date(dtUtil.getTime());
			} catch (Exception ex) {
				ex.printStackTrace();	
			}
		}
		return dtDate;
	}
	
	/**
	 * Converts the Date to String.
	 * 
	 * @param a_dtDate -
	 *            i/p date value
	 * @return String
	 * @throws 
	 */
	public static String getString(java.util.Date a_dtDate) {
		String strDt = "";

		if (a_dtDate != null) {
			java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(getDateFormat());
			strDt = df.format(a_dtDate);
		}

		return strDt;
	}
	
	public static String getStringWithTime(java.sql.Timestamp a_dtDate) {
		String strDt = "";

		if (a_dtDate != null) {
			java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(getDateFormat() + " hh:mm:ss a");
			strDt = df.format(a_dtDate);
		}

		return strDt;
	}	
	
	
    public static int getDifference(Date dateFrom, Date dateTo)
    {
          Calendar calendarFrom = new GregorianCalendar();
          calendarFrom.setTime(dateFrom);
          Calendar calendarTo = new GregorianCalendar();
          calendarTo.setTime(dateTo);
          int fromYear = calendarFrom.get(Calendar.YEAR);
          int fromMonth = calendarFrom.get(Calendar.MONTH);
          int fromDay = calendarFrom.get(Calendar.DAY_OF_MONTH);
          int toYear = calendarTo.get(Calendar.YEAR);
          int toMonth = calendarTo.get(Calendar.MONTH);
          int toDay = calendarTo.get(Calendar.DAY_OF_MONTH);
          int yearDiff = toYear - fromYear;
          int monthDiff = toMonth - fromMonth;
          int dayDiff = toDay - fromDay;
          if (dayDiff < 0)
          {
                monthDiff--;
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(dateTo);
                calendar.add(calendar.MONTH, -1);
                dayDiff = calendar.getActualMaximum(calendar.DAY_OF_MONTH) + dayDiff;
          }
          if (monthDiff < 0)
          {
                yearDiff--;
                monthDiff = 12 + monthDiff;
          }
          return (yearDiff * 10000 + monthDiff * 100 + dayDiff);
    }

	
	
	/**
	 * Method to provide the system date
	 * @return System Date 
	 */
	public static java.sql.Date getSystemDate(){
		return new java.sql.Date (new Date().getTime());
	}	
	
	/**
	 * Method to provide the maximum date
	 * @return Maximum Date 
	 */
	public static java.sql.Date getMaximumDate(){
		return getDate("12/12/2020","dd/MM/yyyy");
	}


	public static Timestamp addTime(Timestamp labelTs, String labelTimeStr) {
		Calendar tempCal = new GregorianCalendar();
		tempCal.setTimeInMillis(labelTs.getTime());
		
//		String []splitTime = StringUtils.split(labelTimeStr, ":");
//		tempCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(splitTime[0]));
//		tempCal.set(Calendar.MINUTE, Integer.parseInt(splitTime[1]));

		return new Timestamp(tempCal.getTimeInMillis());
	}
	
	public static String getTime(Timestamp labelTs) {
		Calendar tempCal = new GregorianCalendar();
		tempCal.setTimeInMillis(labelTs.getTime());

		return tempCal.get(Calendar.HOUR_OF_DAY)+":"+tempCal.get(Calendar.MINUTE);
	}	

}

