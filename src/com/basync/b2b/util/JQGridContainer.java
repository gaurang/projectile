package com.basync.b2b.util;

import java.util.List;
import java.util.Map;

public class JQGridContainer {
	
	private int page;
	private int total;
	private int records;
	private List<JQGridRow> rows;
	private Map userdata;
	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}
	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
	/**
	 * @return the records
	 */
	public Integer getRecords() {
		return records;
	}
	/**
	 * @param records the records to set
	 */
	public void setRecords(Integer records) {
		this.records = records;
	}
	/**
	 * @return the rows
	 */
	public List<JQGridRow> getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<JQGridRow> rows) {
		this.rows = rows;
	}
	/**
	 * @return the userdata
	 */
	public Map getUserdata() {
		return userdata;
	}
	/**
	 * @param userdata the userdata to set
	 */
	public void setUserdata(Map userdata) {
		this.userdata = userdata;
	}


}
