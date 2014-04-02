package com.liferay.smp.service;

import com.liferay.smp.event.when.model.EventTime;
import com.liferay.smp.event.where.model.OnlineConference;

public interface WebExMeetingService {

	public OnlineConference createNewConference(EventTime eventTime);
}