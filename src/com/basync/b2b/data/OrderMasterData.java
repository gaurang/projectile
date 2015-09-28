package com.basync.b2b.data;

import java.math.BigDecimal;
import java.util.List;

public class OrderMasterData {
	
	private Integer id;
	private Integer userId;
	private String orderDate;
	private String userName;
	private String companyName;
	private String contactPerson;
	private String comments;
	private Integer status; // Constant 1 -- pending , 2 Approved,  3- Rejected, 4-GENERAL 
	private String statusCode;
	private Integer approvedBy;
	private String modifiedOn;
	private String orderType;
	
	
	private Double totalAmount;
	private Double expences;
	private Double discount;
	private Double totalCts;
	private Double tax;
	private Double finalAmount;
	private List<PacketDetails> packetList;
	private Integer dgc;
	
	private Integer partyAccId;
	private Integer brokerId;
	private Double brokrage;
	private Integer subBrokerId;
	private Double subBrokrage;
	
	private Integer refPartyId;
	
	private Integer memo;
	
	private Double term;
	
	private Integer termId;
	
	private String dueDate;
	
	private Double exrate;
	
	private String brokerName;
	
	private Double shipCharges;
	
	private String country;
	
	private String city;
	
	private String accType;
	
	private String lab;
	
	/**
	 * @return the lab
	 */
	public String getLab() {
		return lab;
	}
	/**
	 * @param lab the lab to set
	 */
	public void setLab(String lab) {
		this.lab = lab;
	}
	public OrderMasterData() {
		super();
		this.totalAmount = 0d;
		this.expences = 0d;
		this.discount = 0d;
		this.tax = 0d;
		this.finalAmount = 0d;
	}
	/**
	 * @return the packetList
	 */
	public List<PacketDetails> getPacketList() {
		return packetList;
	}
	/**
	 * @param packetList the packetList to set
	 */
	public void setPacketList(List<PacketDetails> packetList) {
		this.packetList = packetList;
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
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * @return the contactPerson
	 */
	public String getContactPerson() {
		return contactPerson;
	}
	/**
	 * @param contactPerson the contactPerson to set
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
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
	 * @return the approvedBy
	 */
	public Integer getApprovedBy() {
		return approvedBy;
	}
	/**
	 * @param approvedBy the approvedBy to set
	 */
	public void setApprovedBy(Integer approvedBy) {
		this.approvedBy = approvedBy;
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
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to setaccType
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the companyNameName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyNameName the companyNameName to set
	 */
	public void setCompanyName(String companyNameName) {
		this.companyName = companyNameName;
	}
	/**
	 * @return the totalAmount
	 */
	public Double getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * @return the expences
	 */
	public Double getExpences() {
		return expences;
	}
	/**
	 * @param expences the expences to set
	 */
	public void setExpences(Double expences) {
		this.expences = expences;
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
	 * @return the finalAmount
	 */
	public Double getFinalAmount() {
		return finalAmount;
	}
	/**
	 * @param finalAmount the finalAmount to set
	 */
	public void setFinalAmount(Double finalAmount) {
		this.finalAmount = finalAmount;
	}
	/**
	 * @return the dgc
	 */
	public Integer getDgc() {
		return dgc;
	}
	/**
	 * @param dgc the dgc to set
	 */
	public void setDgc(Integer dgc) {
		this.dgc = dgc;
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
	/**
	 * @return the brokerId
	 */
	public Integer getBrokerId() {
		return brokerId;
	}
	/**
	 * @param brokerId the brokerId to set
	 */
	public void setBrokerId(Integer brokerId) {
		this.brokerId = brokerId;
	}
	/**
	 * @return the brokrage
	 */
	public Double getBrokrage() {
		return brokrage;
	}
	/**
	 * @param brokrage the brokrage to set
	 */
	public void setBrokrage(Double brokrage) {
		this.brokrage = brokrage;
	}
	/**
	 * @return the subBrokerId
	 */
	public Integer getSubBrokerId() {
		return subBrokerId;
	}
	/**
	 * @param subBrokerId the subBrokerId to set
	 */
	public void setSubBrokerId(Integer subBrokerId) {
		this.subBrokerId = subBrokerId;
	}
	/**
	 * @return the subBrokrage
	 */
	public Double getSubBrokrage() {
		return subBrokrage;
	}
	/**
	 * @param subBrokrage the subBrokrage to set
	 */
	public void setSubBrokrage(Double subBrokrage) {
		this.subBrokrage = subBrokrage;
	}
	/**
	 * @return the refPartyId
	 */
	public Integer getRefPartyId() {
		return refPartyId;
	}
	/**
	 * @param refPartyId the refPartyId to set
	 */
	public void setRefPartyId(Integer refPartyId) {
		this.refPartyId = refPartyId;
	}
	/**
	 * @return the memo
	 */
	public Integer getMemo() {
		return memo;
	}
	/**
	 * @param memo the memo to set
	 */
	public void setMemo(Integer memo) {
		this.memo = memo;
	}
	/**
	 * @return the term
	 */
	public Double getTerm() {
		return term;
	}
	/**
	 * @param term the term to set
	 */
	public void setTerm(Double term) {
		this.term = term;
	}
	/**
	 * @return the termId
	 */
	public Integer getTermId() {
		return termId;
	}
	/**
	 * @param termId the termId to set
	 */
	public void setTermId(Integer termId) {
		this.termId = termId;
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
	 * @return the exrate
	 */
	public Double getExrate() {
		return exrate;
	}
	/**
	 * @param exrate the exrate to set
	 */
	public void setExrate(Double exrate) {
		this.exrate = exrate;
	}
	/**
	 * @return the brokerName
	 */
	public String getBrokerName() {
		return brokerName;
	}
	/**
	 * @param brokerName the brokerName to set
	 */
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}
	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * @return the shipCharges
	 */
	public Double getShipCharges() {
		return shipCharges;
	}
	/**
	 * @param shipCharges the shipCharges to set
	 */
	public void setShipCharges(Double shipCharges) {
		this.shipCharges = shipCharges;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the accType
	 */
	public String getAccType() {
		return accType;
	}
	/**
	 * @param accType the accType to set
	 */
	public void setAccType(String accType) {
		this.accType = accType;
	}
}
