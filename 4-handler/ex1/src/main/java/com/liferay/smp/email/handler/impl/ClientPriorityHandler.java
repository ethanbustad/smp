package com.liferay.smp.email.handler.impl;

import com.liferay.smp.email.handler.EmailHandler;
import com.liferay.smp.email.model.Email;
import com.liferay.smp.email.util.EmailProperties;

public class ClientPriorityHandler extends EmailHandler {

	@Override
	protected void process(Email email) {
		if (_isFromClient(email)) {
			email.setPriority(Email.Priority.URGENT);
		}
	}

	private boolean _isFromClient(Email email) {
		String[] clientDomains = EmailProperties.getClientDomains();

		String fromLC = email.getFrom().toLowerCase();

		for (String clientDomain : clientDomains) {
			if (fromLC.contains(clientDomain.toLowerCase())) {
				return true;
			}
		}

		return false;
	}

}