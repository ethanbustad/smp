package com.liferay.smp.event.when.model;

public interface WeeklyEvent extends ReoccurringEvent {

	public DayOfWeek getRepeatOn();
	public void setRepeatOn(DayOfWeek dayOfWeek);
}