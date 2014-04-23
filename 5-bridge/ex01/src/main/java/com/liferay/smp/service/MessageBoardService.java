package com.liferay.smp.service;

public interface MessageBoardService {

	public void addThread(long categoryId, String title, String message);
	public void addMessage(long threadId, String title, String message);
	public void deleteThread(long threadId);
	public void deleteMessage(long messageId);
}