package com.liferay.smp.service.impl;

import com.liferay.smp.audit.UserActionAuditor;
import com.liferay.smp.model.Action;
import com.liferay.smp.model.UserAction;
import com.liferay.smp.model.UserActionFactory;
import com.liferay.smp.service.MessageBoardService;
import com.liferay.smp.security.auth.UserThreadLocal;

import java.util.Date;

public class MessageBoardServiceImpl implements MessageBoardService {

	/**
	 * Please add a call at the end of this method to audit the user action.
	 */
	@Override
	public void addThread(long categoryId, String title, String message) {
		/*
		 * Pretend logic to add new thread is already done in this
		 * comment space.
		 */

		sendAudit(Action.ADD, title);
	}

	/**
	 * Please add a call at the end of this method to audit the user action.
	 */
	@Override
	public void addMessage(long threadId, String title, String message) {
		/*
		 * Pretend logic to add new message is already done in this
		 * comment space.
		 */

		sendAudit(Action.ADD, title);
	}

	/**
	 * Please add a call at the end of this method to audit the user action.
	 */
	@Override
	public void deleteThread(long threadId) {
		/*
		 * Pretend logic to delete thread is already done in this
		 * comment space.
		 */

		sendAudit(Action.DELETE, null);
	}

	/**
	 * Please add a call at the end of this method to audit the user action.
	 */
	@Override
	public void deleteMessage(long messageId) {
		/*
		 * Pretend logic to delete message is already done in this
		 * comment space.
		 */

		sendAudit(Action.DELETE, null);
	}

	public void setUserActionAuditor(UserActionAuditor uaa) {
		userActionAuditor = uaa;
	}

	public void setUserActionFactory(UserActionFactory uaf) {
		userActionFactory = uaf;
	}

	protected void sendAudit(Action action, String title) {
		UserAction userAction = userActionFactory.createUserAction();

		userAction.setAction(action);
		userAction.setActorUserId(UserThreadLocal.getUserId());
		userAction.setCritical(false);
		userAction.setDate(new Date());
		userAction.setObject(title);

		userActionAuditor.actionAudit(userAction);
	}

	private UserActionAuditor userActionAuditor;
	private UserActionFactory userActionFactory;
}