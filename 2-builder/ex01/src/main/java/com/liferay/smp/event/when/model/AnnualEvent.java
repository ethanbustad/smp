package com.liferay.smp.event.when.model;

public interface AnnualEvent extends ReoccurringEvent {

	public DayOfMonth getDayOfMonth();
	public void setDayOfMonth(DayOfMonth dayOfMonth);
	public Month getMonth();
	public void setMonth(Month month);
}