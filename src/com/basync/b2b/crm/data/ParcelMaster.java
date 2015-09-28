package com.basync.b2b.crm.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tb_parcelMaster")
public class ParcelMaster implements Serializable {
	
	@Id
	@Column(name="code")
	private String code;

	@Column(name="ID")
	private Integer ID;
	
	/**
	 * @return the iD
	 */
	public Integer getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(Integer iD) {
		ID = iD;
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
	 * @return the deleteFlag
	 */
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	/**
	 * @param deleteFlag the deleteFlag to set
	 */
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Column(name="parcelType")
 	private String parcelType;
	
	@Column(name="baseRate")
	private Double baseRate;
	
	@Column(name="rate")
	private Double rate;
	
	@Column(name="colFr")
	private String colFr;
	
	@Column(name="colFr_so")
	private Double colFr_so;
	
	@Column(name="colTo")
	private String colTo;
	
	@Column(name="colTo_so")
	private Double colTo_so;
	
	@Column(name="puFr")
	private String puFr;
	
	@Column(name="puFr_so")
	private Double puFr_so;
	
	@Column(name="puTo")
	private String puTo;
	
	@Column(name="puTo_so")
	private Double puTo_so;
	
	@Column(name="shFr")
	private String shFr;
	
	@Column(name="shFr_so")
	private Double shFr_so;
	
	@Column(name="shTo")
	private String shTo;
	
	@Column(name="shTo_so")
	private Double shTo_so;
	
	@Column(name="rootPkt")
	private String rootPkt;
	
	@Column(name="roughPkt")
	private String roughPkt;
	
	@Column(name="costRate")
	private Double costRate;

	@Column(name="cts")
	private Double cts;
	
	@Column(name="totalCts")
	private Double totalCts;

	@Column(name="status")
	private Integer status;
	
	@Column(name="deleteflag")
	private Integer deleteFlag;
	
	@Column(name="PurchaseParcel")
	private Integer PurchaseParcel;
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the purchaseParcel
	 */
	public Integer getPurchaseParcel() {
		return PurchaseParcel;
	}

	/**
	 * @param purchaseParcel the purchaseParcel to set
	 */
	public void setPurchaseParcel(Integer purchaseParcel) {
		PurchaseParcel = purchaseParcel;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	
	/**
	 * @return the parcelType
	 */
	public String getParcelType() {
		return parcelType;
	}

	/**
	 * @param parcelType the parcelType to set
	 */
	public void setParcelType(String parcelType) {
		this.parcelType = parcelType;
	}

	/**
	 * @return the baseRate
	 */
	public Double getBaseRate() {
		return baseRate;
	}

	/**
	 * @param baseRate the baseRate to set
	 */
	public void setBaseRate(Double baseRate) {
		this.baseRate = baseRate;
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
	 * @return the colFr
	 */
	public String getColFr() {
		return colFr;
	}

	/**
	 * @param colFr the colFr to set
	 */
	public void setColFr(String colFr) {
		this.colFr = colFr;
	}

	/**
	 * @return the colFr_so
	 */
	public Double getColFr_so() {
		return colFr_so;
	}

	/**
	 * @param colFr_so the colFr_so to set
	 */
	public void setColFr_so(Double colFr_so) {
		this.colFr_so = colFr_so;
	}

	/**
	 * @return the colTo
	 */
	public String getColTo() {
		return colTo;
	}

	/**
	 * @param colTo the colTo to set
	 */
	public void setColTo(String colTo) {
		this.colTo = colTo;
	}

	/**
	 * @return the colTo_so
	 */
	public Double getColTo_so() {
		return colTo_so;
	}

	/**
	 * @param colTo_so the colTo_so to set
	 */
	public void setColTo_so(Double colTo_so) {
		this.colTo_so = colTo_so;
	}

	/**
	 * @return the puFr
	 */
	public String getPuFr() {
		return puFr;
	}

	/**
	 * @param puFr the puFr to set
	 */
	public void setPuFr(String puFr) {
		this.puFr = puFr;
	}

	/**
	 * @return the puFr_so
	 */
	public Double getPuFr_so() {
		return puFr_so;
	}

	/**
	 * @param puFr_so the puFr_so to set
	 */
	public void setPuFr_so(Double puFr_so) {
		this.puFr_so = puFr_so;
	}

	/**
	 * @return the puTo
	 */
	public String getPuTo() {
		return puTo;
	}

	/**
	 * @param puTo the puTo to set
	 */
	public void setPuTo(String puTo) {
		this.puTo = puTo;
	}

	/**
	 * @return the puTo_so
	 */
	public Double getPuTo_so() {
		return puTo_so;
	}

	/**
	 * @param puTo_so the puTo_so to set
	 */
	public void setPuTo_so(Double puTo_so) {
		this.puTo_so = puTo_so;
	}

	/**
	 * @return the shFr
	 */
	public String getShFr() {
		return shFr;
	}

	/**
	 * @param shFr the shFr to set
	 */
	public void setShFr(String shFr) {
		this.shFr = shFr;
	}

	/**
	 * @return the shFr_so
	 */
	public Double getShFr_so() {
		return shFr_so;
	}

	/**
	 * @param shFr_so the shFr_so to set
	 */
	public void setShFr_so(Double shFr_so) {
		this.shFr_so = shFr_so;
	}

	/**
	 * @return the shTo
	 */
	public String getShTo() {
		return shTo;
	}

	/**
	 * @param shTo the shTo to set
	 */
	public void setShTo(String shTo) {
		this.shTo = shTo;
	}

	/**
	 * @return the shTo_so
	 */
	public Double getShTo_so() {
		return shTo_so;
	}

	/**
	 * @param shTo_so the shTo_so to set
	 */
	public void setShTo_so(Double shTo_so) {
		this.shTo_so = shTo_so;
	}

	/**
	 * @return the rootPkt
	 */
	public String getRootPkt() {
		return rootPkt;
	}

	/**
	 * @param rootPkt the rootPkt to set
	 */
	public void setRootPkt(String rootPkt) {
		this.rootPkt = rootPkt;
	}

	/**
	 * @return the roughPkt
	 */
	public String getRoughPkt() {
		return roughPkt;
	}

	/**
	 * @param roughPkt the roughPkt to set
	 */
	public void setRoughPkt(String roughPkt) {
		this.roughPkt = roughPkt;
	}

	/**
	 * @return the costRate
	 */
	public Double getCostRate() {
		return costRate;
	}

	/**
	 * @param costRate the costRate to set
	 */
	public void setCostRate(Double costRate) {
		this.costRate = costRate;
	}

	/**
	 * @return the cts
	 */
	public Double getCts() {
		return cts;
	}

	/**
	 * @param cts the cts to set
	 */
	public void setCts(Double cts) {
		this.cts = cts;
	}

	/**
	 * @return the totalCts
	 */
	public Double getTotalCts() {
		return totalCts;
	}

	/**
	 * @param totalCts the totalCts to set
	 */
	public void setTotalCts(Double totalCts) {
		this.totalCts = totalCts;
	}
	
	
	

}
