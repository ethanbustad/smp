package com.liferay.smp.email.handler.impl;

import com.liferay.smp.email.handler.EmailHandler;
import com.liferay.smp.email.model.Email;
import com.liferay.smp.email.util.EmailProperties;

public class JIRAHandler implements EmailHandler {

	public void processEmail(Email email) {
		if (_isFromJIRA(email)) {
			email.setFolder(Email.Folder.ARCHIVE);
		}

		if (successor != null) {
			successor.processEmail(email);
		}
	}

	public void setSuccessor(EmailHandler handler) {
		successor = handler;
	}

	private boolean _isFromJIRA(Email email) {
		String[] clientDomains = EmailProperties.getClientDomains();

		String subject = email.getSubject();

		return subject.contains(JIRA_SUBJECT);
	}

	private EmailHandler successor;

	private static final String JIRA_SUBJECT = "[JIRA]";

}