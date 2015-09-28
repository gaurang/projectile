package com.basync.b2b.data;

import java.util.List;

import javax.persistence.Column;

public class PacketDetails {	
	private Integer pktId;
	private String pktCode;
	private Double baseRate;
	private Double rate;
	private Double pcs;
	private Double cts;
	private Double value;
	private Double rap;
	private String lab;
		
	private String certId;
	private Integer status;
	private String statusCode;
	
	private String fnc;
	private String fnci;
	private String fnco;
	
	private String remark;
	private String comments;
	
	
	private Double sellRate;
	
	private Double totalRate;
	
	List<String> prpValue;
	
	//order Details
	private Integer orderId;
	private String orderDate;
	private Integer pktStatus;
	
	private String sh;
	private String pu;
	private String c;

	
	private String companyName;
	private String brokerName;
	private Double brokerage;
	private String term;
	private String rootPkt;
	private String date;
	
	private Double exrate;
	private Double rapPrice;
	private Integer grpId;
	private Integer rapnetFlag;
	private Integer webSiteFlag;
	private String issueDate; 
	
	private Double sh_so;
	private Double pu_so;
	private Double c_so;
	
	private Long partyAccId;
	private String accType;
	private String returnDate;
	private Double addDisc;
	private Double rejCts;
	private Double finalCts;
	
	//for pending stcck by kris
	private String ct;
	private String po;
	private String flc;
	private String fls;
	private String t;
	private String dp;
	private String sy;
	private String md;
	private String xd;
	private String d;
	private double askingPriceDisc;
	private String sd;
	private Double memoRate;
	private Integer days;
	  private String shFr    ;
	  private String shTo    ;
	  private String puFr    ;
	  private String puTo    ;
	  private String cFr    ;
	  private String cTo    ;
	  private String pTyp    ;
	  private String parcelNum    ;
	  private String sieve    ;
	  private Double avgCts    ;
	 
	  private Double lab_so;
	  
	  private String reportComm;
	  private String gd;
	  private String lh;
	  private String ca;
	  private String pa;
	  private String pd;
	  private String td;
	  private String cu;
	  private String sl;
	  private String gd_per;
	  private String cc;
	  private String ch;
	  private String li;
	  
	  private String comment1;
	  private String comment2;
	  private String comment3;
	  
	  
	public String getSd() {
		return sd;
	}
	public void setSd(String sd) {
		this.sd = sd;
	}
	
	public double getAskingPriceDisc() {
		return askingPriceDisc;
	}
	public void setAskingPriceDisc(double askingPriceDisc) {
		this.askingPriceDisc = askingPriceDisc;
	}
	public String getMd() {
		return md;
	}
	public void setMd(String md) {
		this.md = md;
	}
	public String getXd() {
		return xd;
	}
	public void setXd(String xd) {
		this.xd = xd;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public String getFlc() {
		return flc;
	}
	public void setFlc(String flc) {
		this.flc = flc;
	}
	public String getFls() {
		return fls;
	}
	public void setFls(String fls) {
		this.fls = fls;
	}
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
	}
	public String getDp() {
		return dp;
	}
	public void setDp(String dp) {
		this.dp = dp;
	}
	public String getSy() {
		return sy;
	}
	public void setSy(String sy) {
		this.sy = sy;
	}
	
