package com.liferay.smp.model;

public interface Product {

	public long getProductId();
	public String getName();
	public String getDescription();
	public String getModelNumber();
	public double getPrice();
	public long getQuantityLeft();

	public void setName(String name);
	public void setDescription(String description);
	public void setModelNumber(String modelNumber);
	public void setPrice(double price);
	public void setQuantityLeft(long quantityLeft);
}