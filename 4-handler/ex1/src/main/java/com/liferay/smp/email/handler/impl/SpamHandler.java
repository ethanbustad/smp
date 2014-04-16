package com.liferay.smp.email.handler.impl;

import com.liferay.smp.email.handler.EmailHandler;
import com.liferay.smp.email.model.Email;
import com.liferay.smp.email.util.EmailProperties;

public class SpamHandler extends EmailHandler {

	@Override
	protected void process(Email email) {
		if (_hasPriorityOverride(email)) {
			return;
		}

		if (_isSpam(email)) {
			email.setFolder(Email.Folder.SPAM);
		}
	}

	private boolean _hasPriorityOverride(Email email) {
		return ((email.getPriority() == Email.Priority.URGENT) ||
			(email.getPriority() == Email.Priority.HIGH) ||
			(email.getPriority() == Email.Priority.LOW));
	}

	private boolean _isSpam(Email email) {
		String[] spamKeywords = EmailProperties.getSpamKeywords();

		String subjectLC = email.getSubject().toLowerCase();

		for (String spamKeyword : spamKeywords) {
			spamKeyword = spamKeyword.toLowerCase();

			if (subjectLC.contains(spamKeyword)) {
				return true;
			}
		}

		String bodyLC = email.getBody().toLowerCase();

		for (String spamKeyword : spamKeywords) {
			spamKeyword = spamKeyword.toLowerCase();

			if (bodyLC.contains(spamKeyword)) {
				return true;
			}
		}

		return false;
	}

}