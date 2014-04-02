package com.liferay.smp.event.when.model;

public interface Reoccurrence {

	public enum Type {
		DAILY, WEEKLY, EVERY_WEEKDAY, EVERY_MON_WED_FRI, EVERY_TUES_THURS, MONTHLY, YEARLY
	}

	public Type getType();
	public int getFrequency();
	@Override
	public String toString();
}