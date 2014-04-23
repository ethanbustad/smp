package com.liferay.smp.audit;

public interface AuditContextFactory {

	/**
	 * Returns a new 'empty' AuditContext.
	 * Do NOT modify.
	 * @return
	 */
	public AuditContext createAuditContext();
}