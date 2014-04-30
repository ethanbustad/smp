package com.liferay.smp.audit;

import com.liferay.smp.IOException;
import com.liferay.smp.io.PrintWriter;
import com.liferay.smp.io.PrintWriterImpl;

import org.springframework.beans.factory.annotation.Autowired;

public class LogFileStore implements AuditStore {

	public void store(AuditContext ctx) {
		try {
			printWriter.open(LOG_FILE);

			StringBuilder sb = new StringBuilder();

			sb.append(ctx.getDate());
			sb.append(SPACE);
			sb.append(DASH);
			sb.append(SPACE);
			sb.append(PAREN_OPEN);
			sb.append(ctx.getLevel());
			sb.append(PAREN_CLOSE);
			sb.append(SPACE);
			sb.append(ctx.getType());
			sb.append(COLON);
			sb.append(SPACE);
			sb.append(ctx.getMessage());

			printWriter.println(sb.toString());

			printWriter.close();
		}
		catch (IOException ioe) {
			throw new Error("An unexpected error occurred.", ioe);
		}
	}

	public void setPrintWriter(PrintWriter pw) {
		printWriter = pw;
	}

	private PrintWriter printWriter;

	private static final String COLON = ":";
	private static final String DASH = "-";
	private static final String LOG_FILE = "audit.log";
	private static final String PAREN_CLOSE = ")";
	private static final String PAREN_OPEN = "(";
	private static final String SPACE = " ";

}