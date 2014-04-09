package com.liferay.smp.model;

public interface OrderObservable extends Order {

	public void addOrderObserver(OrderObserver o);
	public void notifyOrderObservers();
	public void removeOrderObserver(OrderObserver o);

}