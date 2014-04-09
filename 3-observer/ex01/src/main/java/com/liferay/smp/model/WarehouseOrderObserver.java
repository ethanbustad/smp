package com.liferay.smp.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.liferay.smp.service.WarehouseService;

public class WarehouseOrderObserver implements OrderObserver {

	public void handleUpdate(Order order) {
		warehouseService.fulfillOrder(order);
	}

	@Autowired
	private WarehouseService warehouseService;

}