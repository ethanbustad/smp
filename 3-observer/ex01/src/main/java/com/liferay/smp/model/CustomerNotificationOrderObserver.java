package com.liferay.smp.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.liferay.smp.service.CustomerNotificationService;

public class CustomerNotificationOrderObserver implements OrderObserver {

	public void handleUpdate(Order order) {
		customerNotificationService.sendOrderNotification(order);
	}

	@Autowired
	private CustomerNotificationService customerNotificationService;

}