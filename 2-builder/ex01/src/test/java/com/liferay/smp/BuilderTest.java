package com.liferay.smp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.liferay.smp.event.model.Event;
import com.liferay.smp.event.scheduler.EventBuilder;
import com.liferay.smp.event.scheduler.Scheduler;
import com.liferay.smp.event.when.model.EventTime;
import com.liferay.smp.event.when.model.MonthlyEvent;
import com.liferay.smp.event.when.model.Reoccurrence;
import com.liferay.smp.event.when.model.ReoccurringEvent;
import com.liferay.smp.event.when.model.impl.OnceEventImpl;
import com.liferay.smp.event.where.model.MeetingRoom;
import com.liferay.smp.event.where.model.OnlineConference;
import com.liferay.smp.event.where.model.PhoneConference;
import com.liferay.smp.event.who.model.Participant;
import com.liferay.smp.event.who.model.User;
import com.liferay.smp.service.MeetingRoomSchedulingService;
import com.liferay.smp.service.PhoneConferenceService;
import com.liferay.smp.service.WebExMeetingService;

@ContextConfiguration(loader = SpringockitoContextLoader.class, locations = "classpath:spring.xml")
public class BuilderTest extends AbstractJUnit4SpringContextTests {

	@ReplaceWithMock
	@Autowired
	private MeetingRoomSchedulingService meetingRoomSchedulingService;
	@ReplaceWithMock
	@Autowired
	private PhoneConferenceService phoneConferenceService;
	@ReplaceWithMock
	@Autowired
	private WebExMeetingService webExMeetingService;
	@ReplaceWithMock
	@Autowired
	private MeetingRoom meetingRoom;
	@ReplaceWithMock
	@Autowired
	private PhoneConference phoneConference;
	@ReplaceWithMock
	@Autowired
	private OnlineConference onlineConference;
	private Date startDate;
	private Date endDate;

	@Before
	public void setUp() {
		when(meetingRoomSchedulingService.bookARoom(
			(EventTime) anyObject(), anyInt())).thenReturn(meetingRoom);
		when(phoneConferenceService.createNewConference(
			(EventTime) anyObject())).thenReturn(phoneConference);
		when(webExMeetingService.createNewConference(
			(EventTime) anyObject())).thenReturn(onlineConference);

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2015);
		cal.set(Calendar.MONTH, Calendar.AUGUST);
		cal.set(Calendar.DAY_OF_MONTH, 17);
		cal.set(Calendar.HOUR, 9);
		cal.set(Calendar.MINUTE, 0);

		startDate = cal.getTime();

		cal.set(Calendar.MINUTE, 30);

