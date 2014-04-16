package com.liferay.smp.email.handler;

import com.liferay.smp.email.model.Email;

public interface EmailHandler {

	public void processEmail(Email email);
	public void setSuccessor(EmailHandler handler);
}