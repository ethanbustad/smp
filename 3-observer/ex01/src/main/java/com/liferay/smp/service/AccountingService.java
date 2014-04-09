package com.liferay.smp.service;

import com.liferay.smp.model.Order;

public interface AccountingService {

	public void processPayment(Order order);
	public void refundCustomer(Order order);
}