package com.basync.b2b.util;

import java.math.BigDecimal;


public class NumberUtils {
	public static final java.text.NumberFormat DEFAULT_DECIMAL_FORMAT =
	    new java.text.DecimalFormat ("#.0#################");
	public static final BigDecimal ZERO = new BigDecimal ("0");

	public static BigDecimal add (double a, double b) {
	    String s = DEFAULT_DECIMAL_FORMAT.format(a);
	    BigDecimal bd = new BigDecimal (s);
	    return add (bd, b);
	}

	public static BigDecimal add (BigDecimal a, double b) {
	    String s = DEFAULT_DECIMAL_FORMAT.format(b);
	    BigDecimal bd = new BigDecimal (s);
	    return add (a, bd);
	}

	public static BigDecimal add (BigDecimal a, BigDecimal b) {
	    if (a == null) return (b == null) ? ZERO : b;
	    return a.add (b);
	}
	
	public static boolean isGreaterThanBigDecimal(BigDecimal a, BigDecimal b) {
		int result = a.compareTo(b);
	    return (result == 1);
	    
	}
	
	public static boolean isSmallerThanBigDecimal(BigDecimal a, BigDecimal b) {
		int result = a.compareTo(b);
	    return (result == -1);
	    
	}
	
	public static boolean isEqualToBigDecimal(BigDecimal a, BigDecimal b) {
		int result = a.compareTo(b);
	    return (result == 0);
	    
	}
}
