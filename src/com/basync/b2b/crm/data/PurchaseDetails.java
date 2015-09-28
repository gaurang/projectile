package com.basync.b2b.crm.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="tb_purchaseDetails")
public class PurchaseDetails implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5336563025476960364L;

	@Id
	@Column(name="purchaseId", insertable = false, updatable = false)
	private Integer purchaseId; 
	
	@Id
	@Column(name="pktCode")
	private String pktCode; 
	
	@Id
	@Column(name="parcelId")
	private String parcelId; 
	
	@Column(name="rate")
	private Double rate; 
	
	@Column(name="discount")
	private Double discount; 
	
	@Column(name="tax")
	private Double tax; 
	
	@Column(name="status")
	private Integer status; 
	
	@Column(name="wt")
	private Double wt; 
	
	@Column(name="totalrate")
	private Double totalRate; 
	
	@Column(name="finalrate")
	private Double finalRate; 
	
	@Column(name="fixedexp")
	private Double fixedExp; 
	
	@Column(name="percentexp")
	private Double percentExp; 
	
	@ManyToOne
	@JoinColumn(name="partyAddId", insertable = false, updatable = false)
	private PurchaseMaster purchaseMaster;
	
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
	 * @return the pktCode
	 */
	public String getPktCode() {
		return pktCode;
	}

	/**
	 * @param pktCode the pktCode to set
	 */
	public void setPktCode(String pktCode) {
		this.pktCode = pktCode;
	}

	/**
	 * @return the rate
	 */
	public Double getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(Double rate) {
		this.rate = rate;
	}

	/**
	 * @return the discount
	 */
	public Double getDiscount() {
		return discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	/**
	 * @return the tax
	 */
	public Double getTax() {
		return tax;
	}

	/**
	 * @param tax the tax to set
	 */
	public void setTax(Double tax) {
		this.tax = tax;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the wt
	 */
	public Double getWt() {
		return wt;
	}

	/**
	 * @param wt the wt to set
	 */
	public void setWt(Double wt) {
		this.wt = wt;
	}

	/**
	 * @return the totalRate
	 */
	public Double getTotalRate() {
		return totalRate;
	}

	/**
	 * @param totalRate the totalRate to set
	 */
	public void setTotalRate(Double totalRate) {
		this.totalRate = totalRate;
	}

	/**
	 * @return the finalRate
	 */
	public Double getFinalRate() {
		return finalRate;
	}

	/**
	 * @param finalRate the finalRate to set
	 */
	public void setFinalRate(Double finalRate) {
		this.finalRate = finalRate;
	}

	/**
	 * @return the fixedExp
	 */
	public Double getFixedExp() {
		return fixedExp;
	}

	/**
	 * @param fixedExp the fixedExp to set
	 */
	public void setFixedExp(Double fixedExp) {
		this.fixedExp = fixedExp;
	}

	/**
	 * @return the percentExp
	 */
	public Double getPercentExp() {
		return percentExp;
	}

	/**
	 * @param percentExp the percentExp to set
	 */
	public void setPercentExp(Double percentExp) {
		this.percentExp = percentExp;
	}
	/**
	 * @return the purchaseMaster
	 */
	public PurchaseMaster getPurchaseMaster() {
		return purchaseMaster;
	}

	/**
	 * @param purchaseMaster the purchaseMaster to set
	 */
	public void setPurchaseMaster(PurchaseMaster purchaseMaster) {
		this.purchaseMaster = purchaseMaster;
	}

	/**
	 * @return the parcelId
	 */
	public String getParcelId() {
		return parcelId;
	}

	/**
	 * @param parcelId the parcelId to set
	 */
	public void setParcelId(String parcelId) {
		this.parcelId = parcelId;
	}

}
