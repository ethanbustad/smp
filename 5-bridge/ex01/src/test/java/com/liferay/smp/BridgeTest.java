package com.liferay.smp;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import com.liferay.smp.service.MessageBoardService;
import com.liferay.smp.service.RemoteSystemService;

public class BridgeTest extends AbstractBridgeTest {

	@Test
	@Ignore
	public void testLogFileStore() throws Exception {
		Date now = new Date();
		exercise1Setup(now);
		RemoteSystemService service =
			(RemoteSystemService) applicationContext.getBean("remoteSystemService");
		service.checkSystemHealth();
		exercise1Verify(now);
	}

	@Test
	@Ignore
	public void testUserActionOnMongo() throws Exception {
		// Setup variables for this test

		String title = "How to Setup IDE?";
		long userId = 45633L;
		Date now = new Date();

		exercise2Setup(userId, title);
		MessageBoardService service =
			(MessageBoardService) applicationContext.getBean("messageBoardService");
		service.addMessage(123L, title, "Just a quick note to see if any of you have the steps.");
		exercise2Verify(userId, title, now);
	}
}