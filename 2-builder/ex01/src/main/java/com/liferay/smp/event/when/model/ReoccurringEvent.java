package com.liferay.smp.event.when.model;

public interface ReoccurringEvent extends EventTime {

	public Reoccurrence getReoccurrence();
	public boolean hasEnd();
	public void setEnd(boolean end);
}