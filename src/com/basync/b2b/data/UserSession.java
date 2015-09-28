package com.basync.b2b.data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class UserSession  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;
	
	private String userName;
	
	private String email;
	
	private Integer roleId;
	
	private String compnayName;
	
	private Double term;
	
	private Integer termId;
	
	private Timestamp loginDateTime;
	
	
	private Integer partyAccId;
	
	private String roleName;
	
	private Integer status;
	
	private String password;
	
	private String currency;
	
	private String currencySymbol;
	
	private HashMap<String,String> userActivityMap = new HashMap<String, String>();

	private HashMap<String,String> userUpperActivityMap = new HashMap<String, String>();

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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the compnayName
	 */
	public String getCompnayName() {
		return compnayName;
	}

	/**
	 * @param compnayName the compnayName to set
	 */
	public void setCompnayName(String compnayName) {
		this.compnayName = compnayName;
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
	 * @return the loginDateTime
	 */
	public Timestamp getLoginDateTime() {
		return loginDateTime;
	}

	/**
	 * @param loginDateTime the loginDateTime to set
	 */
	public void setLoginDateTime(Timestamp loginDateTime) {
		this.loginDateTime = loginDateTime;
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
	 * @return the userActivityMap
	 */
	public HashMap<String, String> getUserActivityMap() {
		return userActivityMap;
	}

	/**
	 * @param userActivityMap the userActivityMap to set
	 */
	public void setUserActivityMap(HashMap<String, String> userActivityMap) {
		this.userActivityMap = userActivityMap;
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
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the currencySymbol
	 */
	public String getCurrencySymbol() {
		return currencySymbol;
	}

	/**
	 * @param currencySymbol the currencySymbol to set
	 */
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
	
	/**
	 * 
	 * @return the userUpperActivityMap
	 */
	public HashMap<String, String> getUserUpperActivityMap() {
		return userUpperActivityMap;
	}

	/**
	 * 
	 * @param userUpperActivityMap
	 */
	public void setUserUpperActivityMap(HashMap<String, String> userUpperActivityMap) {
		this.userUpperActivityMap = userUpperActivityMap;
	}
	
	
}
