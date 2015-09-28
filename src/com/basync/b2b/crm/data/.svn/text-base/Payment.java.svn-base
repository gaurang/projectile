package com.basync.b2b.crm.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name="tb_payment")
public class Payment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1631773211958746930L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="partyAccId")
	private Integer partyAccId;
	
	@Transient
	private String companyName;
	
	@Column(name="paymentDate")
	private String paymentDate;
	
	@Column(name="mode")
	private String mode;
	
	@Column(name="bank")
	private String bank;
	
	@Column(name="bankAccNo")
	private String bankAccNo;
	
	@Column(name="amount")
	private BigDecimal amount;
	
	@Column(name="invId")
	private Integer invId;
	
	@Column(name="paymentTerm")
	private String paymentTerm;
	
	@Column(name="userId")
	private Integer userId;
	
	@Column(name="createdOn")
	private String createdOn;
	
	@Column(name="createdBY")
	private Integer createdBY;
	
	@Column(name="modifiedOn")
	private String modifiedOn;
	
	@Column(name="modifiedBy")
	private Integer modifiedBy;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="payFromPartyId")
	private Integer payFromPartyId;
	
	@Column(name="transNo")
	private Integer transNo;
	
	@Column(name="type")
	private Integer type;

	@Column(name="dsc")
	private String dsc;
	
	@Column(name="currency")
	private String currency;

	
	
	
	@Transient
	private Integer paymentDetailId;
	@Transient
	private Integer invoiceId;
	
	@Transient
	private String payType;
	
	@Transient
	private BigDecimal amt;
	
	@Transient
	private String bankName;
	
	@Transient
	private String bankAcc;
	
	@Transient
	private String chequeDate;
	
	@Transient
	private String chequeNo;
	
	@Transient
	private String clearDate;

	@Transient
	private Integer clearStatus;
	
	@Transient
	private String description;


	@OneToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name="paymentId", nullable=false)
	private  List<PaymentDetails> paymentDetails = new ArrayList<PaymentDetails>();


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
	 * @return the partyAddId
	 */
	public Integer getPartyAccId() {
		return partyAccId;
	}

	/**
	 * @param partyAddId the partyAddId to set
	 */
	public void setPartyAccId(Integer partyAccId) {
		this.partyAccId = partyAccId;
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
	 * @return the paymentDate
	 */
	public String getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * @return the bank
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * @param bank the bank to set
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * @return the bankAccNo
	 */
	public String getBankAccNo() {
		return bankAccNo;
	}

	/**
	 * @param bankAccNo the bankAccNo to set
	 */
	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the invId
	 */
	public Integer getInvId() {
		return invId;
	}

	/**
	 * @param invId the invId to set
	 */
	public void setInvId(Integer invId) {
		this.invId = invId;
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
	 * @return the createdOn
	 */
	public String getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the createdBY
	 */
	public Integer getCreatedBY() {
		return createdBY;
	}

	/**
	 * @param createdBY the createdBY to set
	 */
	public void setCreatedBY(Integer createdBY) {
		this.createdBY = createdBY;
	}

	/**
	 * @return the modifiedOn
	 */
	public String getModifiedOn() {
		return modifiedOn;
	}

	/**
	 * @param modifiedOn the modifiedOn to set
	 */
	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
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
	 * @return the payFromPartyId
	 */
	public Integer getPayFromPartyId() {
		return payFromPartyId;
	}

	/**
	 * @param payFromPartyId the payFromPartyId to set
	 */
	public void setPayFromPartyId(Integer payFromPartyId) {
		this.payFromPartyId = payFromPartyId;
	}

	/**
	 * @return the paymentDetails
	 */
	public List<PaymentDetails> getPaymentDetails() {
		return paymentDetails;
	}

	/**
	 * @param paymentDetails the paymentDetails to set
	 */
	public void setPaymentDetails(List<PaymentDetails> paymentDetails) {
		this.paymentDetails = paymentDetails;
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
	 * @return the transNo
	 */
	public Integer getTransNo() {
		return transNo;
	}

	/**
	 * @param transNo the transNo to set
	 */
	public void setTransNo(Integer transNo) {
		this.transNo = transNo;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the dsc
	 */
	public String getDsc() {
		return dsc;
	}

	/**
	 * @param dsc the dsc to set
	 */
	public void setDsc(String dsc) {
		this.dsc = dsc;
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
	 * @return the invoiceId
	 */
	public Integer getInvoiceId() {
		return invoiceId;
	}

	/**
	 * @param invoiceId the invoiceId to set
	 */
	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	/**
	 * @return the payType
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * @param payType the payType to set
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * @return the amt
	 */
	public BigDecimal getAmt() {
		return amt;
	}

	/**
	 * @param amt the amt to set
	 */
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the bankAcc
	 */
	public String getBankAcc() {
		return bankAcc;
	}

	/**
	 * @param bankAcc the bankAcc to set
	 */
	public void setBankAcc(String bankAcc) {
		this.bankAcc = bankAcc;
	}

	/**
	 * @return the chequeDate
	 */
	public String getChequeDate() {
		return chequeDate;
	}

	/**
	 * @param chequeDate the chequeDate to set
	 */
	public void setChequeDate(String chequeDate) {
		this.chequeDate = chequeDate;
	}

	/**
	 * @return the chequeNo
	 */
	public String getChequeNo() {
		return chequeNo;
	}

	/**
	 * @param chequeNo the chequeNo to set
	 */
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	/**
	 * @return the clearDate
	 */
	public String getClearDate() {
		return clearDate;
	}

	/**
	 * @param clearDate the clearDate to set
	 */
	public void setClearDate(String clearDate) {
		this.clearDate = clearDate;
	}

	/**
	 * @return the clearStatus
	 */
	public Integer getClearStatus() {
		return clearStatus;
	}

	/**
	 * @param clearStatus the clearStatus to set
	 */
	public void setClearStatus(Integer clearStatus) {
		this.clearStatus = clearStatus;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the paymentDetailId
	 */
	public Integer getPaymentDetailId() {
		return paymentDetailId;
	}

	/**
	 * @param paymentDetailId the paymentDetailId to set
	 */
	public void setPaymentDetailId(Integer paymentDetailId) {
		this.paymentDetailId = paymentDetailId;
	}

}
