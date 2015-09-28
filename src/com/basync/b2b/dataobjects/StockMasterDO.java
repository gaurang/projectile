package com.basync.b2b.dataobjects;

import java.io.Serializable;
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
@Table(name="tb_stockmaster", catalog = "hridhesh")
public class StockMasterDO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6257421995289918268L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long ID;
	
	@Column(name="pktCode")
	private String pktCode;

	@Transient
	private String editPktCode;
	
	@Column(name="pcs")
	private Double pcs; 
	@Column(name="rate")
	private Double rate; 
	@Column(name="baseRate")
	private Double baseRate; 
	@Column(name="issueDate")
	private String issueDate ;
	@Column(name="status")  
	private Long status  ;
	@Column(name="totalCts")
	private Double totalCts;
	@Column(name="rapPrice")
	private Double rapPrice ;
	@Column(name="rap")
	private Double rap ;
	@Column(name="certId")  
	private String certId ;
	@Column(name="companyName")  
	private String companyName ;
	@Column(name="ownerCode")  
	private String ownerCode ;
	@Column(name="certURL")
	private String certURL ;
	@Column(name="updateDate")
	private String updateDate ;
	@Column(name="treatment")  
	private String treatment ;
	@Column(name="cashPrice")
	private String cashPrice ;
	@Column(name="cashDiscount")
	private String cashDiscount ;
	@Column(name="comment")
	private String comment ;
	@Column(name="country")
	private String country ;
	@Column(name="state")
	private String state ;
	@Column(name="city")
	  private String city ;
	@Column(name="isMatchedSep")
	  private String isMatchedSep ;
	@Column(name="isMatched")
	  private String isMatched ;
	@Column(name="pairStock")
	  private String pairStock ;
	@Column(name="allowRapFeed")
	  private String allowRapFeed ;
	@Column(name="parcelNum")
	  private String parcelNum ;
	@Column(name="diamondImage")
	  
	  private String diamondImage ;
	@Column(name="d3Image")
	  private String d3Image ;
	@Column(name="tradeShow")
	  private String tradeShow;
	@Column(name="vendorId")
	  private Integer vendorId ;
	@Column(name="rootPkt")
	  private String rootPkt;
	@Column(name="remark")
	  private String remark;
	
	@Column(name="partyAccId")
	  private Integer partyAccId;
	
	@Column(name="userId")
	private Integer userId;
	@Column(name="totalPcs")
	private Double totalPcs;
	@Column(name="value")
	private Double value;
	
	@Column(name="pairNo")
	private Double pairNo;
	
	
	@Column(name="pktCode2")
	private String pktCode2;
	
	@Column(name="rapCode")
	private String rapCode;
	
	@Column(name="vendorStockCode")
	private String vendorStockCode;
	
	@Column(name="availabilty")
	private String availabilty;
	
	@Column(name="sarinFile")
	private String sarinFile;
	
	@Column(name="gemFile")
	private String gemFile;
	
	@Column(name="clientRow")
	private String clientRow;
	
	@Column(name="rapnetFlag")
	private String rapnetFlag;

	@Column(name="websiteFlag")
	private String websiteFlag;

	  //only for log use
	  @Column(name="lastUpdateDate")
	  private String lastUpdateDate ;	
	  
	  @Column(name="updateBy")
	  private Integer updateBy ;	
	
	  
    @Column(name="askingPriceDisc")
	private Double askingPriceDisc; 
    
    @Column(name="issueCts")
	private Double issueCts; 

    
	@OneToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn (name="id", nullable=false)
	private List<StockPRPDO> stockPRPDOs;
    
	  
	public StockMasterDO() {
		super();
		this.pcs = 1d;
		status = 0L;
	}
	public Long getID() {
		return ID;
	}
	public void setID(Long id) {
		ID = id;
	}
	public String getPktCode() {
		return pktCode;
	}
	public void setPktCode(String pktCode) {
		this.pktCode = pktCode;
	}
	public Double getPcs() {
		return pcs;
	}
	public void setPcs(Double pcs) {
		this.pcs = pcs;
	}
	public Double getRate() {
		if(rate == null  )
			return 0d;
		else
			return rate;
	}
	public void setRate(Double rate) {
		
			this.rate = rate;
	}
	public Double getBaseRate() {
		return baseRate;
	}
	public void setBaseRate(Double baseRate) {
		this.baseRate = baseRate;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public Double getRapPrice() {
		return rapPrice;
	}
	public void setRapPrice(Double rapPrice) {
		this.rapPrice = rapPrice;
	}
	public Double getRap() {
		return rap;
	}
	public void setRap(Double rap) {
		this.rap = rap;
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
	 * @return the ownerCode
	 */
	public String getOwnerCode() {
		return ownerCode;
	}
	/**
	 * @param ownerCode the ownerCode to set
	 */
	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}
	/**
	 * @return the certURL
	 */
	public String getCertURL() {
		return certURL;
	}
	/**
	 * @param certURL the certURL to set
	 */
	public void setCertURL(String certURL) {
		this.certURL = certURL;
	}
	/**
	 * @return the updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return the treatment
	 */
	public String getTreatment() {
		return treatment;
	}
	/**
	 * @param treatment the treatment to set
	 */
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	/**
	 * @return the cashPrice
	 */
	public String getCashPrice() {
		return cashPrice;
	}
	/**
	 * @param cashPrice the cashPrice to set
	 */
	public void setCashPrice(String cashPrice) {
		this.cashPrice = cashPrice;
	}
	/**
	 * @return the cashDiscount
	 */
	public String getCashDiscount() {
		return cashDiscount;
	}
	/**
	 * @param cashDiscount the cashDiscount to set
	 */
	public void setCashDiscount(String cashDiscount) {
		this.cashDiscount = cashDiscount;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
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
	 * @return the isMatchedSep
	 */
	public String getIsMatchedSep() {
		return isMatchedSep;
	}
	/**
	 * @param isMatchedSep the isMatchedSep to set
	 */
	public void setIsMatchedSep(String isMatchedSep) {
		this.isMatchedSep = isMatchedSep;
	}
	/**
	 * @return the isMatched
	 */
	public String getIsMatched() {
		return isMatched;
	}
	/**
	 * @param isMatched the isMatched to set
	 */
	public void setIsMatched(String isMatched) {
		this.isMatched = isMatched;
	}
	/**
	 * @return the pairStock
	 */
	public String getPairStock() {
		return pairStock;
	}
	/**
	 * @param pairStock the pairStock to set
	 */
	public void setPairStock(String pairStock) {
		this.pairStock = pairStock;
	}
	/**
	 * @return the allowRapFeed
	 */
	public String getAllowRapFeed() {
		return allowRapFeed;
	}
	/**
	 * @param allowRapFeed the allowRapFeed to set
	 */
	public void setAllowRapFeed(String allowRapFeed) {
		this.allowRapFeed = allowRapFeed;
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
	 * @return the diamondImage
	 */
	public String getDiamondImage() {
		return diamondImage;
	}
	/**
	 * @param diamondImage the diamondImage to set
	 */
	public void setDiamondImage(String diamondImage) {
		this.diamondImage = diamondImage;
	}
	/**
	 * @return the d3Image
	 */
	public String getD3Image() {
		return d3Image;
	}
	/**
	 * @param d3Image the d3Image to set
	 */
	public void setD3Image(String d3Image) {
		this.d3Image = d3Image;
	}
	/**
	 * @return the tradeShow
	 */
	public String getTradeShow() {
		return tradeShow;
	}
	/**
	 * @param tradeShow the tradeShow to set
	 */
	public void setTradeShow(String tradeShow) {
		this.tradeShow = tradeShow;
	}
	/**
	 * @return the vendorId
	 */
	public Integer getVendorId() {
		return vendorId;
	}
	/**
	 * @param vendorId the vendorId to set
	 */
	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
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
	 * @return the stockPRPDOs
	 */
	public List<StockPRPDO> getStockPRPDOs() {
		return stockPRPDOs;
	}
	/**
	 * @param stockPRPDOs the stockPRPDOs to set
	 */
	public void setStockPRPDOs(List<StockPRPDO> stockPRPDOs) {
		this.stockPRPDOs = stockPRPDOs;
	}
	/**
	 * @return the totalCts
	 */
	public Double getTotalCts() {
		return totalCts;
	}
	/**
	 * @param totalCts the totalCts to set
	 */
	public void setTotalCts(Double totalCts) {
		this.totalCts = totalCts;
	}
	/**
	 * @return the totalPcs
	 */
	public Double getTotalPcs() {
		return totalPcs;
	}
	/**
	 * @param totalPcs the totalPcs to set
	 */
	public void setTotalPcs(Double totalPcs) {
		this.totalPcs = totalPcs;
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
	 * @return the pairNo
	 */
	public Double getPairNo() {
		return pairNo;
	}
	/**
	 * @param pairNo the pairNo to set
	 */
	public void setPairNo(Double pairNo) {
		this.pairNo = pairNo;
	}
	/**
	 * @return the pktCode2
	 */
	public String getPktCode2() {
		return pktCode2;
	}
	/**
	 * @param pktCode2 the pktCode2 to set
	 */
	public void setPktCode2(String pktCode2) {
		this.pktCode2 = pktCode2;
	}
	/**
	 * @return the editPktCode
	 */
	public String getEditPktCode() {
		return editPktCode;
	}
	/**
	 * @param editPktCode the editPktCode to set
	 */
	public void setEditPktCode(String editPktCode) {
		this.editPktCode = editPktCode;
	}
	/**
	 * @return the rapCode
	 */
	public String getRapCode() {
		return rapCode;
	}
	/**
	 * @param rapCode the rapCode to set
	 */
	public void setRapCode(String rapCode) {
		this.rapCode = rapCode;
	}
	/**
	 * @return the vendorStockCode
	 */
	public String getVendorStockCode() {
		return vendorStockCode;
	}
	/**
	 * @param vendorStockCode the vendorStockCode to set
	 */
	public void setVendorStockCode(String vendorStockCode) {
		this.vendorStockCode = vendorStockCode;
	}
	/**
	 * @return the availabilty
	 */
	public String getAvailabilty() {
		return availabilty;
	}
	/**
	 * @param availabilty the availabilty to set
	 */
	public void setAvailabilty(String availabilty) {
		this.availabilty = availabilty;
	}
	/**
	 * @return the sarinFIle
	 */
	public String getSarinFile() {
		return sarinFile;
	}
	/**
	 * @param sarinFIle the sarinFIle to set
	 */
	public void setSarinFile(String sarinFile) {
		this.sarinFile = sarinFile;
	}
	/**
	 * @return the gemFile
	 */
	public String getGemFile() {
		return gemFile;
	}
	/**
	 * @param gemFile the gemFile to set
	 */
	public void setGemFile(String gemFile) {
		this.gemFile = gemFile;
	}
	/**
	 * @return the clientRow
	 */
	public String getClientRow() {
		return clientRow;
	}
	/**
	 * @param clientRow the clientRow to set
	 */
	public void setClientRow(String clientRow) {
		this.clientRow = clientRow;
	}
	/**
	 * @return the lastUpdateDate
	 */
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}
	/**
	 * @param lastUpdateDate the lastUpdateDate to set
	 */
	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	/**
	 * @return the updateBy
	 */
	public Integer getUpdateBy() {
		return updateBy;
	}
	/**
	 * @param updateBy the updateBy to set
	 */
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * @return the rapnetFlag
	 */
	public String getRapnetFlag() {
		return rapnetFlag;
	}
	/**
	 * @param rapnetFlag the rapnetFlag to set
	 */
	public void setRapnetFlag(String rapnetFlag) {
		this.rapnetFlag = rapnetFlag;
	}
	/**
	 * @return the websiteFlag
	 */
	public String getWebsiteFlag() {
		return websiteFlag;
	}
	/**
	 * @param websiteFlag the websiteFlag to set
	 */
	public void setWebsiteFlag(String websiteFlag) {
		this.websiteFlag = websiteFlag;
	}
	
	public Double getAskingPriceDisc() {
		return askingPriceDisc;
	}
	public void setAskingPriceDisc(Double askingPriceDisc) {
		this.askingPriceDisc = askingPriceDisc;
	}
	/**
	 * @return the issueCts
	 */
	public Double getIssueCts() {
		return issueCts;
	}
	/**
	 * @param issueCts the issueCts to set
	 */
	public void setIssueCts(Double issueCts) {
		this.issueCts = issueCts;
	}
	

	
}
