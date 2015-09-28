package com.basync.b2b.dataobjects;


public class StockUploadDO extends BaseDO{
	private Long srNO;	
	private Long refNO;	
	public Double getTable() {
		return table;
	}
	public void setTable(Double table) {
		this.table = table;
	}
	private String shape;	
	private String color;	
	private String clarity;	
	private Double weight;
	private Double price;	
	private String cut;
	private String polish;	
	private String symm;
	private String flour;	
	private String diam	;
	private Double dep;	
	private Double table;	
	private String cert	;
	private Long certno;	
	private Double discount;
	public Long getSrNO() {
		return srNO;
	}
	public void setSrNO(Long srNO) {
		this.srNO = srNO;
	}
	public Long getRefNO() {
		return refNO;
	}
	public void setRefNO(Long refNO) {
		this.refNO = refNO;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getClarity() {
		return clarity;
	}
	public void setClarity(String clarity) {
		this.clarity = clarity;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCut() {
		return cut;
	}
	public void setCut(String cut) {
		this.cut = cut;
	}
	public String getPolish() {
		return polish;
	}
	public void setPolish(String polish) {
		this.polish = polish;
	}
	public String getSymm() {
		return symm;
	}
	public void setSymm(String symm) {
		this.symm = symm;
	}
	public String getFlour() {
		return flour;
	}
	public void setFlour(String flour) {
		this.flour = flour;
	}
	public Double getDep() {
		return dep;
	}
	public void setDep(Double dep) {
		this.dep = dep;
	}
	public String getDiam() {
		return diam;
	}
	public void setDiam(String diam) {
		this.diam = diam;
	}
	public String getCert() {
		return cert;
	}
	public void setCert(String cert) {
		this.cert = cert;
	}
	public Long getCertno() {
		return certno;
	}
	public void setCertno(Long certno) {
		this.certno = certno;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
		
}
