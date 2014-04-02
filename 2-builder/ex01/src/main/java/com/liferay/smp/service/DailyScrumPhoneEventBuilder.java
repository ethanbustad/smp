package com.liferay.smp.event.scheduler;

import com.liferay.smp.event.model.Event;
import com.liferay.smp.event.when.model.EventTime;
import com.liferay.smp.event.when.model.impl.WeekdayEventImpl;
import com.liferay.smp.event.who.model.User;
import com.liferay.smp.service.PhoneConferenceService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class DailyScrumPhoneEventBuilder extends EventBuilder {

	public void buildDateTime(Date startDate, Date endDate) throws Exception {
		EventTime eventTime = new WeekdayEventImpl();

		buildDateTime(eventTime, startDate, endDate, false);
	}

	public void buildLocation() throws Exception {
		event.setLocation(_phoneConferenceService.createNewConference(
			event.getEventTime()));
	}

	public void buildParticipants(List<User> inviters, List<User> invitees)
		throws Exception {

		buildParticipants(inviters, false, true, invitees, false, true);
	}

	@Autowired
	private PhoneConferenceService _phoneConferenceService;

}