package com.basync.b2b.crm.data;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tb_paymentDetails")
public class PaymentDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5990409819874489829L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="paymentId", insertable = false, updatable = false)
	private Integer paymentId;
	
	@Column(name="invId")
	private Integer invId;
	
	@Column(name="payType")
	private String payType;
	
	@Column(name="amount")
	private BigDecimal amount;
	
	@Column(name="bank")
	private String bank;
	
	@Column(name="bankAccNo")
	private String bankAccNo;
	
	@Column(name="chequeDate")
	private String chequeDate;
	
	@Column(name="chequeNo")
	private String chequeNo;
	
	@Column(name="clearDate")
	private String clearDate;

	@Column(name="clearStatus")
	private Integer clearStatus;
	
	@Column(name="dsc")
	private String dsc;
	
	@Transient
	private String accountCode;
	
	private BigDecimal USD; 
	
	private BigDecimal exRate;
	
	private BigDecimal actualEnteredAmt;
	
	/**
	 * @return the accountCode
	 */
	public String getAccountCode() {
		return accountCode;
	}


	/**
	 * @param accountCode the accountCode to set
	 */
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
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

	@ManyToOne
	@JoinColumn(name="paymentId", insertable = false, updatable = false)
	private Payment payment;


	/**
	 * @return the paymentId
	 */
	public Integer getPaymentId() {
		return paymentId;
	}


	/**
	 * @param paymentId the paymentId to set
	 */
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
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
	 * @return the payment
	 */
	public Payment getPayment() {
		return payment;
	}


	/**
	 * @param payment the payment to set
	 */
	public void setPayment(Payment payment) {
		this.payment = payment;
	}


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
	 * @return the uSD
	 */
	public BigDecimal getUSD() {
		return USD;
	}


	/**
	 * @param uSD the uSD to set
	 */
	public void setUSD(BigDecimal uSD) {
		USD = uSD;
	}


	/**
	 * @return the exRate
	 */
	public BigDecimal getExRate() {
		return exRate;
	}


	/**
	 * @param exRate the exRate to set
	 */
	public void setExRate(BigDecimal exRate) {
		this.exRate = exRate;
	}


	/**
	 * @return the actualEnteredAmt
	 */
	public BigDecimal getActualEnteredAmt() {
		return actualEnteredAmt;
	}


	/**
	 * @param actualEnteredAmt the actualEnteredAmt to set
	 */
	public void setActualEnteredAmt(BigDecimal actualEnteredAmt) {
		this.actualEnteredAmt = actualEnteredAmt;
	}
	
	
	
}
