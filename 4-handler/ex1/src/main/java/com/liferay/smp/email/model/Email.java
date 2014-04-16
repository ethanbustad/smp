package com.liferay.smp.email.model;

import java.util.ArrayList;
import java.util.List;

public class Email {

	public Email() {
		priority = Priority.NORMAL;
		folder = Folder.INBOX;
	}

	public enum Folder {
		INBOX, ARCHIVE, TRASH, SPAM, SENT, DRAFT
	}

	public enum Priority {
		URGENT, HIGH, NORMAL, LOW
	}

	private List<String> tos;
	private List<String> ccs;
	private List<String> bccs;
	private String from;
	private String subject;
	private String body;
	private Folder folder;
	private Priority priority;

	public List<String> getTos() { return tos; }
	public List<String> getCCs() { return ccs; }
	public List<String> getBCCs() { return bccs; }
	public String getFrom() { return from; }
	public String getSubject() { return subject; }
	public String getBody() { return body; }
	public Folder getFolder() { return folder; }
	public Priority getPriority() { return priority; }

	public void addTo(String emailAddress) {
		if (tos == null) {
			tos = new ArrayList<String>();
		}
		tos.add(emailAddress);
	}

	public void addCC(String emailAddress) {
		if (ccs == null) {
			ccs = new ArrayList<String>();
		}
		ccs.add(emailAddress);
	}

	public void addBCC(String emailAddress) {
		if (bccs == null) {
			bccs = new ArrayList<String>();
		}
		bccs.add(emailAddress);
	}

	public void setFrom(String from) { this.from = from; }
	public void setSubject(String subject) { this.subject = subject; }
	public void setBody(String body) { this.body = body; }
	public void setFolder(Folder folder) { this.folder = folder; }
	public void setPriority(Priority priority) { this.priority = priority; }
}