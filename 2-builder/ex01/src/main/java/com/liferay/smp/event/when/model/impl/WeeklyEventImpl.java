package com.liferay.smp.event.when.model.impl;

import com.liferay.smp.event.when.model.DayOfWeek;
import com.liferay.smp.event.when.model.Reoccurrence;
import com.liferay.smp.event.when.model.WeeklyEvent;

public class WeeklyEventImpl extends ReoccurringEventImpl implements WeeklyEvent {

	private static final long serialVersionUID = 1L;
	private static Reoccurrence reoccurrence = null;
	private DayOfWeek dayOfWeek;

	public WeeklyEventImpl() {
		this(1);
	}

	public WeeklyEventImpl(int frequency) {
		reoccurrence = new ReoccurrenceImpl(Reoccurrence.Type.WEEKLY, frequency);
	}

	@Override
	public Reoccurrence getReoccurrence() { return reoccurrence; }
	@Override
	public DayOfWeek getRepeatOn() { return dayOfWeek; }
	@Override
	public void setRepeatOn(DayOfWeek dayOfWeek) { this.dayOfWeek = dayOfWeek; }
}