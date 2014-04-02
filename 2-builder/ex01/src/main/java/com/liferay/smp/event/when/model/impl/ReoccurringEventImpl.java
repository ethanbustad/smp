package com.liferay.smp.event.when.model.impl;

import java.util.Date;

import com.liferay.smp.event.when.model.ReoccurringEvent;

public abstract class ReoccurringEventImpl implements ReoccurringEvent {

	private static final long serialVersionUID = 1L;
	protected boolean end;
	private Date startDate;
	private Date endDate;
	private boolean allDay;

	@Override
	public Type getType() { return Type.REOCCURRING; }
	@Override
	public boolean hasEnd() { return end; }
	@Override
	public void setEnd(boolean end) { this.end = end; }
	@Override
	public Date getStartDate() { return startDate; }
	@Override
	public void setStartDate(Date startDate) { this.startDate = startDate; }
	@Override
	public Date getEndDate() { return endDate; }
	@Override
	public void setEndDate(Date endDate) { this.endDate = endDate; }
	@Override
	public boolean isAllDay() { return allDay; }
	@Override
	public void setAllDay(boolean allDay) { this.allDay = allDay; }
}
