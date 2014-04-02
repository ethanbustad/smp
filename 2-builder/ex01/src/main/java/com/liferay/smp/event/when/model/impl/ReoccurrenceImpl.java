package com.liferay.smp.event.when.model.impl;

import com.liferay.smp.event.when.model.Reoccurrence;

public class ReoccurrenceImpl implements Reoccurrence {

	public ReoccurrenceImpl(Type type, int frequency) {
		this.type = type;
		this.frequency = frequency;
	}

	private Type type;
	private int frequency;

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public int getFrequency() {
		return frequency;
	}
}