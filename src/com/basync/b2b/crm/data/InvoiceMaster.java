package com.basync.b2b.crm.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="tb_invoiceMaster")
public class InvoiceMaster implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6719298718863830335L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="partyAccId")
	private Integer partyAccId; 
	
	@Transient
	private String companyName; 
	@Transient
	private String brokerName; 
	
	
	@Column(name="invoiceDate")
	private String  invoiceDate;
	@Column(name="dueDate")
	private String  dueDate ;
	@Column(name="invType")
	private String invType ;
	@Column(name="invoiceCode")
	private String invoiceCode; 
	@Column(name="totalAmount")
	private Double totalAmount ;
	@Column(name="tax")
	private Double tax ;
	@Column(name="discount")
	private Double discount;
	@Column(name="shipCharges")
	private Double shipCharges;
	@Column(name="expences")
	private Double expences ;
	@Column(name="invStatus")
	private String invStatus ;
	@Column(name="memoOrderId")
	private Integer memoOrderId ;
	@Column(name="consigneePartyId")
	private Integer consigneePartyId ;
	@Column(name="consignmentCode")
	
	
	private String consignmentCode;
	@Column(name="brokerId")
	private Integer brokerId ;
	@Column(name="brokrage")
	private Double brokrage ;
	@Column(name="exportStatus")
	private String exportStatus;
	@Column(name="finalAmount")
	private Double finalAmount;
	@Column(name="paidAmt")
	private Double paidAmt;
	
	@Column(name="userId")
	private Integer userId;
	
	@Column(name="preCarrierPartyId")
	private Integer preCarrierPartyId;
	
	@Column(name="placeOfPreCarrier")
	private String placeOfPreCarrier;
	
	@Column(name="vesselFlight")
	private String vesselFlight;
	
	@Column(name="portOfLoad")
	private String portOfLoad;
	
	@Column(name="portOfDischarge")
	private String portOfDischarge;
	
	@Column(name="destination")
	private String destination;
	
	@Column(name="consigneeName") 
	private String consigneeName;
	
	@Column(name="carrierPartyId")
	private String carrierPartyId;
	
	@Column(name="invCode")
	private String invCode;
	
	@Column(name="expRefNo")
	private String expRefNo;
	
	@Column(name="othRefNo")
	private String othRefNo;
	
	@Transient
	@Column(name="termDesc")
	private String termDesc;
	
	@Column(name="status")
	private String status;
	
	@Column(name="pan")
	private String pan;
	
	@Column(name="cstVat")
	private String cstVat;
	
	@Transient
	@Column(name="exRate")
	private float exRate;
	
	public float getExRate() {
		return exRate;
	}
	public void setExRate(float exRate) {
		this.exRate = exRate;
	}
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
	 * @return the partyAddId
	 */
	public Integer getPartyAccId() {
		return partyAccId;
	}
	/**
	 * @param partyAddId the partyAddId to set
	 */
	public void setPartyAccId(Integer partyAccId) {
		this.partyAccId = partyAccId;
	}
	/**
	 * @return the invoiceDate
	 */
	public String getInvoiceDate() {
		return invoiceDate;
	}
	/**
	 * @param invoiceDate the invoiceDate to set
	 */
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
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
	 * @return the invType
	 */
	public String getInvType() {
		return invType;
	}
	/**
	 * @param invType the invType to set
	 */
	public void setInvType(String invType) {
		this.invType = invType;
	}
	/**
	 * @return the invoiceCode
	 */
	public String getInvoiceCode() {
		return invoiceCode;
	}
	/**
	 * @param invoiceCode the invoiceCode to set
	 */
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	/**
	 * @return the totalAmount
	 */
	public Double getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * @return the tax
	 */
	public Double getTax() {
		return tax;
	}
	/**
	 * @param tax the tax to set
	 */
	public void setTax(Double tax) {
		this.tax = tax;
	}
	/**
	 * @return the discount
	 */
	public Double getDiscount() {
		return discount;
	}
	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	/**
	 * @return the shipCharges
	 */
	public Double getShipCharges() {
		return shipCharges;
	}
	/**
	 * @param shipCharges the shipCharges to set
	 */
	public void setShipCharges(Double shipCharges) {
		this.shipCharges = shipCharges;
	}
	/**
	 * @return the expences
	 */
	public Double getExpences() {
		return expences;
	}
	/**
	 * @param expences the expences to set
	 */
	public void setExpences(Double expences) {
		this.expences = expences;
	}
	/**
	 * @return the invStatus
	 */
	public String getInvStatus() {
		return invStatus;
	}
	/**
	 * @param invStatus the invStatus to set
	 */
	public void setInvStatus(String invStatus) {
		this.invStatus = invStatus;
	}
	/**
	 * @return the memoOrderId
	 */
	public Integer getMemoOrderId() {
		return memoOrderId;
	}
	/**
	 * @param memoOrderId the memoOrderId to set
	 */
	public void setMemoOrderId(Integer memoOrderId) {
		this.memoOrderId = memoOrderId;
	}
	
	
	/**
	 * @return the consigneePartyId
	 */
	public Integer getConsigneePartyId() {
		return consigneePartyId;
	}
	/**
	 * @param consigneePartyId the consigneePartyId to set
	 */
	public void setConsigneePartyId(Integer consigneePartyId) {
		this.consigneePartyId = consigneePartyId;
	}
	/**
	 * @return the consignmentCode
	 */
	public String getConsignmentCode() {
		return consignmentCode;
	}
	/**
	 * @param consignmentCode the consignmentCode to set
	 */
	public void setConsignmentCode(String consignmentCode) {
		this.consignmentCode = consignmentCode;
	}
	/**
	 * @return the brokerId
	 */
	public Integer getBrokerId() {
		return brokerId;
	}
	/**
	 * @param brokerId the brokerId to set
	 */
	public void setBrokerId(Integer brokerId) {
		this.brokerId = brokerId;
	}
	/**
	 * @return the brokrage
	 */
	public Double getBrokrage() {
		return brokrage;
	}
	/**
	 * @param brokrage the brokrage to set
	 */
	public void setBrokrage(Double brokrage) {
		this.brokrage = brokrage;
	}
	/**
	 * @return the exportStatus
	 */
	public String getExportStatus() {
		return exportStatus;
	}
	/**
	 * @param exportStatus the exportStatus to set
	 */
	public void setExportStatus(String exportStatus) {
		this.exportStatus = exportStatus;
	}
	/**
	 * @return the finalAmount
	 */
	public Double getFinalAmount() {
		return finalAmount;
	}
	/**
	 * @param finalAmount the finalAmount to set
	 */
	public void setFinalAmount(Double finalAmount) {
		this.finalAmount = finalAmount;
	}
	/**
	 * @return the paidAmt
	 */
	public Double getPaidAmt() {
		return paidAmt;
	}
	/**
	 * @param paidAmt the paidAmt to set
	 */
	public void setPaidAmt(Double paidAmt) {
		this.paidAmt = paidAmt;
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
	 * @return the consigneeName
	 */
	public String getConsigneeName() {
		return consigneeName;
	}
	/**
	 * @param consigneeName the consigneeName to set
	 */
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	/**
	 * @return the preCarrierPartyId
	 */
	public Integer getPreCarrierPartyId() {
		return preCarrierPartyId;
	}
	/**
	 * @param preCarrierPartyId the preCarrierPartyId to set
	 */
	public void setPreCarrierPartyId(Integer preCarrierPartyId) {
		this.preCarrierPartyId = preCarrierPartyId;
	}
	/**
	 * @return the placeOfPreCarrier
	 */
	public String getPlaceOfPreCarrier() {
		return placeOfPreCarrier;
	}
	/**
	 * @param placeOfPreCarrier the placeOfPreCarrier to set
	 */
	public void setPlaceOfPreCarrier(String placeOfPreCarrier) {
		this.placeOfPreCarrier = placeOfPreCarrier;
	}
	/**
	 * @return the vesselFlight
	 */
	public String getVesselFlight() {
		return vesselFlight;
	}
	/**
	 * @param vesselFlight the vesselFlight to set
	 */
	public void setVesselFlight(String vesselFlight) {
		this.vesselFlight = vesselFlight;
	}
	/**
	 * @return the portOfLoad
	 */
	public String getPortOfLoad() {
		return portOfLoad;
	}
	/**
	 * @param portOfLoad the portOfLoad to set
	 */
	public void setPortOfLoad(String portOfLoad) {
		this.portOfLoad = portOfLoad;
	}
	/**
	 * @return the portOfDischarge
	 */
	public String getPortOfDischarge() {
		return portOfDischarge;
	}
	/**
	 * @param portOfDischarge the portOfDischarge to set
	 */
	public void setPortOfDischarge(String portOfDischarge) {
		this.portOfDischarge = portOfDischarge;
	}
	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	/**
	 * @return the carrierPartyId
	 */
	public String getCarrierPartyId() {
		return carrierPartyId;
	}
	/**
	 * @param carrierPartyId the carrierPartyId to set
	 */
	public void setCarrierPartyId(String carrierPartyId) {
		this.carrierPartyId = carrierPartyId;
	}
	/**
	 * @return the invCode
	 */
	public String getInvCode() {
		return invCode;
	}
	/**
	 * @param invCode the invCode to set
	 */
	public void setInvCode(String invCode) {
		this.invCode = invCode;
	}
	/**
	 * @return the expRefNo
	 */
	public String getExpRefNo() {
		return expRefNo;
	}
	/**
	 * @param expRefNo the expRefNo to set
	 */
	public void setExpRefNo(String expRefNo) {
		this.expRefNo = expRefNo;
	}
	/**
	 * @return the othRefNo
	 */
	public String getOthRefNo() {
		return othRefNo;
	}
	/**
	 * @param othRefNo the othRefNo to set
	 */
	public void setOthRefNo(String othRefNo) {
		this.othRefNo = othRefNo;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getCstVat() {
		return cstVat;
	}
	public void setCstVat(String cstVat) {
		this.cstVat = cstVat;
	}

	
}
