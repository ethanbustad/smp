package com.liferay.smp.model;

public class ProductImpl implements Product {

	private long productId;
	private String name;
	private String description;
	private String modelNumber;

	public ProductImpl(
		long productId, String name, String description, String modelNumber) {

		this.productId = productId;
		this.name = name;
		this.description = description;
		this.modelNumber = modelNumber;
	}

	@Override
	public long getProductId() {
		return productId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getModelNumber() {
		return modelNumber;
	}

}
