package com.liferay.smp;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.regex.Pattern;

import org.junit.Before;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.liferay.smp.audit.AuditContext;
import com.liferay.smp.audit.AuditContextFactory;
import com.liferay.smp.audit.Level;
import com.liferay.smp.audit.Type;
import com.liferay.smp.bigdata.DB;
import com.liferay.smp.bigdata.DBCollection;
import com.liferay.smp.bigdata.DBObject;
import com.liferay.smp.bigdata.DBObjectFactory;
import com.liferay.smp.bigdata.Mongo;
import com.liferay.smp.io.PrintWriter;
import com.liferay.smp.model.Action;
import com.liferay.smp.model.UserAction;
import com.liferay.smp.model.UserActionFactory;
import com.liferay.smp.security.auth.UserThreadLocal;
import com.liferay.smp.util.MessageConstants;

@ContextConfiguration(loader = SpringockitoContextLoader.class, locations = "classpath:spring.xml")
public abstract class AbstractBridgeTest extends AbstractJUnit4SpringContextTests {

	@ReplaceWithMock
	@Autowired
	protected PrintWriter printWriter;
	@ReplaceWithMock
	@Autowired
	protected DBObjectFactory dBObjectFactory;
	@ReplaceWithMock
	@Autowired
	protected Mongo mongo;
	@ReplaceWithMock
	@Autowired
	protected DB dB;
	@ReplaceWithMock
	@Autowired
	protected DBCollection dBCollection;
	@ReplaceWithMock
	@Autowired
	protected DBObject dBObject;
	@ReplaceWithMock
	@Autowired
	protected UserAction userAction;
	@ReplaceWithMock
	@Autowired
	protected UserActionFactory userActionFactory;
	@ReplaceWithMock
	@Autowired
	protected AuditContext ctx;
	@ReplaceWithMock
	@Autowired
	protected AuditContextFactory auditContextFactory;

	@Before
	public void setUp() {
		when(auditContextFactory.createAuditContext()).thenReturn(ctx);
	}

	protected void exercise1Setup(Date date) throws Exception {
		when(ctx.getDate()).thenReturn(date);
		when(ctx.getLevel()).thenReturn(Level.NORMAL);
		when(ctx.getType()).thenReturn(Type.GENERAL);
		when(ctx.getMessage()).thenReturn(MessageConstants.REMOTE_SYSTEM_OK);
	}

	/**
	 * System 
	 * @param ctx
	 * @throws Exception
	 */
	protected void exercise1Verify(Date date) throws Exception {
		verify(auditContextFactory, atLeastOnce()).createAuditContext();
		verify(ctx).setMessage(MessageConstants.REMOTE_SYSTEM_OK);
		verify(ctx).setType(Type.GENERAL);
		verify(ctx).setLevel(Level.NORMAL);
		verify(ctx, atLeastOnce()).setDate((Date) anyObject());

		verify(printWriter).open(anyString());
		verify(printWriter).println(messageReplaceForSystemHealth(
			date, Level.NORMAL, Type.GENERAL, MessageConstants.REMOTE_SYSTEM_OK));
		verify(printWriter).close();
	}

	protected void exercise2Setup(long userId, String title) throws Exception {
		UserThreadLocal.setUserId(userId);

		// setup userAction
		when(userActionFactory.createUserAction()).thenReturn(userAction);
		when(userAction.getActorUserId()).thenReturn(userId);
		when(userAction.isCritical()).thenReturn(false);
		when(userAction.getDate()).thenReturn(new Date());
		when(userAction.getAction()).thenReturn(Action.ADD);
		when(userAction.getObject()).thenReturn(title);

		// setup NoSQL
		when(mongo.getDB("liferay")).thenReturn(dB);
		when(dB.getCollection("audit")).thenReturn(dBCollection);
		when(dBObjectFactory.createDBObject()).thenReturn(dBObject);

		// setup AuditContext
		when(ctx.getUserId()).thenReturn(userId);
		when(ctx.getType()).thenReturn(Type.USER_ACTION);
		when(ctx.getLevel()).thenReturn(Level.LOW);
		when(ctx.getDate()).thenReturn(new Date());
		when(ctx.getEnvironment()).thenReturn(null);
		when(ctx.getMessage()).thenReturn("");
	}

	protected void exercise2Verify(long userId, String title, Date date)
		throws Exception {

		verify(userActionFactory, atLeastOnce()).createUserAction();
		verify(userAction).setActorUserId(userId);
		verify(userAction).setCritical(false);
		verify(userAction).setDate((Date) anyObject());
		verify(userAction).setAction(Action.ADD);
		verify(userAction).setObject(title);

		verify(auditContextFactory, atLeastOnce()).createAuditContext();
		verify(ctx).setUserId(userId);
		verify(ctx).setLevel(Level.LOW);
		verify(ctx).setType(Type.USER_ACTION);
		verify(ctx, atLeastOnce()).setDate((Date) anyObject());
		verify(ctx).setMessage(messageReplaceForUserAction(userId, Action.ADD, title, date));

		verify(dBObjectFactory).createDBObject();
		verify(dBObject, atLeast(5)).put(anyString(), anyObject());
		verify(dBCollection).insert(any(DBObject.class));
		verify(mongo).close();
	}

	private String messageReplaceForUserAction(
			long userId, Action action, String object, Date date) {
		String str = MessageConstants.MB_ADD_AUDIT.replaceAll(Pattern.quote("{USERID}"), String.valueOf(userId));
		str = str.replaceAll(Pattern.quote("{ACTION}"), action.toString());
		str = str.replaceAll(Pattern.quote("{OBJECT}"), object);
		str = str.replaceAll(Pattern.quote("{DATE}"), date.toString());
		log.debug(str);
		return str;
	}

	private String messageReplaceForSystemHealth(
			Date date, Level level, Type type, String message) {
		String str = MessageConstants.RS_HEALTH_AUDIT.replaceAll(Pattern.quote("{DATE}"), date.toString());
		str = str.replaceAll(Pattern.quote("{LEVEL}"), level.toString());
		str = str.replaceAll(Pattern.quote("{TYPE}"), type.toString());
		str = str.replaceAll(Pattern.quote("{MESSAGE}"), message);
		log.debug(str);
		return str;
	}

	private static Logger log = LoggerFactory.getLogger(AbstractBridgeTest.class);
}