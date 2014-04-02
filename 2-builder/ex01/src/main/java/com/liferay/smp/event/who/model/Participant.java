package com.liferay.smp.event.who.model;

public class Participant extends User {

	public Participant(User user) {
		setUserId(user.getUserId());
		setFirstName(user.getFirstName());
		setLastName(user.getLastName());
		setEmailAddress(user.getEmailAddress());
	}

	private boolean optional;
	private boolean sendReminder;

	public boolean isOptional() { return optional; }
	public void setOptional(boolean optional) { this.optional = optional; }
	public boolean isSendReminder() { return sendReminder; }
	public void setSendReminder(boolean sendReminder) { this.sendReminder = sendReminder; }
}