package com.liferay.smp.event.when.model;

import java.io.Serializable;
import java.util.Date;

public interface EventTime extends Serializable {

	public enum Type {
		ONCE, REOCCURRING
	}

	public Type getType();
	public Date getStartDate();
	public void setStartDate(Date startDate);
	public Date getEndDate();
	public void setEndDate(Date endDate);
	public boolean isAllDay();
	public void setAllDay(boolean allDay);

	@Override
	public String toString();
}