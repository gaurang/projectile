package com.basync.b2b.dataobjects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tb_termmaster")
public class TermMaster {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id; 
	
	@Column(name="termName")
	private String termName ; 
	
	@Column(name="termCode")
	private String termCode;
	
	
	@Column(name="comm1")
	private Double comm1;
	
	@Column(name="comm2")
	private Double comm2;
	
	@Column(name="comm3")
	private Double comm3;
	
	@Column(name="ByrComm")
	private Double byrComm;
	
	@Column(name="BrokComm1")
	private Double brokComm1;
	
	@Column(name="BrokComm2")
	private Double brokComm2;
	
	@Column(name="Factor")
	private Double factor;
	
	@Column(name="CreditDays")
	private Double creditDays;
	
	@Column(name="interestDay")
	private Double interestDay;
	
	@Column(name="interest")
	private Double interest;
	
	
	@Column(name="brokerComm3")
	private Double brokerComm3;
	
	@Column(name="TermDesc")
	private String termDesc;
	
	@Column(name="rap")
	private Double rap;
	
	@Column(name="sort")
	private Double sort;	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	public String getTermCode() {
		return termCode;
	}
	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}
	public Double getComm1() {
		return comm1;
	}
	public void setComm1(Double comm1) {
		this.comm1 = comm1;
	}

	/**
	 * @return the byrComm
	 */
	public Double getByrComm() {
		return byrComm;
	}
	/**
	 * @param byrComm the byrComm to set
	 */
	public void setByrComm(Double byrComm) {
		this.byrComm = byrComm;
	}
	/**
	 * @return the brokComm1
	 */
	public Double getBrokComm1() {
		return brokComm1;
	}
	/**
	 * @param brokComm1 the brokComm1 to set
	 */
	public void setBrokComm1(Double brokComm1) {
		this.brokComm1 = brokComm1;
	}
	/**
	 * @return the brokComm2
	 */
	public Double getBrokComm2() {
		return brokComm2;
	}
	/**
	 * @param brokComm2 the brokComm2 to set
	 */
	public void setBrokComm2(Double brokComm2) {
		this.brokComm2 = brokComm2;
	}
	/**
	 * @return the factor
	 */
	public Double getFactor() {
		return factor;
	}
	/**
	 * @param factor the factor to set
	 */
	public void setFactor(Double factor) {
		this.factor = factor;
	}
	/**
	 * @return the creditDays
	 */
	public Double getCreditDays() {
		return creditDays;
	}
	/**
	 * @param creditDays the creditDays to set
	 */
	public void setCreditDays(Double creditDays) {
		this.creditDays = creditDays;
	}
	/**
	 * @return the intrestDays
	 */
	public Double getInterestDay() {
		return interestDay;
	}
	/**
	 * @param intrestDays the intrestDays to set
	 */
	public void setInterestDay(Double interestDay) {
		this.interestDay = interestDay;
	}
	/**
	 * @return the brokerComm3
	 */
	public Double getBrokerComm3() {
		return brokerComm3;
	}
	/**
	 * @param brokerComm3 the brokerComm3 to set
	 */
	public void setBrokerComm3(Double brokerComm3) {
		this.brokerComm3 = brokerComm3;
	}
	/**
	 * @return the termDesc
	 */
	public String getTermDesc() {
		return termDesc;
	}
	/**
	 * @param termDesc the termDesc to set
	 */
	public void setTermDesc(String termDesc) {
		this.termDesc = termDesc;
	}
	/**
	 * @return the comm2
	 */
	public Double getComm2() {
		return comm2;
	}
	/**
	 * @param comm2 the comm2 to set
	 */
	public void setComm2(Double comm2) {
		this.comm2 = comm2;
	}
	/**
	 * @return the comm3
	 */
	public Double getComm3() {
		return comm3;
	}
	/**
	 * @param comm3 the comm3 to set
	 */
	public void setComm3(Double comm3) {
		this.comm3 = comm3;
	}
	/**
	 * @return the interest
	 */
	public Double getInterest() {
		return interest;
	}
	/**
	 * @param interest the interest to set
	 */
	public void setInterest(Double interest) {
		this.interest = interest;
	}
	/**
	 * @return the rap
	 */
	public Double getRap() {
		return rap;
	}
	/**
	 * @param rap the rap to set
	 */
	public void setRap(Double rap) {
		this.rap = rap;
	}
	/**
	 * @return the sort
	 */
	public Double getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(Double sort) {
		this.sort = sort;
	}
	
	

}
