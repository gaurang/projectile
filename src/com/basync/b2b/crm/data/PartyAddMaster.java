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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name="tb_partyAddMaster")
public class PartyAddMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7112469205018548360L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="partyId", insertable = false, updatable = false)
	private Integer partyId;
	
	@Column(name="addType")
	private String addType;
	
	@Column(name="address1")
	private String address1;
	
	@Column(name="address2")
	private String address2;
	
	@Column(name="address3")
	private String address3;
	
	@Column(name="pin")
	private String pin;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="country")
	private String country;
	
	@Column(name="contactPerson")
	private String contactPerson;
	
	@Column(name="cellNo")
	private Long cellNo;
	
	@Column(name="phone")
	private Long phone;
	
	@Column(name="countryPhoneCode")
	private Integer countryPhoneCode;
	
	@Column(name="fax")
	private Long fax;
	
	@Column(name="mainAdd")
	private Integer mainAdd;
	
	@Column(name="createdBy")
	private Integer	createdBy;
	
	@Column(name="createdOn")
	private Timestamp createdOn;
    
	@Column(name="modifiedBY")
	private Integer modifiedBY;
	
	@Column(name="modifiedOn")
	private Timestamp modifiedOn;
	
	@Column(name="activeFlag")
	private Integer	activeFlag = 1;
	
	@Column(name="branchCode")
	private String branchCode;
	

	@Column(name="email")
	private String email;
	
	@Column(name="designation")
	private String designation;
	
	@Column(name="bank")
	private String bank;
	
	@Column(name="bankAccNo")
	private String bankAccNo;
	
	@Column(name="bankAdd")
	private String bankAdd;
	
	@Column(name="bankBranch")
	private String bankBranch;
	
	@ManyToOne
	@JoinColumn(name="partyId", insertable = false, updatable = false)
	private PartyMasterData partyMasterData;
	
	@OneToOne(optional=true,fetch = FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE})
	@PrimaryKeyJoinColumn
	@JoinColumn (name="id", nullable=false)
	private PartyShipAdd partyShipAdd;

	@OneToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name="partyAddId", nullable=false)
	private  List<PartyAccData> partyAccs = new ArrayList<PartyAccData>();
	
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
	 * @return the addType
	 */
	public String getAddType() {
		return addType;
	}
	/**
	 * @param addType the addType to set
	 */
	public void setAddType(String addType) {
		this.addType = addType;
	}
	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}
	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}
	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	/**
	 * @return the address3
	 */
	public String getAddress3() {
		return address3;
	}
	/**
	 * @param address3 the address3 to set
	 */
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	/**
	 * @return the pin
	 */
	public String getPin() {
		return pin;
	}
	/**
	 * @param pin the pin to set
	 */
	public void setPin(String pin) {
		this.pin = pin;
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
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
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
	 * @return the cellNo
	 */
	public Long getCellNo() {
		return cellNo;
	}
	/**
	 * @param cellNo the cellNo to set
	 */
	public void setCellNo(Long cellNo) {
		this.cellNo = cellNo;
	}
	/**
	 * @return the phone
	 */
	public Long getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	/**
	 * @return the countryPhoneCode
	 */
	public Integer getCountryPhoneCode() {
		return countryPhoneCode;
	}
	/**
	 * @param countryPhoneCode the countryPhoneCode to set
	 */
	public void setCountryPhoneCode(Integer countryPhoneCode) {
		this.countryPhoneCode = countryPhoneCode;
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
	 * @return the mainAdd
	 */
	public Integer getMainAdd() {
		return mainAdd;
	}
	/**
	 * @param mainAdd the mainAdd to set
	 */
	public void setMainAdd(Integer mainAdd) {
		this.mainAdd = mainAdd;
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
	 * @return the modifiedBY
	 */
	public Integer getModifiedBY() {
		return modifiedBY;
	}
	/**
	 * @param modifiedBY the modifiedBY to set
	 */
	public void setModifiedBY(Integer modifiedBY) {
		this.modifiedBY = modifiedBY;
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
	 * @return the branchCode
	 */
	public String getBranchCode() {
		return branchCode;
	}
	/**
	 * @param branchCode the branchCode to set
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	/**
	 * @return the partyShipAdd
	 */
	public PartyShipAdd getPartyShipAdd() {
		return partyShipAdd;
	}
	/**
	 * @param partyShipAdd the partyShipAdd to set
	 */
	public void setPartyShipAdd(PartyShipAdd partyShipAdd) {
		this.partyShipAdd = partyShipAdd;
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
	 * @return the bankAdd
	 */
	public String getBankAdd() {
		return bankAdd;
	}
	/**
	 * @param bankAdd the bankAdd to set
	 */
	public void setBankAdd(String bankAdd) {
		this.bankAdd = bankAdd;
	}
	/**
	 * @return the bankBranch
	 */
	public String getBankBranch() {
		return bankBranch;
	}
	/**
	 * @param bankBranch the bankBranch to set
	 */
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	/**
	 * @return the partyMasterData
	 */
	public PartyMasterData getPartyMasterData() {
		return partyMasterData;
	}
	/**
	 * @param partyMasterData the partyMasterData to set
	 */
	public void setPartyMasterData(PartyMasterData partyMasterData) {
		this.partyMasterData = partyMasterData;
	}
	/**
	 * @return the partyAcc
	 */
	public List<PartyAccData> getPartyAccs() {
		return partyAccs;
	}
	/**
	 * @param partyAcc the partyAcc to set
	 */
	public void setPartyAccs(List<PartyAccData> partyAccs) {
		this.partyAccs = partyAccs;
	}


	
}

