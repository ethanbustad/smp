package com.liferay.smp.email;

import com.liferay.smp.email.handler.EmailHandler;
import com.liferay.smp.email.model.Email;

public interface EmailProcessor {

	public void setHandler(EmailHandler handler);
	public void processEmail(Email email);
}