		endDate = cal.getTime();
	}

	@Test
	@Ignore
	public void testBasicSetup() throws Exception {
		Object object = applicationContext.getBean("scheduler");
		assertNotNull(object);
	}

	@Test
	@Ignore
	public void testOnceWebExEvent() throws Exception {

		String name = "Demo over WebEx";
		String description = "a quick demo for our new features.";
		Scheduler scheduler =
			(Scheduler) applicationContext.getBean("scheduler");
		EventBuilder onceWebExtBuilder =
			(EventBuilder) applicationContext.getBean("onceWebExBuilder");
		Event event = scheduler.constructEvent(
			name, description, startDate, endDate, getWebExInviters(),
			getWebExInvitees(), onceWebExtBuilder);

		assertNotNull(event);

		// Test WHEN
		EventTime eventTime = event.getEventTime();
		assertNotNull(eventTime);
		assertEquals("Name is not set correctly.", name, event.getName());
		assertEquals("Description is not set correctly.",
			description, event.getDescription());
		assertFalse("EventTime should NOT be an instance of ReoccurringEvent",
			eventTime instanceof ReoccurringEvent);
		assertTrue("EventTime is not an instance of OnceEventImpl.",
			eventTime instanceof OnceEventImpl);
		assertEquals("EventTime should be of type ONCE.", eventTime.getType(), EventTime.Type.ONCE);
		assertFalse("Event should not be all day.", eventTime.isAllDay());
		assertEquals(eventTime.getStartDate(), startDate);
		assertEquals(eventTime.getEndDate(), endDate);

		// Test WHO
		List<Participant> inviters = event.getInviters();
		assertEquals("There should be exactly 2 inviters.", inviters.size(), 2);
		Participant inviter = inviters.get(0);
		assertNotNull("The inviter is null.", inviter);
		assertFalse("The inviter should NOT be optional.", inviter.isOptional());
		assertTrue("Reminders should be sent to inviters.", inviter.isSendReminder());
		List<Participant> invitees = event.getInvitees();
		assertEquals("There should be exactly 2 invitees.", invitees.size(), 2);
		Participant invitee = invitees.get(0);
		assertNotNull("At least one of the invitees is null", invitee);
		assertFalse("The invitees' participation should NOT be optional.", invitee.isOptional());
		assertTrue("Reminders should be sent to participants.", invitee.isSendReminder());

		verify(webExMeetingService, times(1)).createNewConference((EventTime) anyObject());
	}

	@Test
	@Ignore
	public void testDailyScrumEvent() throws Exception {

		String name = "Daily IT Scrum";
		String description = "A daily SCRUM MEETING for IT Team";
		Scheduler scheduler =
			(Scheduler) applicationContext.getBean("scheduler");
		EventBuilder dailyScrumBuilder =
			(EventBuilder) applicationContext.getBean("dailyScrumBuilder");
		Event event = scheduler.constructEvent(
			name, description, startDate, null, getSCRUMInviter(),
			getSCRUMInvitees(), dailyScrumBuilder);

		assertNotNull(event);

		// Test WHEN
		EventTime eventTime = event.getEventTime();
		assertNotNull(eventTime);
		assertEquals("Name is not set correctly.", name, event.getName());
		assertEquals("Description is not set correctly.",
			description, event.getDescription());
		assertTrue("EventTime is not an instance of ReoccurringEvent",
			eventTime instanceof ReoccurringEvent);
		assertFalse("Event should not be all day.", eventTime.isAllDay());
		ReoccurringEvent reoccurringEvent = (ReoccurringEvent) eventTime;
		Reoccurrence reoccurrence = reoccurringEvent.getReoccurrence();
		assertEquals(reoccurrence.getType(), Reoccurrence.Type.EVERY_WEEKDAY);
		assertEquals(reoccurrence.getFrequency(), 1);
		assertEquals(eventTime.getStartDate(), startDate);
		assertFalse("This Reoccurring Event should NOT have an end.", reoccurringEvent.hasEnd());

		// Test WHO
		List<Participant> inviters = event.getInviters();
		assertEquals("There should only be 1 inviter.", inviters.size(), 1);
		Participant inviter = inviters.get(0);
		assertNotNull("The inviter is null.", inviter);
		assertFalse("The inviter should NOT be optional.", inviter.isOptional());
		assertTrue("Reminders should be sent to inviter.", inviter.isSendReminder());
		List<Participant> invitees = event.getInvitees();
		assertEquals("There should be exactly 2 invitees.", invitees.size(), 2);
		Participant invitee = invitees.get(0);
		assertNotNull("At least one of the invitees is null", invitee);
		assertFalse("The invitees' participation should NOT be optional.", invitee.isOptional());
		assertTrue("Reminders should be sent to participants.", invitee.isSendReminder());

		// Test WHERE
		verify(phoneConferenceService, atLeastOnce()).createNewConference((EventTime) anyObject());
		assertNotNull(event.getLocation());
	}

	@Test
	@Ignore
	public void testAllHandsEvent() throws Exception {

		String name = "Quarterly All-Hands";
		String description = "Our company-wide meeting.";
		Scheduler scheduler =
			(Scheduler) applicationContext.getBean("scheduler");
		EventBuilder allHandsBuilder =
			(EventBuilder) applicationContext.getBean("allHandsBuilder");
		Event event = scheduler.constructEvent(
			name, description, startDate, null, getAllHandsInviter(),
			getAllHandsInvitees(), allHandsBuilder);

		assertNotNull(event);

		// Test WHEN
		EventTime eventTime = event.getEventTime();
		assertNotNull(eventTime);
		assertEquals("Name is not set correctly.", name, event.getName());
		assertEquals("Description is not set correctly.",
			description, event.getDescription());
		assertTrue("EventTime is not an instance of ReoccurringEvent",
			eventTime instanceof ReoccurringEvent);
		assertTrue("EventTime is not an instance of MonthlyEvent",
			eventTime instanceof MonthlyEvent);
		assertFalse("Event should not be all day.", eventTime.isAllDay());
		ReoccurringEvent reoccurringEvent = (ReoccurringEvent) eventTime;
		Reoccurrence reoccurrence = reoccurringEvent.getReoccurrence();
		assertEquals(reoccurrence.getType(), Reoccurrence.Type.MONTHLY);
		assertEquals(reoccurrence.getFrequency(), 3);
		assertEquals(eventTime.getStartDate(), startDate);
		assertFalse(reoccurringEvent.hasEnd());

		// Test WHO
		List<Participant> inviters = event.getInviters();
		assertEquals("There should only be 1 inviter.", inviters.size(), 1);
		Participant inviter = inviters.get(0);
		assertNotNull("The inviter is null.", inviter);
		assertFalse("The inviter should NOT be optional.", inviter.isOptional());
		assertTrue("Reminders should be sent to inviter.", inviter.isSendReminder());
		List<Participant> invitees = event.getInvitees();
		assertEquals("There should be exactly 10 invitees.", invitees.size(), 10);
		Participant invitee = invitees.get(0);
		assertNotNull("At least one of the invitees is null", invitee);
		assertTrue("The invitees' participation should be optional.", invitee.isOptional());
		assertTrue("Reminders should be sent to participants.", invitee.isSendReminder());

		// Test WHERE
		verify(meetingRoomSchedulingService, atLeastOnce()).bookARoom((EventTime) anyObject(), eq(11)); 
		assertNotNull(event.getLocation());
	}

	protected List<User> getWebExInvitees() {
		User user1 = new User();
		user1.setUserId(12L);
		user1.setEmailAddress("peter@customer.com");
		user1.setFirstName("Peter");
		user1.setLastName("Russell");

		User user2 = new User();
		user2.setUserId(13L);
		user2.setEmailAddress("albert@customer.com");
		user2.setFirstName("Albert");
		user2.setLastName("McCleod");

		List<User> invitees = new ArrayList<User>();
		invitees.add(user1);
		invitees.add(user2);
		
		return invitees;
	}

	protected List<User> getWebExInviters() {
		User user1 = new User();
		user1.setUserId(10L);
		user1.setEmailAddress("jerry@liferay.com");
		user1.setFirstName("Jerry");
		user1.setLastName("Niu");

		User user2 = new User();
		user2.setUserId(11L);
		user2.setEmailAddress("jeff@liferay.com");
		user2.setFirstName("Jeff");
		user2.setLastName("Young");

		List<User> inviters = new ArrayList<User>();
		inviters.add(user1);
		inviters.add(user2);

		return inviters;
	}

	protected List<User> getSCRUMInvitees() {
		User user1 = new User();
		user1.setUserId(2L);
		user1.setEmailAddress("jane.doe@liferay.com");
		user1.setFirstName("Jane");
		user1.setLastName("Doe");

		User user2 = new User();
		user2.setUserId(3L);
		user2.setEmailAddress("john.doe@liferay.com");
		user2.setFirstName("John");
		user2.setLastName("Doe");

		List<User> invitees = new ArrayList<User>();
		invitees.add(user1);
		invitees.add(user2);

		return invitees;
	}

	protected List<User> getSCRUMInviter() {
		User user = new User();
		user.setUserId(111L);
		user.setEmailAddress("joe.shmoe@liferay.com");
		user.setFirstName("Joe");
		user.setLastName("Shmoe");

		List<User> inviters = new ArrayList<User>();
		inviters.add(user);

		return inviters;
	}

	protected List<User> getAllHandsInvitees() {
		User user = null;
		List<User> invitees = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {
			user = new User();
			user.setUserId(10 + i);
			user.setEmailAddress(i + "a@liferay.com");
			user.setFirstName("User" + i);
			user.setLastName("Last" + i);
			invitees.add(user);
		}

		return invitees;
	}

	protected List<User> getAllHandsInviter() {
		User user = new User();
		user.setUserId(1L);
		user.setEmailAddress("bryan@liferay.com");
		user.setFirstName("Bryan");
		user.setLastName("C");

		List<User> inviters = new ArrayList<User>();
		inviters.add(user);

		return inviters;
	}
}