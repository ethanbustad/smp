package com.liferay.smp;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.mockito.InOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.liferay.smp.model.Order;
import com.liferay.smp.service.AccountingService;
import com.liferay.smp.service.CustomerNotificationService;
import com.liferay.smp.service.WarehouseService;

@ContextConfiguration(loader = SpringockitoContextLoader.class, locations = "classpath:spring.xml")
public class ObserverTest extends AbstractJUnit4SpringContextTests {

	@ReplaceWithMock
	@Autowired
	private WarehouseService warehouseService;
	@ReplaceWithMock
	@Autowired
	private CustomerNotificationService customerNotificationService;
	@ReplaceWithMock
	@Autowired
	private AccountingService accountingService;

	private Order order;

	@Before
	public void setUp() {
		order = (Order) applicationContext.getBean("order");
	}

	@Test
	public void testBaseSpringWiring() throws Exception {
		assertNotNull(applicationContext);
		assertNotNull(warehouseService);
		assertNotNull(order);
	}

	@Test
	public void testBasicWarehouseService() throws Exception {
		order.confirm();
		verify(warehouseService).fulfillOrder(order);
	}

	@Test
	public void testCustomerNotification() throws Exception {
		order.confirm();
		verify(warehouseService).fulfillOrder(order);
		verify(customerNotificationService).sendOrderNotification(order);
	}

	@Test
	public void testAccountingService() throws Exception {
		order.confirm();
		verify(warehouseService).fulfillOrder(order);
		verify(customerNotificationService).sendOrderNotification(order);
		verify(accountingService).processPayment(order);
		verify(accountingService, never()).refundCustomer(order);
	}

	@Test
	public void testOrderedCall() throws Exception {
		order.confirm();
		InOrder inOrder = inOrder(accountingService, customerNotificationService, warehouseService);
		inOrder.verify(accountingService).processPayment(order);
		inOrder.verify(customerNotificationService).sendOrderNotification(order);
		inOrder.verify(warehouseService).fulfillOrder(order);
		verify(accountingService, never()).refundCustomer(order);
	}
}