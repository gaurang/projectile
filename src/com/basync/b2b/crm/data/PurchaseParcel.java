package com.basync.b2b.crm.data;

import java.io.Serializable;

import javax.persistence.Column;

public class PurchaseParcel implements Serializable{
	private double totalcts;
	private String companyname;
	private String billno;
	private String purchaseDate;
	private String ID;
	private Integer purchaseId;
	/**
	 * @return the purchaseId
	 */
	public Integer getPurchaseId() {
		return purchaseId;
	}
	/**
	 * @param purchaseId the purchaseId to set
	 */
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}
	/**
	 * @return the totalcts
	 */
	public double getTotalcts() {
		return totalcts;
	}
	/**
	 * @param totalcts the totalcts to set
	 */
	public void setTotalcts(double totalcts) {
		this.totalcts = totalcts;
	}
	/**
	 * @return the companyname
	 */
	public String getCompanyname() {
		return companyname;
	}
	/**
	 * @param companyname the companyname to set
	 */
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	/**
	 * @return the billno
	 */
	public String getBillno() {
		return billno;
	}
	/**
	 * @param billno the billno to set
	 */
	public void setBillno(String billno) {
		this.billno = billno;
	}
	/**
	 * @return the purchaseDate
	 */
	public String getPurchaseDate() {
		return purchaseDate;
	}
	/**
	 * @param purchaseDate the purchaseDate to set
	 */
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
}
