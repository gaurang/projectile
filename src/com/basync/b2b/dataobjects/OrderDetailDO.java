package com.basync.b2b.dataobjects;

public class OrderDetailDO {

	private String pktCd;
	private String status;
	private String cts;
	private String sh;
	private String rate;
	public String getPktCd() {
		return pktCd;
	}
	public void setPktCd(String pktCd) {
		this.pktCd = pktCd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCts() {
		return cts;
	}
	public void setCts(String cts) {
		this.cts = cts;
	}
	public String getSh() {
		return sh;
	}
	public void setSh(String sh) {
		this.sh = sh;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
}
