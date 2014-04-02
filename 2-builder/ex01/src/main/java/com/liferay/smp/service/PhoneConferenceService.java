package com.liferay.smp.service;

import com.liferay.smp.event.when.model.EventTime;
import com.liferay.smp.event.where.model.PhoneConference;

public interface PhoneConferenceService {

	public PhoneConference createNewConference(EventTime eventTime);
}