package com.liferay.smp.service;

import com.liferay.smp.model.Customer;
import com.liferay.smp.model.Order;

public interface CustomerNotificationService {

	public void sendOrderNotification(Order order);
	public void sendProfileUpdateNotification(Customer customer);
}