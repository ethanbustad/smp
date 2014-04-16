package com.liferay.smp.email.handler.impl;

import com.liferay.smp.email.handler.EmailHandler;
import com.liferay.smp.email.model.Email;
import com.liferay.smp.email.util.EmailProperties;

public class ClientPriorityHandler implements EmailHandler {

	public void processEmail(Email email) {
		if (_isFromClient(email)) {
			email.setPriority(Email.Priority.URGENT);
		}

		if (successor != null) {
			successor.processEmail(email);
		}
	}

	public void setSuccessor(EmailHandler handler) {
		successor = handler;
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

	private EmailHandler successor;

}