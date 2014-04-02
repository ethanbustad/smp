package com.liferay.smp.event.where.model;

public interface Location {

	public enum Type {
		ROOM, ADDRESS, WEBEX, PHONE
	}

	public Type getType();
	@Override
	public String toString();
}