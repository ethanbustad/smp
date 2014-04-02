package com.liferay.smp.event.who.model;

public class User {

	private long userId;
	private String firstName;
	private String lastName;
	private String emailAddress;

	public long getUserId() { return userId; }
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public String getEmailAddress() { return emailAddress; }

	public void setUserId(long userId) { this.userId = userId; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
}