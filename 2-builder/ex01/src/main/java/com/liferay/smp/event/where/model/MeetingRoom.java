package com.liferay.smp.event.where.model;

public interface MeetingRoom extends Location {

	public enum Size {
		SMALL, MEDIUM, LARGE, AUDITORIUM
	}

	public Size getRoomSize();
	public int getRoomNumber();
	public void setRoomNumber(int roomNumber);
	public String getBuilding();
	public void setBuilding(String building);
}