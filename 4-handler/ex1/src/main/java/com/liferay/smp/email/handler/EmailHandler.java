package com.liferay.smp.email.handler;

import com.liferay.smp.email.model.Email;

public abstract class EmailHandler {

	public void processEmail(Email email) {
		process(email);

		if (successor != null) {
			successor.processEmail(email);
		}
	}

	public void setSuccessor(EmailHandler handler) {
		successor = handler;
	}

	protected abstract void process(Email email);

	protected EmailHandler successor;

}