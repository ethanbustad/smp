package com.liferay.smp.model;

public enum Action {
	ADD("ADD"), GET("GET"), EDIT("EDIT"), DELETE("DELETE"), PING("PING"),
	BEFRIEND("BEFRIEND"), ACCEPT("ACCEPT"), REJECT("REJECT");

	private final String id;
	private Action(String id) { this.id = id; }
	@Override
	public String toString() { return this.id; }
}