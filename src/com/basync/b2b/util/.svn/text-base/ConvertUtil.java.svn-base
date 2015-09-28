package com.basync.b2b.util;

public class ConvertUtil {
	public static Double stringToDouble(String no) {
		Double d =null;
		if(no!=null && !"".equals(no)){
			d = new Double(no);
		}
		return d;
	}
	public static Long stringToLong(String no) {
		Long d =null;
		if(no!=null && !"".equals(no)){
			d = new Long(no);
		}
		return d;
	}
	public static String getFieldName(String methodName) {
		return ConvertUtil.initLowerCase(methodName.substring(3));
	}
	
	public static String initLowerCase(String in) {
		return in.substring(0, 1).toLowerCase() + in.substring(1);
	}
}
