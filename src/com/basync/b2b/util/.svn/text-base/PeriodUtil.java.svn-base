package com.basync.b2b.util;

import java.util.Calendar;
import java.util.Date;

public class PeriodUtil {
	private Date startDate = null;
	private Date endDate = null;
	private int period =-1;
	public PeriodUtil(int period){
		this.period = period;
		calculate();
	}
	public void calculate(){
		//period problems
		//0: Day
		if(period == 0) {
			Calendar day = Calendar.getInstance();
			endDate = day.getTime();
			day.set(Calendar.HOUR_OF_DAY, 0);
			day.set(Calendar.MINUTE, 0);
			day.set(Calendar.SECOND, 0);
			day.set(Calendar.MILLISECOND, 0);
			startDate = day.getTime();
		} 
		//1: Yesterday
		else if (period == 1) {
			Calendar day = Calendar.getInstance();
			day.set(Calendar.HOUR_OF_DAY, 0);
			day.set(Calendar.MINUTE, 0);
			day.set(Calendar.SECOND, 0);
			day.set(Calendar.MILLISECOND, 0);
			endDate = day.getTime();
			day.add(Calendar.DATE, -1);
			startDate = day.getTime();
		}
		//2: This week
		else if (period == 2) {
			Calendar now = Calendar.getInstance();
			endDate = now.getTime();
			now.set(Calendar.HOUR_OF_DAY, 0);
			now.set(Calendar.MINUTE, 0);
			now.set(Calendar.SECOND, 0);
			now.set(Calendar.MILLISECOND, 0);
			int day = -(now.get(Calendar.DAY_OF_WEEK))+2;
			now.add(Calendar.DATE, day);
			startDate = now.getTime();
		}
		//3: Last week
		else if (period == 3) {
			Calendar now = Calendar.getInstance();
			now.set(Calendar.HOUR_OF_DAY, 0);
			now.set(Calendar.MINUTE, 0);
			now.set(Calendar.SECOND, 0);
			now.set(Calendar.MILLISECOND, 0);
			int day = -(now.get(Calendar.DAY_OF_WEEK)) + 2;
			now.add(Calendar.DATE, day);
			endDate = now.getTime();
			now.add(Calendar.DATE, -7);
			startDate = now.getTime();
		}
		//4: This month
		else if (period == 4) {
			Calendar now = Calendar.getInstance();
			endDate = now.getTime();
			now.set(Calendar.HOUR_OF_DAY, 0);
			now.set(Calendar.MINUTE, 0);
			now.set(Calendar.SECOND, 0);
			now.set(Calendar.MILLISECOND, 0);
			int day = -(now.get(Calendar.DAY_OF_MONTH)) + 1;
			now.add(Calendar.DATE, day);
			startDate = now.getTime();
		}
		//5: Last month
		else if (period == 5) {
			Calendar now = Calendar.getInstance();
			now.set(Calendar.HOUR_OF_DAY, 0);
			now.set(Calendar.MINUTE, 0);
			now.set(Calendar.SECOND, 0);
			now.set(Calendar.MILLISECOND, 0);
			int day = -(now.get(Calendar.DAY_OF_MONTH))+1;
			now.add(Calendar.DATE, day);
			endDate = now.getTime();
			now.add(Calendar.MONTH, -1);
			startDate = now.getTime();
		}
		//6: This year
		else if (period == 6) {
			Calendar now = Calendar.getInstance();
			endDate = now.getTime();
			now.set(Calendar.HOUR_OF_DAY, 0);
			now.set(Calendar.MINUTE, 0);
			now.set(Calendar.SECOND, 0);
			now.set(Calendar.MILLISECOND, 0);
			int day = -(now.get(Calendar.DAY_OF_YEAR))+1;
			now.add(Calendar.DATE, day);
			startDate = now.getTime();
		}
		//7: Last year
		else if (period == 7) {
			Calendar now = Calendar.getInstance();
			now.set(Calendar.HOUR_OF_DAY, 0);
			now.set(Calendar.MINUTE, 0);
			now.set(Calendar.SECOND, 0);
			now.set(Calendar.MILLISECOND, 0);
			int day = -(now.get(Calendar.DAY_OF_YEAR))+1;
			now.add(Calendar.DATE, day);
			endDate = now.getTime();
			now.add(Calendar.YEAR, -1);
			startDate = now.getTime();
		}
	}


	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the period
	 */
	public int getPeriod() {
		return period;
	}
	/**
	 * @param period the period to set
	 */
	public void setPeriod(int period) {
		this.period = period;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	public static void main(String [] args){
		int period =0;
		PeriodUtil pu = new PeriodUtil(period);
		pu.getEndDate();
		System.out.println(period);
		System.out.println(pu.getEndDate());
		System.out.println(pu.getStartDate());
		
		period = 1;
		pu = new PeriodUtil(period);
		System.out.println(period);
		System.out.println(pu.getEndDate());
		System.out.println(pu.getStartDate());
		
		period = 2;
		pu = new PeriodUtil(period);
		System.out.println(period);
		System.out.println(pu.getEndDate());
		System.out.println(pu.getStartDate());
		
		period = 3;
		pu = new PeriodUtil(period);
		System.out.println(period);
		System.out.println(pu.getEndDate());
		System.out.println(pu.getStartDate());
		
		period = 4;
		pu = new PeriodUtil(period);
		System.out.println(period);
		System.out.println(pu.getEndDate());
		System.out.println(pu.getStartDate());
		
		period = 5;
		pu = new PeriodUtil(period);
		System.out.println(period);
		System.out.println(pu.getEndDate());
		System.out.println(pu.getStartDate());
		
		period = 6;
		pu = new PeriodUtil(period);
		System.out.println(period);
		System.out.println(pu.getEndDate());
		System.out.println(pu.getStartDate());
		
		period = 7;
		pu = new PeriodUtil(period);
		System.out.println(period);
		System.out.println(pu.getEndDate());
		System.out.println(pu.getStartDate());
	}
}
