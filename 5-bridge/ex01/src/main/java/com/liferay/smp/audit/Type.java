package com.liferay.smp.audit;

public enum Type {
	GENERAL(0), USER_ACTION(1), TIME_SENSITIVE(2);
	private final int id;
	private Type(int id) { this.id = id; }
	public int getId() {
		return this.id;
	}
}