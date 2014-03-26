package com.liferay.smp.model;

public class ProductImpl implements Product {

	private long productId;
	private String name;
	private String description;
	private String modelNumber;
	private double price;
	private long quantityLeft;

	/**
	 * Do NOT modify this class
	 * @param productId
	 */
	public ProductImpl(long productId) {
		this.productId = productId;
	}

	@Override
	public long getProductId() { return productId; }
	@Override
	public String getName() { return name; }
	@Override
	public String getDescription() { return description; }
	@Override
	public String getModelNumber() { return modelNumber; }
	@Override
	public double getPrice() { return price; }
	@Override
	public long getQuantityLeft() { return quantityLeft; }
	@Override
	public void setName(String name) { this.name = name; }
	@Override
	public void setDescription(String description) { this.description = description; }
	@Override
	public void setModelNumber(String modelNumber) { this.modelNumber = modelNumber; }
	@Override
	public void setPrice(double price) { this.price = price; }
	@Override
	public void setQuantityLeft(long quantityLeft) { this.quantityLeft = quantityLeft; }
}