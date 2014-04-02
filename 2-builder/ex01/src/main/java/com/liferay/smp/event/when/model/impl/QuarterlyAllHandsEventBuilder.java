package com.liferay.smp.event.scheduler;

import com.liferay.smp.event.model.Event;
import com.liferay.smp.event.when.model.EventTime;
import com.liferay.smp.event.when.model.impl.MonthlyEventImpl;
import com.liferay.smp.event.who.model.Participant;
import com.liferay.smp.event.who.model.User;
import com.liferay.smp.service.MeetingRoomSchedulingService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class QuarterlyAllHandsEventBuilder extends EventBuilder {

	public void buildDateTime(Date startDate, Date endDate) throws Exception {
		EventTime eventTime = new MonthlyEventImpl(3);

		buildDateTime(eventTime, startDate, endDate, false);
	}

	public void buildLocation() throws Exception {
		int participantsCount = _getParticipantsCount();

		event.setLocation(_meetingRoomSchedulingService.bookARoom(
			event.getEventTime(), participantsCount));
	}

	public void buildParticipants(List<User> inviters, List<User> invitees)
		throws Exception {

		buildParticipants(inviters, false, true, invitees, true, true);
	}

	private int _getParticipantsCount() throws Exception {
		List<Participant> invitees = event.getInvitees();
		List<Participant> inviters = event.getInviters();

		return invitees.size() + inviters.size();
	}

	@Autowired
	private MeetingRoomSchedulingService _meetingRoomSchedulingService;

}