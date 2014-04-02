package com.liferay.smp.event.when.model;

public interface MonthlyEvent extends ReoccurringEvent {

	public DayOfMonth getRepeatOn();
	public void setRepeatOn(DayOfMonth dayOfMonth);
}