package com.liferay.smp.model;

import java.util.ArrayList;
import java.util.List;

import com.liferay.smp.IllegalOperationException;

public class OrderImpl implements Order {

	private List<Product> products;
	private Status status = Status.NEW;
	private long customerId;

	@Override
	public void confirm() throws IllegalOperationException {

		if (status.getId() >= Status.CONFIRMED.getId()) {
			throw new IllegalOperationException();
		}
		status = Status.CONFIRMED;
	}

	@Override
	public void delete() throws IllegalOperationException {

		if (status.getId() > Status.CONFIRMED.getId()) {
			throw new IllegalOperationException();
		}
		status = Status.DELETED;
	}

	@Override
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public Status getOrderStatus() {
		return status;
	}

	@Override
	public long getCustomerId() {
		return customerId;
	}

	public void addProduct(Product product) throws IllegalOperationException {

		if (status.getId() >= Status.CONFIRMED.getId()) {
			throw new IllegalOperationException();
		}

		if (products == null) {
			products = new ArrayList<Product>();
		}

		products.add(product);
	}
}