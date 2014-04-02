package com.liferay.smp.event.scheduler;

import com.liferay.smp.event.model.Event;
import com.liferay.smp.event.when.model.EventTime;
import com.liferay.smp.event.who.model.Participant;
import com.liferay.smp.event.who.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class EventBuilder {

	public abstract void buildDateTime(Date startDate, Date endDate)
		throws Exception;

	public void buildEvent(String name, String description) throws Exception {
		if (event == null) {
			event = new Event();
		}

		event.setDescription(description);
		event.setName(name);
	}

	public abstract void buildParticipants(
			List<User> inviters, List<User> invitees)
		throws Exception;

	public abstract void buildLocation() throws Exception;

	public Event getResult() throws Exception {
		return event;
	}

	protected void buildDateTime(
		EventTime eventTime, Date startDate, Date endDate, boolean allDay) {

		eventTime.setAllDay(allDay);
		eventTime.setEndDate(endDate);
		eventTime.setStartDate(startDate);

		event.setEventTime(eventTime);
	}

	protected void buildParticipants(
		List<User> inviters, boolean inviterOptional, boolean inviterReminder,
		List<User> invitees, boolean inviteeOptional, boolean inviteeReminder) {

		List<Participant> inviteeParticipants = new ArrayList<Participant>();

		for (User user : invitees) {
			Participant participant = new Participant(user);

			participant.setOptional(inviteeOptional);
			participant.setSendReminder(inviteeReminder);

			inviteeParticipants.add(participant);
		}

		event.setInvitees(inviteeParticipants);

		List<Participant> inviterParticipants = new ArrayList<Participant>();

		for (User user : inviters) {
			Participant participant = new Participant(user);

			participant.setOptional(inviterOptional);
			participant.setSendReminder(inviterReminder);

			inviterParticipants.add(participant);
		}

		event.setInviters(inviterParticipants);
	}

	protected Event event;

}