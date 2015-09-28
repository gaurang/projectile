package com.basync.b2b.crm.data;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name="tb_partyMaster")
public class PartyMasterData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="companyName")
	private String companyName;
	
	@Column(name="typeOfParty")
	private String typeOfParty ;
	
	@Column(name="brokerPartyId")
	private Integer brokerPartyId;
	
	@Transient
	private String brokerPartyName;
	
	@Column(name="subBrokerPartyId")
	private Integer subBrokerPartyId;
	
	@Column(name="phoneNo1")
	private Long phoneNo1;
	
	@Column(name="phoneNo2")
	private Long phoneNo2;
	
	@Column(name="fax")
	private Long fax;
	
	@Column(name="email")
	private String email;
	
	@Column(name="companyType")
	private String companyType;
	
	@Column(name="subBroker2PartyId")
	private Long subBroker2PartyId;

	@Column(name="businessType")
	private String businessType;
	
	@Column(name="comments")
	private String comments;
	
	@Column(name="activeFlag")
	private Integer activeFlag;
	
	@Column(name="createdBy")
	private Integer createdBy; 
	
	@Column(name="createdOn")
	private Timestamp createdOn; 
	
	@Column(name="modifiedBy")
	private Integer modifiedBy ;
	
	@Column(name="modifiedOn")
	private Timestamp modifiedOn; 

	@Column(name="membersOfAccociation")
	private String membersOfAccociation;
	
	@Column(name="tradeRefComp1")
	private String tradeRefComp1;
	
	@Column(name="tradeRefContact1")
	private String tradeRefContact1;
	
	@Column(name="tradeRefMobile1")
	private Long tradeRefMobile1;
	
	@Column(name="tradeRefPhone1")
	private Long tradeRefPhone1;
	
	@Column(name="tradeRefComp2")
	private String 	tradeRefComp2;
	
	@Column(name="tradeRefContact2")
	private String 	tradeRefContact2;
	
	@Column(name="tradeRefMobile2")
	private Long tradeRefMobile2;
	
	@Column(name="tradeRefPhone2")
	private Long tradeRefPhone2;
	
	@Column(name="designation")
	private String designation;
	
	@Column(name="registrationId")
	private Integer registrationId;

	@Column(name="ownerName")
	private String ownerName;
	
	@Column(name="ownerMobNo")
	private Long ownerMobNo;
	
	@OneToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name="partyId", nullable=false)
	private List<PartyAddMaster> partyAddMasters = new ArrayList<PartyAddMaster>();
	
	 
	/**
	 * @return the id
	 */
	public Integer getId(){
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
	 * @return the typeOfParty
	 */
	public String getTypeOfParty() {
		return typeOfParty;
	}

	/**
	 * @param typeOfParty the typeOfParty to set
	 */
	public void setTypeOfParty(String typeOfParty) {
		this.typeOfParty = typeOfParty;
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
	 * @return the brokerPartyName
	 */
	public String getBrokerPartyName() {
		return brokerPartyName;
	}

	/**
	 * @param brokerPartyName the brokerPartyName to set
	 */
	public void setBrokerPartyName(String brokerPartyName) {
		this.brokerPartyName = brokerPartyName;
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
	 * @return the phoneNo1
	 */
	public Long getPhoneNo1() {
		return phoneNo1;
	}

	/**
	 * @param phoneNo1 the phoneNo1 to set
	 */
	public void setPhoneNo1(Long phoneNo1) {
		this.phoneNo1 = phoneNo1;
	}

	/**
	 * @return the phoneNo2
	 */
	public Long getPhoneNo2() {
		return phoneNo2;
	}

	/**
	 * @param phoneNo2 the phoneNo2 to set
	 */
	public void setPhoneNo2(Long phoneNo2) {
		this.phoneNo2 = phoneNo2;
	}

	/**
	 * @return the fax
	 */
	public Long getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(Long fax) {
		this.fax = fax;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the companyType
	 */
	public String getCompanyType() {
		return companyType;
	}

	/**
	 * @param companyType the companyType to set
	 */
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	/**
	 * @return the subBroker2PartyId
	 */
	public Long getSubBroker2PartyId() {
		return subBroker2PartyId;
	}

	/**
	 * @param subBroker2PartyId the subBroker2PartyId to set
	 */
	public void setSubBroker2PartyId(Long subBroker2PartyId) {
		this.subBroker2PartyId = subBroker2PartyId;
	}

	/**
	 * @return the businessType
	 */
	public String getBusinessType() {
		return businessType;
	}

	/**
	 * @param businessType the businessType to set
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
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
	 * @return the modifiedOn
	 */
	public Timestamp getModifiedOn() {
		return modifiedOn;
	}

	/**
	 * @param modifiedOn the modifiedOn to set
	 */
	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	/**
	 * @return the membersOfAccociation
	 */
	public String getMembersOfAccociation() {
		return membersOfAccociation;
	}

	/**
	 * @param membersOfAccociation the membersOfAccociation to set
	 */
	public void setMembersOfAccociation(String membersOfAccociation) {
		this.membersOfAccociation = membersOfAccociation;
	}

	/**
	 * @return the tradeRefComp1
	 */
	public String getTradeRefComp1() {
		return tradeRefComp1;
	}

	/**
	 * @param tradeRefComp1 the tradeRefComp1 to set
	 */
	public void setTradeRefComp1(String tradeRefComp1) {
		this.tradeRefComp1 = tradeRefComp1;
	}

	/**
	 * @return the tradeRefContact1
	 */
	public String getTradeRefContact1() {
		return tradeRefContact1;
	}

	/**
	 * @param tradeRefContact1 the tradeRefContact1 to set
	 */
	public void setTradeRefContact1(String tradeRefContact1) {
		this.tradeRefContact1 = tradeRefContact1;
	}

	/**
	 * @return the tradeRefMobile1
	 */
	public Long getTradeRefMobile1() {
		return tradeRefMobile1;
	}

	/**
	 * @param tradeRefMobile1 the tradeRefMobile1 to set
	 */
	public void setTradeRefMobile1(Long tradeRefMobile1) {
		this.tradeRefMobile1 = tradeRefMobile1;
	}

	/**
	 * @return the tradeRefPhone1
	 */
	public Long getTradeRefPhone1() {
		return tradeRefPhone1;
	}

	/**
	 * @param tradeRefPhone1 the tradeRefPhone1 to set
	 */
	public void setTradeRefPhone1(Long tradeRefPhone1) {
		this.tradeRefPhone1 = tradeRefPhone1;
	}

	/**
	 * @return the tradeRefComp2
	 */
	public String getTradeRefComp2() {
		return tradeRefComp2;
	}

	/**
	 * @param tradeRefComp2 the tradeRefComp2 to set
	 */
	public void setTradeRefComp2(String tradeRefComp2) {
		this.tradeRefComp2 = tradeRefComp2;
	}

	/**
	 * @return the tradeRefContact2
	 */
	public String getTradeRefContact2() {
		return tradeRefContact2;
	}

	/**
	 * @param tradeRefContact2 the tradeRefContact2 to set
	 */
	public void setTradeRefContact2(String tradeRefContact2) {
		this.tradeRefContact2 = tradeRefContact2;
	}

	/**
	 * @return the tradeRefMobile2
	 */
	public Long getTradeRefMobile2() {
		return tradeRefMobile2;
	}

	/**
	 * @param tradeRefMobile2 the tradeRefMobile2 to set
	 */
	public void setTradeRefMobile2(Long tradeRefMobile2) {
		this.tradeRefMobile2 = tradeRefMobile2;
	}

	/**
	 * @return the tradeRefPhone2
	 */
	public Long getTradeRefPhone2() {
		return tradeRefPhone2;
	}

	/**
	 * @param tradeRefPhone2 the tradeRefPhone2 to set
	 */
	public void setTradeRefPhone2(Long tradeRefPhone2) {
		this.tradeRefPhone2 = tradeRefPhone2;
	}

	/**
	 * @return the partyAddMasters
	 */
	public List<PartyAddMaster> getPartyAddMasters() {
		return partyAddMasters;
	}

	/**
	 * @param partyAddMasters the partyAddMasters to set
	 */
	public void setPartyAddMasters(List<PartyAddMaster> partyAddMasters) {
		this.partyAddMasters = partyAddMasters;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the registrationId
	 */
	public Integer getRegistrationId() {
		return registrationId;
	}

	/**
	 * @param registrationId the registrationId to set
	 */
	public void setRegistrationId(Integer registrationId) {
		this.registrationId = registrationId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Long getOwnerMobNo() {
		return ownerMobNo;
	}

	public void setOwnerMobNo(Long ownerMobNo) {
		this.ownerMobNo = ownerMobNo;
	}
}
