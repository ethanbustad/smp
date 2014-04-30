package com.liferay.smp.audit;

import com.liferay.smp.model.UserAction;

public class UserActionAuditor extends Auditor {

	public void actionAudit(UserAction userAction) {
		AuditContext ctx = auditContextFactory.createAuditContext();

		ctx.setDate(userAction.getDate());

		if (userAction.isCritical()) {
			ctx.setLevel(Level.HIGH);
		}
		else {
			ctx.setLevel(Level.LOW);
		}

		StringBuilder sb = new StringBuilder();

		sb.append(userAction.getActorUserId());
		sb.append(SPACE);
		sb.append(userAction.getAction());
		sb.append(SPACE);
		sb.append(QUOTE);
		sb.append(userAction.getObject());
		sb.append(QUOTE);
		sb.append(" on ");
		sb.append(userAction.getDate());

		ctx.setMessage(sb.toString());

		ctx.setType(Type.USER_ACTION);
		ctx.setUserId(userAction.getActorUserId());

		audit(ctx);
	}

	public void setAuditContextFactory(AuditContextFactory acf) {
		auditContextFactory = acf;
	}

	private AuditContextFactory auditContextFactory;

	private static final String SPACE = " ";
	private static final String QUOTE = "\"";

}

