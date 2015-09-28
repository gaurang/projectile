package com.basync.b2b.dataobjects;

public class LoginDetailsDO extends BaseDO{
	private String loginName;
	private String password;
	private String confirmPassword;
	private String emailUpdates;
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getEmailUpdates() {
		return emailUpdates;
	}
	public void setEmailUpdates(String emailUpdates) {
		this.emailUpdates = emailUpdates;
	}

}