	public String getPo() {
		return po;
	}
	public void setPo(String po) {
		this.po = po;
	}
	/**
	 * @return the pktId
	 */
	public Integer getPktId() {
		return pktId;
	}
	/**
	 * @param pktId the pktId to set
	 */
	public void setPktId(Integer pktId) {
		this.pktId = pktId;
	}
	/**
	 * @return the pktCode
	 */
	public String getPktCode() {
		return pktCode;
	}
	/**
	 * @param pktCode the pktCode to set
	 */
	public void setPktCode(String pktCode) {
		this.pktCode = pktCode;
	}
	/**
	 * @return the baseRate
	 */
	public Double getBaseRate() {
		return baseRate;
	}
	/**
	 * @param baseRate the baseRate to set
	 */
	public void setBaseRate(Double baseRate) {
		this.baseRate = baseRate;
	}
	/**
	 * @return the rate
	 */
	public Double getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(Double rate) {
		this.rate = rate;
	}
	/**
	 * @return the pcs
	 */
	public Double getPcs() {
		return pcs;
	}
	/**
	 * @param pcs the pcs to set
	 */
	public void setPcs(Double pcs) {
		this.pcs = pcs;
	}
	/**
	 * @return the cts
	 */
	public Double getCts() {
		return cts;
	}
	/**
	 * @param cts the cts to set
	 */
	public void setCts(Double cts) {
		this.cts = cts;
	}
	/**
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(Double value) {
		this.value = value;
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
	/**
	 * @return the certId
	 */
	public String getCertId() {
		return certId;
	}
	/**
	 * @param certId the certId to set
	 */
	public void setCertId(String certId) {
		this.certId = certId;
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
	 * @return the prpValue
	 */
	public List<String> getPrpValue() {
		return prpValue;
	}
	/**
	 * @param prpValue the prpValue to set
	 */
	public void setPrpValue(List<String> prpValue) {
		this.prpValue = prpValue;
	}
	/**
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	 * @return the pktStatus
	 */
	public Integer getPktStatus() {
		return pktStatus;
	}
	/**
	 * @param pktStatus the pktStatus to set
	 */
	public void setPktStatus(Integer pktStatus) {
		this.pktStatus = pktStatus;
	}
	/**
	 * @return the sh
	 */
	public String getSh() {
		return sh;
	}
	/**
	 * @param sh the sh to set
	 */
	public void setSh(String sh) {
		this.sh = sh;
	}
	/**
	 * @return the pu
	 */
	public String getPu() {
		return pu;
	}
	/**
	 * @param pu the pu to set
	 */
	public void setPu(String pu) {
		this.pu = pu;
	}
	/**
	 * @return the c
	 */
	public String getC() {
		return c;
	}
	/**
	 * @param c the c to set
	 */
	public void setC(String c) {
		this.c = c;
	}
	/**
	 * @return the ct
	 */
	public String getCt() {
		return ct;
	}
	/**
	 * @param ct the ct to set
	 */
	public void setCt(String ct) {
		this.ct = ct;
	}
	/**
	 * @return the totalRate
	 */
	public Double getTotalRate() {
		return totalRate;
	}
	/**
	 * @param totalRate the totalRate to set
	 */
	public void setTotalRate(Double totalRate) {
		this.totalRate = totalRate;
	}
	/**
	 * @return the sellRate
	 */
	public Double getSellRate() {
		return sellRate;
	}
	/**
	 * @param sellRate the sellRate to set
	 */
	public void setSellRate(Double sellRate) {
		this.sellRate = sellRate;
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
	 * @return the fnc
	 */
	public String getFnc() {
		return fnc;
	}
	/**
	 * @param fnc the fnc to set
	 */
	public void setFnc(String fnc) {
		this.fnc = fnc;
	}
	/**
	 * @return the fnci
	 */
	public String getFnci() {
		return fnci;
	}
	/**
	 * @param fnci the fnci to set
	 */
	public void setFnci(String fnci) {
		this.fnci = fnci;
	}
	/**
	 * @return the fnco
	 */
	public String getFnco() {
		return fnco;
	}
	/**
	 * @param fnco the fnco to set
	 */
	public void setFnco(String fnco) {
		this.fnco = fnco;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * @param term the term to set
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	/**
	 * @return the rootPkt
	 */
	public String getRootPkt() {
		return rootPkt;
	}
	/**
	 * @param rootPkt the rootPkt to set
	 */
	public void setRootPkt(String rootPkt) {
		this.rootPkt = rootPkt;
	}
	/**
	 * @return the brokerage
	 */
	public Double getBrokerage() {
		return brokerage;
	}
	/**
	 * @param brokerage the brokerage to set
	 */
	public void setBrokerage(Double brokerage) {
		this.brokerage = brokerage;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
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
	 * @return the rapPrice
	 */
	public Double getRapPrice() {
		return rapPrice;
	}
	/**
	 * @param rapPrice the rapPrice to set
	 */
	public void setRapPrice(Double rapPrice) {
		this.rapPrice = rapPrice;
	}
	/**
	 * @return the grpId
	 */
	public Integer getGrpId() {
		return grpId;
	}
	/**
	 * @param grpId the grpId to set
	 */
	public void setGrpId(Integer grpId) {
		this.grpId = grpId;
	}
	/**
	 * @return the rapnetFlag
	 */
	public Integer getRapnetFlag() {
		return rapnetFlag;
	}
	/**
	 * @param rapnetFlag the rapnetFlag to set
	 */
	public void setRapnetFlag(Integer rapnetFlag) {
		this.rapnetFlag = rapnetFlag;
	}
	/**
	 * @return the webSiteFlag
	 */
	public Integer getWebSiteFlag() {
		return webSiteFlag;
	}
	/**
	 * @param webSiteFlag the webSiteFlag to set
	 */
	public void setWebSiteFlag(Integer webSiteFlag) {
		this.webSiteFlag = webSiteFlag;
	}
	/**
	 * @return the issueDate
	 */
	public String getIssueDate() {
		return issueDate;
	}
	/**
	 * @param issueDate the issueDate to set
	 */
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	/**
	 * @return the sh_so
	 */
	public Double getSh_so() {
		return sh_so;
	}
	/**
	 * @param sh_so the sh_so to set
	 */
	public void setSh_so(Double sh_so) {
		this.sh_so = sh_so;
	}
	/**
	 * @return the pu_so
	 */
	public Double getPu_so() {
		return pu_so;
	}
	/**
	 * @param pu_so the pu_so to set
	 */
	public void setPu_so(Double pu_so) {
		this.pu_so = pu_so;
	}
	/**
	 * @return the c_so
	 */
	public Double getC_so() {
		return c_so;
	}
	/**
	 * @param c_so the c_so to set
	 */
	public void setC_so(Double c_so) {
		this.c_so = c_so;
	}
	/**
	 * @return the partyAccId
	 */
	public Long getPartyAccId() {
		return partyAccId;
	}
	/**
	 * @param partyAccId the partyAccId to set
	 */
	public void setPartyAccId(Long partyAccId) {
		this.partyAccId = partyAccId;
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
	 * @return the returnDate
	 */
	public String getReturnDate() {
		return returnDate;
	}
	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public Double getAddDisc() {
		return addDisc;
	}
	public void setAddDisc(Double addDisc) {
		this.addDisc = addDisc;
	}
	/**
	 * @return the rejCts
	 */
	public Double getRejCts() {
		return rejCts;
	}
	/**
	 * @param rejCts the rejCts to set
	 */
	public void setRejCts(Double rejCts) {
		this.rejCts = rejCts;
	}
	/**
	 * @return the finalCts
	 */
	public Double getFinalCts() {
		return finalCts;
	}
	/**
	 * @param finalCts the finalCts to set
	 */
	public void setFinalCts(Double finalCts) {
		this.finalCts = finalCts;
	}
	/**
	 * @return the shFr
	 */
	public String getShFr() {
		return shFr;
	}
	/**
	 * @param shFr the shFr to set
	 */
	public void setShFr(String shFr) {
		this.shFr = shFr;
	}
	/**
	 * @return the shTo
	 */
	public String getShTo() {
		return shTo;
	}
	/**
	 * @param shTo the shTo to set
	 */
	public void setShTo(String shTo) {
		this.shTo = shTo;
	}
	/**
	 * @return the puFr
	 */
	public String getPuFr() {
		return puFr;
	}
	/**
	 * @param puFr the puFr to set
	 */
	public void setPuFr(String puFr) {
		this.puFr = puFr;
	}
	/**
	 * @return the puTo
	 */
	public String getPuTo() {
		return puTo;
	}
	/**
	 * @param puTo the puTo to set
	 */
	public void setPuTo(String puTo) {
		this.puTo = puTo;
	}
	/**
	 * @return the cFr
	 */
	public String getcFr() {
		return cFr;
	}
	/**
	 * @param cFr the cFr to set
	 */
	public void setcFr(String cFr) {
		this.cFr = cFr;
	}
	/**
	 * @return the cTo
	 */
	public String getcTo() {
		return cTo;
	}
	/**
	 * @param cTo the cTo to set
	 */
	public void setcTo(String cTo) {
		this.cTo = cTo;
	}
	/**
	 * @return the pTyp
	 */
	public String getpTyp() {
		return pTyp;
	}
	/**
	 * @param pTyp the pTyp to set
	 */
	public void setpTyp(String pTyp) {
		this.pTyp = pTyp;
	}
	/**
	 * @return the parcelNum
	 */
	public String getParcelNum() {
		return parcelNum;
	}
	/**
	 * @param parcelNum the parcelNum to set
	 */
	public void setParcelNum(String parcelNum) {
		this.parcelNum = parcelNum;
	}
	/**
	 * @return the sieve
	 */
	public String getSieve() {
		return sieve;
	}
	/**
	 * @param sieve the sieve to set
	 */
	public void setSieve(String sieve) {
		this.sieve = sieve;
	}
	/**
	 * @return the avgCts
	 */
	public Double getAvgCts() {
		return avgCts;
	}
	/**
	 * @param avgCts the avgCts to set
	 */
	public void setAvgCts(Double avgCts) {
		this.avgCts = avgCts;
	}
	/**
	 * @return the lab_so
	 */
	public Double getLab_so() {
		return lab_so;
	}
	/**
	 * @param lab_so the lab_so to set
	 */
	public void setLab_so(Double lab_so) {
		this.lab_so = lab_so;
	}
	/**
	 * @return the memoRate
	 */
	public Double getMemoRate() {
		return memoRate;
	}
	/**
	 * @param memoRate the memoRate to set
	 */
	public void setMemoRate(Double memoRate) {
		this.memoRate = memoRate;
	}
	/**
	 * @return the days
	 */
	public Integer getDays() {
		return days;
	}
	/**
	 * @param days the days to set
	 */
	public void setDays(Integer days) {
		this.days = days;
	}
	/**
	 * @return the reportComm
	 */
	public String getReportComm() {
		return reportComm;
	}
	/**
	 * @param reportComm the reportComm to set
	 */
	public void setReportComm(String reportComm) {
		this.reportComm = reportComm;
	}
	/**
	 * @return the gd
	 */
	public String getGd() {
		return gd;
	}
	/**
	 * @param gd the gd to set
	 */
	public void setGd(String gd) {
		this.gd = gd;
	}
	/**
	 * @return the lh
	 */
	public String getLh() {
		return lh;
	}
	/**
	 * @param lh the lh to set
	 */
	public void setLh(String lh) {
		this.lh = lh;
	}
	/**
	 * @return the ca
	 */
	public String getCa() {
		return ca;
	}
	/**
	 * @param ca the ca to set
	 */
	public void setCa(String ca) {
		this.ca = ca;
	}
	/**
	 * @return the pa
	 */
	public String getPa() {
		return pa;
	}
	/**
	 * @param pa the pa to set
	 */
	public void setPa(String pa) {
		this.pa = pa;
	}
	/**
	 * @return the td
	 */
	public String getTd() {
		return td;
	}
	/**
	 * @param td the td to set
	 */
	public void setTd(String td) {
		this.td = td;
	}
	/**
	 * @return the cu
	 */
	public String getCu() {
		return cu;
	}
	/**
	 * @param cu the cu to set
	 */
	public void setCu(String cu) {
		this.cu = cu;
	}
	/**
	 * @return the st
	 */
	public String getSl() {
		return sl;
	}
	/**
	 * @param st the st to set
	 */
	public void setSl(String sl) {
		this.sl = sl;
	}
	/**
	 * @return the cc
	 */
	public String getCc() {
		return cc;
	}
	/**
	 * @param cc the cc to set
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}
	/**
	 * @return the gd_per
	 */
	public String getGd_per() {
		return gd_per;
	}
	/**
	 * @param gd_per the gd_per to set
	 */
	public void setGd_per(String gd_per) {
		this.gd_per = gd_per;
	}
	/**
	 * @return the ch
	 */
	public String getCh() {
		return ch;
	}
	/**
	 * @param ch the ch to set
	 */
	public void setCh(String ch) {
		this.ch = ch;
	}
	/**
	 * @return the pd
	 */
	public String getPd() {
		return pd;
	}
	/**
	 * @param pd the pd to set
	 */
	public void setPd(String pd) {
		this.pd = pd;
	}
	/**
	 * @return the li
	 */
	public String getLi() {
		return li;
	}
	/**
	 * @param li the li to set
	 */
	public void setLi(String li) {
		this.li = li;
	}
	/**
	 * @return the comment3
	 */
	public String getComment3() {
		return comment3;
	}
	/**
	 * @param comment3 the comment3 to set
	 */
	public void setComment3(String comment3) {
		this.comment3 = comment3;
	}
	/**
	 * @return the comment1
	 */
	public String getComment1() {
		return comment1;
	}
	/**
	 * @param comment1 the comment1 to set
	 */
	public void setComment1(String comment1) {
		this.comment1 = comment1;
	}
	/**
	 * @return the comment2
	 */
	public String getComment2() {
		return comment2;
	}
	/**
	 * @param comment2 the comment2 to set
	 */
	public void setComment2(String comment2) {
		this.comment2 = comment2;
	}

	
}
