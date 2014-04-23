package com.liferay.smp.audit;

import java.util.Date;

/**
 * Value Object for auditing purposes.
 * Do NOT modify.
 */
public interface AuditContext {

	public String getMessage();
	public String getEnvironment();
	public Date getDate();
	public Level getLevel();
	public Type getType();
	public long getUserId();

	public void setMessage(String message);
	public void setEnvironment(String environment);
	public void setDate(Date date);
	public void setLevel(Level level);
	public void setType(Type type);
	public void setUserId(long userId);
}