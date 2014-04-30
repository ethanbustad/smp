package com.liferay.smp.audit;

public interface AuditStore {

	public void store(AuditContext ctx);

}