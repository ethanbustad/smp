package com.liferay.smp.event.when.model.impl;

import com.liferay.smp.event.when.model.AnnualEvent;
import com.liferay.smp.event.when.model.DayOfMonth;
import com.liferay.smp.event.when.model.Month;
import com.liferay.smp.event.when.model.Reoccurrence;

public class AnnualEventImpl extends ReoccurringEventImpl implements AnnualEvent{

	private static final long serialVersionUID = 1L;
	private static final Reoccurrence reoccurrence =
		new ReoccurrenceImpl(Reoccurrence.Type.YEARLY, 1);
	private DayOfMonth dayOfMonth;
	private Month month;

	@Override
	public Reoccurrence getReoccurrence() { return reoccurrence; }
	@Override
	public DayOfMonth getDayOfMonth() { return dayOfMonth; }
	@Override
	public void setDayOfMonth(DayOfMonth dayOfMonth) { this.dayOfMonth = dayOfMonth; }
	@Override
	public Month getMonth() { return month; }
	@Override
	public void setMonth(Month month) { this.month = month; }
}