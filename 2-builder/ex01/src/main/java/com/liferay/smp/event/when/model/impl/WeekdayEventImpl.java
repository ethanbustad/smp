package com.liferay.smp.event.when.model.impl;

import com.liferay.smp.event.when.model.Reoccurrence;


public class WeekdayEventImpl extends ReoccurringEventImpl {

	private static final long serialVersionUID = 1L;
	private static final Reoccurrence reoccurrence =
		new ReoccurrenceImpl(Reoccurrence.Type.EVERY_WEEKDAY, 1);

	@Override
	public Reoccurrence getReoccurrence() {
		return reoccurrence;
	}
}