package com.liferay.smp.audit;

public class Auditor {

	public void audit(AuditContext ctx) {
		auditStore.store(ctx);
	}

	public void setAuditStore(AuditStore s) {
		auditStore = s;
	}

	private AuditStore auditStore;

}