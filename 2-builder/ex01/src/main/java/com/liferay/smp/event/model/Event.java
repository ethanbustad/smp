package com.liferay.smp.event.model;

import java.util.List;

import com.liferay.smp.event.when.model.EventTime;
import com.liferay.smp.event.where.model.Location;
import com.liferay.smp.event.who.model.Participant;

public class Event {

	// WHAT
	private String name;
	private String description;

	// WHEN
	private EventTime eventTime;

	// WHERE
	private Location location;

	// WHO
	private List<Participant> inviters;
	private List<Participant> invitees;

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public EventTime getEventTime() { return eventTime; }
	public void setEventTime(EventTime eventTime) { this.eventTime = eventTime; }

	public Location getLocation() { return location; }
	public void setLocation(Location location) { this.location = location; }

	public List<Participant> getInviters() { return inviters; }
	public void setInviters(List<Participant> inviters) { this.inviters = inviters; }

	public List<Participant> getInvitees() { return invitees; }
	public void setInvitees(List<Participant> invitees) { this.invitees = invitees; }
}