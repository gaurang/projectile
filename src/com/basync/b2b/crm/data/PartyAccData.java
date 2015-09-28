package com.basync.b2b.crm.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="tb_partyAcc")
public class PartyAccData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	
	@Transient
	private String companyName;
	
	@Transient
	private Integer partyId;
	
	@Column(name="partyAddId", insertable = false, updatable = false)
	private Integer partyAddId;
	
	@Transient
	private Integer brokerPartyId;
	
	@Transient
	private Integer subBrokerPartyId;
	
	@Transient
	private Integer subBroker2PartyId;
	
	@Column(name="termId")
	private Integer termId;
	
	@Transient
	private String termCode;
	
	@Transient
	private Double termFactor;
	
	@Column(name="accType")
	private String accType;
	
	@Transient
	private String partyType;
	
	@Transient
	private String branchCode;

	@Column(name="activeFlag")
	private Integer activeFlag =1;
	
	@Column(name="brokrage")
	private Integer brokrage;

	@Column(name="currency")
	private String currency;
	
	@Column(name="userId")
	private Integer userId;
	
	@ManyToOne
	@JoinColumn(name="partyAddId", insertable = false, updatable = false)
	private PartyAddMaster partyAddMaster;
	
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
	 * @return the brokerPartyId
	 */
	public Integer getBrokerPartyId() {
		return brokerPartyId;
	}

	/**
	 * @param brokerPartyId the brokerPartyId to set
	 */
	public void setBrokerPartyId(Integer brokerPartyId) {
		this.brokerPartyId = brokerPartyId;
	}

	
	
	/**
	 * @return the subBrokerPartyId
	 */
	public Integer getSubBrokerPartyId() {
		return subBrokerPartyId;
	}

	/**
	 * @param subBrokerPartyId the subBrokerPartyId to set
	 */
	public void setSubBrokerPartyId(Integer subBrokerPartyId) {
		this.subBrokerPartyId = subBrokerPartyId;
	}

	/**
	 * @return the subBroker2PartyId
	 */
	public Integer getSubBroker2PartyId() {
		return subBroker2PartyId;
	}

	/**
	 * @param subBroker2PartyId the subBroker2PartyId to set
	 */
	public void setSubBroker2PartyId(Integer subBroker2PartyId) {
		this.subBroker2PartyId = subBroker2PartyId;
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
	 * @return the termCode
	 */
	public String getTermCode() {
		return termCode;
	}

	/**
	 * @param termCode the termCode to set
	 */
	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}

	/**
	 * @return the termFactor
	 */
	public Double getTermFactor() {
		return termFactor;
	}

	/**
	 * @param termFactor the termFactor to set
	 */
	public void setTermFactor(Double termFactor) {
		this.termFactor = termFactor;
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

	/**
	 * @return the partyType
	 */
	public String getPartyType() {
		return partyType;
	}

	/**
	 * @param partyType the partyType to set
	 */
	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}

	/**
	 * @return the partyId
	 */
	public Integer getPartyId() {
		return partyId;
	}

	/**
	 * @param partyId the partyId to set
	 */
	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}

	/**
	 * @return the partyAddId
	 */
	public Integer getPartyAddId() {
		return partyAddId;
	}

	/**
	 * @param partyAddId the partyAddId to set
	 */
	public void setPartyAddId(Integer partyAddId) {
		this.partyAddId = partyAddId;
	}
	/**
	 * @return the brachCode
	 */
	public String getBranchCode() {
		return branchCode;
	}

	/**
	 * @param brachCode the brachCode to set
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	/**
	 * @return the activeFlag
	 */
	public Integer getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag the activeFlag to set
	 */
	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * @return the brokrage
	 */
	public Integer getBrokrage() {
		return brokrage;
	}

	/**
	 * @param brokrage the brokrage to set
	 */
	public void setBrokrage(Integer brokrage) {
		this.brokrage = brokrage;
	}

	/**
	 * @return the partyAddMaster
	 */
	public PartyAddMaster getPartyAddMaster() {
		return partyAddMaster;
	}

	/**
	 * @param partyAddMaster the partyAddMaster to set
	 */
	public void setPartyAddMaster(PartyAddMaster partyAddMaster) {
		this.partyAddMaster = partyAddMaster;
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
	 * 
	 * @return userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 
	 * @param userId Set User Id.
	 */

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
}
