package com.liferay.smp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liferay.smp.email.EmailProcessor;
import com.liferay.smp.email.model.Email;
import com.liferay.smp.email.model.Email.Folder;
import com.liferay.smp.email.model.Email.Priority;

/**
 *
 */
@RunWith(JUnit4.class)
public class HandlerTest {

	private ClassPathXmlApplicationContext applicationContext;
	private EmailProcessor processor;

	@Before
	public void setUp() {
		applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		processor = (EmailProcessor) applicationContext.getBean("emailProcessor");
	}

	/*
	 * TODO:
	 * 1. Create an IncomingEmailProcessor that implements the EmailProcessor interface.
	 * 1. Define the EmailHandler interface.
	 */
	@Test
	@Ignore
	public void testBaseSpringWiring() {
		assertNotNull(applicationContext);
		assertNotNull("Error retrieve EmailProcessor. Make sure bean is defined with the correct ID and class.", processor);
	}

	/*
	 * TODO:
	 * 1. Create a handler that places an email in the SPAM folder if it detects
	 *    a phrase "pennies a day" in any case (upper, lower, camel-case, etc.)
	 * 2. However, if the email is marked LOW, HIGH, or URGENT priority, then do nothing.
	 * 3. Wire this handler to an email processor.
	 */
	@Test
	@Ignore
	public void testSpam() {

		Email good = new Email();
		good.addTo("it@lportal.com");
		good.setFrom("hr@lportal.com");
		good.setSubject("VPN no worky");
		good.setBody("Please fix our VPN connection!");

		processor.processEmail(good);

		assertFalse("Email is not spam but is marked spam!", good.getFolder().equals(Email.Folder.SPAM));

		Email spam = new Email();
		spam.addTo("admin@lportal.com");
		spam.setFrom("fskjl@saldkfj.net");
		spam.setSubject("Great Savings!");
		spam.setBody("Take advantage of this great value, just peNnies a dAy!!!  Click on the following link: asdflkasjdf.net/save!");

		processor.processEmail(spam);

		assertTrue("Spam email is not marked spam!", spam.getFolder().equals(Email.Folder.SPAM));

		Email high = new Email();
		high.addTo("joe@lportal.com");
		high.setFrom("paula@gmail.com");
		high.setSubject("how much is EE subscription?");
		high.setBody("pennies a day!");
		high.setPriority(Priority.HIGH);

		processor.processEmail(high);

		assertFalse("Email with HIGH priority is not spam but is marked spam!", high.getFolder().equals(Email.Folder.SPAM));
	}

	/*
	 * TODO:
	 * 1. Create a handler that marks an email as URGENT if it comes from liferay.com
	 * 2. Chain this handler
	 */
	@Test
	@Ignore
	public void testClientPriority() {

		Email normal = new Email();
		normal.addTo("jerry@lportal.com");
		normal.setFrom("tom@gmail.com");
		normal.setSubject("Just saying hello!");
		normal.setBody("how are you doing?  just wanna catch up, cheers, Tom");

		processor.processEmail(normal);

		assertTrue("Email should have NORMAL priority, but it doesn't.", normal.getPriority().equals(Priority.NORMAL));

		Email urgent = new Email();
		urgent.addTo("sam@lportal.com");
		urgent.setFrom("mike@liferay.com");
		urgent.setSubject("Pricing Inquiry");
		urgent.setBody("Just want to check your prices!");

		processor.processEmail(urgent);

		assertTrue("Email should've been marked as URGENT!", urgent.getPriority().equals(Priority.URGENT));
	}

	/*
	 * TODO:
	 * 1. Create a handler that places an email in the ARCHIVE folder if it detects a
	 *    "[JIRA]" in the email subject.
	 * 2. Chain this handler.
	 */
	@Test
	@Ignore
	public void testJIRA() {

		Email nonJIRA = new Email();
		nonJIRA.addTo("james@gmail.com");
		nonJIRA.setFrom("scott@gmail.com");
		nonJIRA.setSubject("Out Of Office");
		nonJIRA.setBody("I'm currently out of the office on vacation.  please contact support for assistance.");

		processor.processEmail(nonJIRA);

		assertTrue("Email should still be in INBOX, but it isn't.", nonJIRA.getFolder().equals(Folder.INBOX));

		Email jira = new Email();
		jira.addTo("amy@gmail.com");
		jira.setFrom("jira@lportal.com");
		jira.setSubject("[JIRA] LPS-123 updated");
		jira.setBody("so and so updated LPS-123.");

		processor.processEmail(jira);

		assertTrue("Email should be in ARCHIVE folder, but it isn't.", jira.getFolder().equals(Folder.ARCHIVE));
	}

	/*
	 * The business realized that some emails from Liferay are going into the SPAM folder.
	 * The reason is that the spam handler is placing them there.
	 *
	 * TODO:
	 * 1. Fix this problem without writing any code.  (hint: only edit the spring.xml)
	 */
	@Test
	@Ignore
	public void testReorderingHandlers() {

		Email client = new Email();
		client.addTo("macy@lportal.com");
		client.setFrom("rachel@liferay.com");
		client.setSubject("An offer you can't refuse!");
		client.setBody("We will sell you EE for just pennies a day!");

		processor.processEmail(client);

		assertFalse("Important email from client went to spam folder!", client.getFolder().equals(Folder.SPAM));
	}
}