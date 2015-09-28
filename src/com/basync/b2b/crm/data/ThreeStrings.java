package com.basync.b2b.crm.data;

import java.io.Serializable;

public class ThreeStrings implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String str1;
	
	private String str2;
	
	private String str3;

	/**
	 * @return the str1
	 */
	public String getStr1() {
		return str1;
	}

	/**
	 * @param str1 the str1 to set
	 */
	public void setStr1(String str1) {
		this.str1 = str1;
	}

	/**
	 * @return the str2
	 */
	public String getStr2() {
		return str2;
	}

	/**
	 * @param str2 the str2 to set
	 */
	public void setStr2(String str2) {
		this.str2 = str2;
	}

	/**
	 * @return the str3
	 */
	public String getStr3() {
		return str3;
	}

	/**
	 * @param str3 the str3 to set
	 */
	public void setStr3(String str3) {
		this.str3 = str3;
	}
}
