package com.liferay.smp.email.handler.impl;

import com.liferay.smp.email.handler.EmailHandler;
import com.liferay.smp.email.model.Email;
import com.liferay.smp.email.util.EmailProperties;

public class JIRAHandler extends EmailHandler {

	@Override
	protected void process(Email email) {
		if (_isFromJIRA(email)) {
			email.setFolder(Email.Folder.ARCHIVE);
		}
	}

	private boolean _isFromJIRA(Email email) {
		String subject = email.getSubject();

		return subject.contains(JIRA_SUBJECT);
	}

	private static final String JIRA_SUBJECT = "[JIRA]";

}