package com.basync.b2b.crm.data;

/**
 * For mapping with table tb_ExcelFile
 * @author gaurang
 *
 */
public class FileTypes {

	private Integer id ;
	
	private String fileName;
	
	private String CompanyName;
	
	private String type;
	
	private String processType;
	
	private int sortId;
	
	private int rapFormat;
	
	private String lab;
	
	private String pktType; 

	/**
	 * @return the pktType
	 */
	public String getPktType() {
		return pktType;
	}

	/**
	 * @param pktType the pktType to set
	 */
	public void setPktType(String pktType) {
		this.pktType = pktType;
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
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return CompanyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the processType
	 */
	public String getProcessType() {
		return processType;
	}

	/**
	 * @param processType the processType to set
	 */
	public void setProcessType(String processType) {
		this.processType = processType;
	}

	/**
	 * @return the sortId
	 */
	public int getSortId() {
		return sortId;
	}

	/**
	 * @param sortId the sortId to set
	 */
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

	/**
	 * @return the rapFormat
	 */
	public int getRapFormat() {
		return rapFormat;
	}

	/**
	 * @param rapFormat the rapFormat to set
	 */
	public void setRapFormat(int rapFormat) {
		this.rapFormat = rapFormat;
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
	
	
	
}
