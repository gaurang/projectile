package com.basync.b2b.crm.data;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name="tb_partyShipAdd")
public class PartyShipAdd implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7614474191752299360L;
	
	@Id
	@GeneratedValue(generator="foreign")
	  @GenericGenerator(name="foreign", strategy = "foreign", parameters={
			    @Parameter(name="property", value="partyAddMaster")
	  })
	@Column (name="partyAddId")
	private Integer	partyAddId;
	
	@Column(name="addType")
	private String addType = "SHP";
	
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
	
	
	@Column(name="createdBy")
	private Integer	createdBy;
	
	@Column(name="createdOn")
	private Timestamp createdOn;
	
	@Column(name="modifiedBy")
    private Integer modifiedBy;
	
	@Column(name="modifiedOn")
	private Timestamp modifiedOn;
	
	@Column(name="activeFlag")
	private Integer	activeFlag;
	
	@OneToOne(optional=false)  
	@JoinColumn (name="partyAddId", insertable = false, updatable = false)
	private PartyAddMaster partyAddMaster;
	 
	
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
		return modifiedBy;
	}
	/**
	 * @param modifiedBY the modifiedBY to set
	 */
	public void setModifiedBY(Integer modifiedBy) {
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
	
	

}
