package com.liferay.smp.model;

public interface OrderObserver {

	public void handleUpdate(Order order);

}