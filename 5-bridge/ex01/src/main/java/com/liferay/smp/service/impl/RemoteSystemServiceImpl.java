package com.liferay.smp.service.impl;

import com.liferay.smp.service.RemoteSystemService;
import com.liferay.smp.util.MessageConstants;

public class RemoteSystemServiceImpl implements RemoteSystemService {

	@Override
	public void checkSystemHealth() {
		// logic to check system
		sendAudit(MessageConstants.REMOTE_SYSTEM_OK);
	}

	/**
	 * Exercise 1: This is a client using the auditing framework you're
	 * creating.  Please a call to your auditing framework from here.
	 * @param message
	 */
	protected void sendAudit(String message) {
	}
}