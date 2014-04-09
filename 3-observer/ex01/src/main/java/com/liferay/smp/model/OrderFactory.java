package com.liferay.smp.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderFactory {

	public Order createOrder(List<OrderObserver> orderObservers) {
		OrderImpl order = new OrderImpl();

		for (OrderObserver oo : orderObservers) {
			order.addOrderObserver(oo);
		}

		return order;
	}

}