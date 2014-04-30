package com.liferay.smp.audit;

import com.liferay.smp.security.auth.UserThreadLocal;

import java.util.Date;

public class GeneralAuditor extends Auditor {

	public void simpleAudit(String message) {
		AuditContext ctx = auditContextFactory.createAuditContext();

		ctx.setDate(new Date());
		ctx.setLevel(Level.NORMAL);
		ctx.setMessage(message);
		ctx.setType(Type.GENERAL);

		audit(ctx);
	}

	public void setAuditContextFactory(AuditContextFactory acf) {
		auditContextFactory = acf;
	}

	private AuditContextFactory auditContextFactory;

}