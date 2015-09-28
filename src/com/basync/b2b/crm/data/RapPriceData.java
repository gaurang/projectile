package com.basync.b2b.crm.data;

import java.io.Serializable;

public class RapPriceData implements Serializable {
	
	private String shape ;
	private Double lowSize;
	private	Double highSize;
	private String color;
	private String clarity;
	private Double price;
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public Double getLowSize() {
		return lowSize;
	}
	public void setLowSize(Double lowSize) {
		this.lowSize = lowSize;
	}
	public Double getHighSize() {
		return highSize;
	}
	public void setHighSize(Double highSize) {
		this.highSize = highSize;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	

}
