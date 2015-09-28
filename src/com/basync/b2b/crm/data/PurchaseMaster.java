package com.basync.b2b.crm.data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="tb_purchasemaster")
public class PurchaseMaster implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3091713211496948982L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id; 

	@Column(name="vendorId")
	private Integer vendorId;
	
	@Transient
	private String companyName;
	
	@Column(name="comments")
	private String comments;
	
	@Column(name="userId")
	private Integer userId;
	
	@Column(name="partyAccId")
	private Integer partyAccId;
	
	@Column(name="billNo")
	private String billNo;
	
	@Column(name="purchaseDate")
	private String purchaseDate;
	
	@Column(name="dueDate")
	private String dueDate;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="createdBy")
	private Integer createdBy;
	
	@Column(name="createdOn")
	private Timestamp createdOn;
	
	@Column(name="modifiedBy")
	private Integer modifiedBy;
	
	@Column(name="modfiedOn")
	private Timestamp modfiedOn;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="discount")
	private Double discount;
	
	@Column(name="tax")
	private Double tax;
	
	@Column(name="expenses")
	private Double expenses;
	
	@Column(name="exRate")
	private Double exRate;
	
	@Column(name="currency")
	private String currency;
	
	@Column(name="paymentTerm")
	private String paymentTerm;
	
	@Column(name="paidStatus")
	private String paidStatus; 
	
	@Column(name="paidAmount")
	private String paidAmount;
	
	@Column(name="glTransNo")
	private Integer glTransNo;
	
	@OneToMany(cascade= {CascadeType.ALL},fetch = FetchType.LAZY )
	@JoinColumn(name="id", nullable=false)
	private List<PurchaseDetails> purchaseDetailsList ;


	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * @return the vendorId
	 */
	public Integer getVendorId() {
		return vendorId;
	}


	/**
	 * @param vendorId the vendorId to set
	 */
	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}


	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}


	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}


	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	/**
	 * @return the billNo
	 */
	public String getBillNo() {
		return billNo;
	}


	/**
	 * @param billNo the billNo to set
	 */
	public void setBillNo(String billNo) {
		this.billNo = billNo;
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


	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}


	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
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
	 * @return the createdBy
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}


	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}


	/**
	 * @return the createdOn
	 */
	public Timestamp getCreatedOn() {
		return createdOn;
	}


	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}


	/**
	 * @return the modifiedBy
	 */
	public Integer getModifiedBy() {
		return modifiedBy;
	}


	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	/**
	 * @return the modfiedOn
	 */
	public Timestamp getModfiedOn() {
		return modfiedOn;
	}


	/**
	 * @param modfiedOn the modfiedOn to set
	 */
	public void setModfiedOn(Timestamp modfiedOn) {
		this.modfiedOn = modfiedOn;
	}


	/**
	 * @return the purchaseDetailsList
	 */
	public List<PurchaseDetails> getPurchaseDetailsList() {
		return purchaseDetailsList;
	}


	/**
	 * @param purchaseDetailsList the purchaseDetailsList to set
	 */
	public void setPurchaseDetailsList(List<PurchaseDetails> purchaseDetailsList) {
		this.purchaseDetailsList = purchaseDetailsList;
	}


	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}


	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}


	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
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
	 * @return the expenses
	 */
	public Double getExpenses() {
		return expenses;
	}


	/**
	 * @param expenses the expenses to set
	 */
	public void setExpenses(Double expenses) {
		this.expenses = expenses;
	}


	/**
	 * @return the exRate
	 */
	public Double getExRate() {
		return exRate;
	}


	/**
	 * @param exRate the exRate to set
	 */
	public void setExRate(Double exRate) {
		this.exRate = exRate;
	}


	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}


	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}


	/**
	 * @return the paymentTerm
	 */
	public String getPaymentTerm() {
		return paymentTerm;
	}


	/**
	 * @param paymentTerm the paymentTerm to set
	 */
	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}


	/**
	 * @return the paidStatus
	 */
	public String getPaidStatus() {
		return paidStatus;
	}


	/**
	 * @param paidStatus the paidStatus to set
	 */
	public void setPaidStatus(String paidStatus) {
		this.paidStatus = paidStatus;
	}


	/**
	 * @return the paidAmount
	 */
	public String getPaidAmount() {
		return paidAmount;
	}


	/**
	 * @param paidAmount the paidAmount to set
	 */
	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}


	/**
	 * @return the glTransNo
	 */
	public Integer getGlTransNo() {
		return glTransNo;
	}


	/**
	 * @param glTransNo the glTransNo to set
	 */
	public void setGlTransNo(Integer glTransNo) {
		this.glTransNo = glTransNo;
	}


	/**
	 * @return the partyAccId
	 */
	public Integer getPartyAccId() {
		return partyAccId;
	}


	/**
	 * @param partyAccId the partyAccId to set
	 */
	public void setPartyAccId(Integer partyAccId) {
		this.partyAccId = partyAccId;
	}


	
	
}
