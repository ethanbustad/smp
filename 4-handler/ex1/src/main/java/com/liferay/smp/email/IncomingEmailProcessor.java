package com.liferay.smp.email;

import com.liferay.smp.email.handler.EmailHandler;
import com.liferay.smp.email.model.Email;

public class IncomingEmailProcessor implements EmailProcessor {

	public void processEmail(Email email) {
		handler.processEmail(email);
	}

	public void setHandler(EmailHandler handler) {
		this.handler = handler;
	}

	private EmailHandler handler;

}