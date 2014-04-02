package com.liferay.smp.event.where.model;

public interface PhoneConference extends Location {

	public void setPhoneNumber(String phoneNumber);
	public void setPasscode(String passcode);
	public void setHostId(int hostId);
}