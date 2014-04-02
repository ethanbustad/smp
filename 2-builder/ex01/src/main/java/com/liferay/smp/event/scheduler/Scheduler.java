package com.liferay.smp.event.scheduler;

import com.liferay.smp.event.model.Event;
import com.liferay.smp.event.who.model.User;

import java.util.Date;
import java.util.List;

public class Scheduler {

	public Event constructEvent(
			String name, String description, Date startDate, Date endDate,
			List<User> inviters, List<User> invitees, EventBuilder builder)
		throws Exception {

		builder.buildEvent(name, description);
		builder.buildDateTime(startDate, endDate);
		builder.buildParticipants(inviters, invitees);
		builder.buildLocation();

		return builder.getResult();
	}

}