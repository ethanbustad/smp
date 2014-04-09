package com.liferay.smp.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.liferay.smp.service.AccountingService;

public class AccountingOrderObserver implements OrderObserver {

	public void handleUpdate(Order order) {
		accountingService.processPayment(order);
	}

	@Autowired
	private AccountingService accountingService;

}