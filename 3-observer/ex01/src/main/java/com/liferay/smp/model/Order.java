package com.liferay.smp.model;

import java.util.List;

import com.liferay.smp.IllegalOperationException;

public interface Order {

	public enum Status {
		NEW(-1), ON_HOLD(0), CONFIRMED(1), PROCESSING(2), SHIPPED(3), DELETED(4);
		private final int id;

		private Status(int id) {
			this.id = id;
		}

		public int getId() {
			return this.id;
		}
	};

	public void confirm() throws IllegalOperationException;
	public void delete()throws IllegalOperationException;
	public List<Product> getProducts();
	public Status getOrderStatus();
	public long getCustomerId();
}