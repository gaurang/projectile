package com.basync.b2b.dataobjects;


public class RegistrationViewDO extends BaseDO{

	private Long registrationSeq;
	public Long getRegistrationSeq() {
		return registrationSeq;
	}
	public void setRegistrationSeq(Long registrationSeq) {
		this.registrationSeq = registrationSeq;
	}
	private String loginName;
	private String companyName;
	private String brokerName;
	private String businessType;
	private String cEmail;
	private String cName;
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getCEmail() {
		return cEmail;
	}
	public void setCEmail(String email) {
		cEmail = email;
	}
	public String getCName() {
		return cName;
	}
	public void setCName(String name) {
		cName = name;
	}
	public String getCMobile() {
		return cMobile;
	}
	public void setCMobile(String mobile) {
		cMobile = mobile;
	}
	private String cMobile;
	
	
}
