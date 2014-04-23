package com.liferay.smp.model;

import java.util.Date;

public interface UserAction {

	public static final long NULL_USER = -1;

	public long getActorUserId();
	public String getObject();
	public Action getAction();
	public Date getDate();
	public boolean isCritical();

	public void setActorUserId(long userId);
	public void setObject(String object);
	public void setAction(Action action);
	public void setDate(Date date);
	public void setCritical(boolean critical);
}