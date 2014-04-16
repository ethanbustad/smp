package com.liferay.smp.email.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailProperties {

	private static String SPAM_KEYWORDS;
	private static String CLIENT_DOMAINS;

	public void setSpamKeywordsValue(String spamKeywordsValue) {
		log.debug("SPAM keywords initialized.");
		SPAM_KEYWORDS = spamKeywordsValue;
	}

	public void setClientDomainsValue(String clientDomainsValue) {
		log.debug("Client domains initialized.");
		CLIENT_DOMAINS = clientDomainsValue;
	}

	public static String[] getSpamKeywords() {
		if (SPAM_KEYWORDS != null) {
			return SPAM_KEYWORDS.split(",");
		}
		return null;
	}

	public static String[] getClientDomains() {
		if (CLIENT_DOMAINS != null) {
			return CLIENT_DOMAINS.split(",");
		}
		return null;
	}

	private static Logger log = LoggerFactory.getLogger(EmailProperties.class);
}