package com.basync.b2b.crm.data;

import java.math.BigDecimal;

public class GlRepData {
	

	private String type; //trans type
    private int ref; //trans no.
    private String date;
    private String dueDate;
    private String companyName;
    private double debit;
    private double credit;
    private double balance;
    private Integer glAccNo;
    //kri
   private String bankAccountName;
   private String angadiaCoName; 
   
   private String desc; 
   private int status;
   private BigDecimal openingBal;
   private BigDecimal closingBal;
   
	/**
 * @return the status
 */
public int getStatus() {
	return status;
}

/**
 * @param status the status to set
 */
public void setStatus(int status) {
	this.status = status;
}

	public String getAngadiaCoName() {
	return angadiaCoName;
}

public void setAngadiaCoName(String angadiaCoName) {
	this.angadiaCoName = angadiaCoName;
}

	public String getBankAccountName() {
	return bankAccountName;
}

public void setBankAccountName(String bankAccountName) {
	this.bankAccountName = bankAccountName;
}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public int getRef() {
		return ref;
	}
	
	public void setRef(int ref) {
		this.ref = ref;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public double getDebit() {
		return debit;
	}
	
	public void setDebit(double debit) {
		this.debit = debit;
	}
	
	public double getCredit() {
		return credit;
	}
	
	public void setCredit(double credit) {
		this.credit = credit;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
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
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the glAccNo
	 */
	public Integer getGlAccNo() {
		return glAccNo;
	}

	/**
	 * @param glAccNo the glAccNo to set
	 */
	public void setGlAccNo(Integer glAccNo) {
		this.glAccNo = glAccNo;
	}

	/**
	 * @return the openingBal
	 */
	public BigDecimal getOpeningBal() {
		return openingBal;
	}

	/**
	 * @param openingBal the openingBal to set
	 */
	public void setOpeningBal(BigDecimal openingBal) {
		this.openingBal = openingBal;
	}

	/**
	 * @return the closingBal
	 */
	public BigDecimal getClosingBal() {
		return closingBal;
	}

	/**
	 * @param closingBal the closingBal to set
	 */
	public void setClosingBal(BigDecimal closingBal) {
		this.closingBal = closingBal;
	}


}