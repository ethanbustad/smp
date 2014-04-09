package com.liferay.smp.service;

import com.liferay.smp.model.Order;

public interface WarehouseService {

	public void fulfillOrder(Order order);
}