//krishna 20/21/12 for stock checking details 
package com.basync.b2b.data;
import java.util.List;
public class StockCheckPktDetals {
	    private Integer pktId;
		private String pktCode;
		private Double baseRate;//base rate from odt table//
		private Double rate;//
		private Integer pcs;
		private Double cts;
		private Double value;
		private Double rap;//
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
		
		private Double totalRate;//totalRate(changed by krishna 20/1/12
		
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
		private Double rapPrice;//changed by krishna rapPrice to srapPrice from stock table
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
		//changed by krishna for stockchecking report and add srap;stocktotalRate;odtbaseRate;srapPrice; 20/1
		private double srap;
		//private double srapPrice;
		private double stocktotalRate;
		private double odtbaseRate;
		
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
		public Integer getPcs() {
			return pcs;
		}
		/**
		 * @param pcs the pcs to set
		 */
		public void setPcs(Integer pcs) {
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
		public double getSrap() {
			return srap;
		}
		public void setSrap(double srap) {
			this.srap = srap;
		}
		public double getStocktotalRate() {
			return stocktotalRate;
		}
		public void setStocktotalRate(double stocktotalRate) {
			this.stocktotalRate = stocktotalRate;
		}
		public double getOdtbaseRate() {
			return odtbaseRate;
		}
		public void setOdtbaseRate(double odtbaseRate) {
			this.odtbaseRate = odtbaseRate;
		}
		
		public Double getTotalRate() {
			return totalRate;
		}
		public void setTotalRate(Double totalRate) {
			this.totalRate = totalRate;
		}
		public Double getRapPrice() {
			return rapPrice;
		}
		public void setRapPrice(Double rapPrice) {
			this.rapPrice = rapPrice;
		}
		

	}



