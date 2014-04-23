package com.liferay.smp.audit;

public enum Level {
	HIGHEST("HIGHEST"), HIGH("HIGH"), NORMAL("NORMAL"), LOW("LOW"), LOWEST("LOWEST");
	private final String id;
	private Level(String id) { this.id = id; }
	@Override
	public String toString() { return this.id; }
}