package com.liferay.smp.service;

import com.liferay.smp.event.when.model.EventTime;
import com.liferay.smp.event.where.model.MeetingRoom;

public interface MeetingRoomSchedulingService {

	public MeetingRoom bookARoom(EventTime eventTime, int numberOfParticipants);
}