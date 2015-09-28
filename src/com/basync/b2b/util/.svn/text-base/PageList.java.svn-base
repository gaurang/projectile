package com.basync.b2b.util;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PageList implements Serializable {

	private static final long serialVersionUID = 2948317577055328009L;

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PageList.class);

	
	public static final int DEFAULT_PAGE_SIZE = 20;

	public static final int DEFAULT_MAX_LINKED_PAGES = 10;
	
	/**
	 * Search results
	 */
	private List results;

	/**
	 * Current page number
	 */
	private int pageNo;

	/**
	 * Total page number
	 */
	private int pageAmount;

	/**
	 * Size of each page
	 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/**
	 * Total records
	 */
	private int recordSize;
	
	/**
	 * Maximum number of page links to a few pages around the current one.
	 */
	private int maxLinkedPages = DEFAULT_MAX_LINKED_PAGES;
	
	/**
	 * URL on the page links
	 */
	private String url = null;

	/**
	 * Personal User Data
	 */
	private Map userdata;
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public PageList(List results, int pageNo, int pageSize, int recordSize) {
		this.results = results;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.recordSize = recordSize;
	}

	public PageList() {
	}

	public void setResults(List results) {
		this.results = results;
	}

	public List getResults() {
		return this.results;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public int getPageAmount() {
		logger.debug("pageAmount: "
				+ (int) Math.ceil((double) this.recordSize / this.pageSize));
		return (int) Math.ceil((double) this.recordSize / this.pageSize);
	}

	public int getRecordSize() {
		return this.recordSize;
	}

	public void setPageAmount(int pageAmount) {
		this.pageAmount = pageAmount;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}

	/**
	 * If there is "next": true, else: false
	 */
	public boolean hasNext() {
		return this.pageNo < this.pageAmount ? true : false;
	}

	/**
	 * If there is "previous": true, else: false
	 */
	public boolean hasPrevious() {
		return this.pageNo > 1 ? true : false;
	}

	/**
	 * 
	 * Display Page list information for debug.
	 */
	public String toString() {

		StringBuilder sBuilder = new StringBuilder();

		sBuilder.append("\r\npage list------------------------------------------");
		sBuilder.append("\r\npageAmount: \t" + getPageAmount());
		sBuilder.append("\r\npageNo: \t" + getPageNo());
		sBuilder.append("\r\nrecordSize: \t" + getRecordSize());
		sBuilder.append("\r\nPageSize: \t" + getPageSize());
		sBuilder.append("\r\ncur page record size: \t" + getCurPageRecordSize());
		sBuilder.append("\r\npage list------------------------------------------");

		return sBuilder.toString();

	}

	public int getCurPageRecordSize() {

		if (pageNo == 0 || recordSize == 0 || pageSize == 0) {
			return 0;
		} else if (pageNo < getPageAmount())
			return pageSize;
		else {
			return recordSize - ((pageNo - 1) * pageSize);
		}
	}
	
	/**
	 * @return the maxLinkedPages
	 */
	public int getMaxLinkedPages() {
		return maxLinkedPages;
	}

	/**
	 * @param maxLinkedPages the maxLinkedPages to set
	 */
	public void setMaxLinkedPages(int maxLinkedPages) {
		this.maxLinkedPages = maxLinkedPages;
	}

	/**
	 * Return the first page to which create a link around the current page.
	 */
	public int getFirstLinkedPage() {
		//return Math.max(1, getPageNo() - (getMaxLinkedPages() / 2));
		if(getPageAmount() <= getMaxLinkedPages())
			return 1;
		if(getPageNo() > (getPageAmount() - (getMaxLinkedPages() / 2)))
			return (getPageAmount() - getMaxLinkedPages() + 1);
		return Math.max(1, getPageNo() - (getMaxLinkedPages() / 2));
		
		
	}

	/**
	 * Return the last page to which create a link around the current page.
	 */
	public int getLastLinkedPage() {
		return Math.min(getFirstLinkedPage() + getMaxLinkedPages() - 1, getPageAmount());
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
