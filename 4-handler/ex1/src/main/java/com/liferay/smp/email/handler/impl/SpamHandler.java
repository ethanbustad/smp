package com.liferay.smp.email.handler.impl;

import com.liferay.smp.email.handler.EmailHandler;
import com.liferay.smp.email.model.Email;
import com.liferay.smp.email.util.EmailProperties;

public class SpamHandler implements EmailHandler {

	public void processEmail(Email email) {
		if (_isPriorityOverride(email)) {
			return;
		}

		if (_isSpam(email)) {
			email.setFolder(Email.Folder.SPAM);
		}

		if (successor != null) {
			successor.processEmail(email);
		}
	}

	public void setSuccessor(EmailHandler handler) {
		successor = handler;
	}

	private boolean _isPriorityOverride(Email email) {
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

	private EmailHandler successor;

}