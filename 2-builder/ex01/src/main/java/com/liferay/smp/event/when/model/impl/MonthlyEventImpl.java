package com.liferay.smp.event.when.model.impl;

import com.liferay.smp.event.when.model.DayOfMonth;
import com.liferay.smp.event.when.model.MonthlyEvent;
import com.liferay.smp.event.when.model.Reoccurrence;

public class MonthlyEventImpl extends ReoccurringEventImpl implements MonthlyEvent {

	private static final long serialVersionUID = 1L;
	private static Reoccurrence reoccurrence = null;

	public MonthlyEventImpl() {
		this(1);
	}

	public MonthlyEventImpl(int frequency) {
		reoccurrence = new ReoccurrenceImpl(Reoccurrence.Type.MONTHLY, frequency);
	}

	private DayOfMonth dayOfMonth;

	@Override
	public Reoccurrence getReoccurrence() { return reoccurrence; }
	@Override
	public DayOfMonth getRepeatOn() { return dayOfMonth; }
	@Override
	public void setRepeatOn(DayOfMonth dayOfMonth) { this.dayOfMonth = dayOfMonth; }
}