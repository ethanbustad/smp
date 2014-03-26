package com.liferay.smp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.liferay.smp.legacy.WarehouseLegacySystem;
import com.liferay.smp.model.Product;
import com.liferay.smp.persistence.ProductPersistence;
import com.liferay.smp.persistence.SecretProductPersistence;
import com.liferay.smp.persistence.db.DataSource;
import com.liferay.smp.security.auth.UserThreadLocal;
import com.liferay.smp.service.ProductService;

@ContextConfiguration(loader = SpringockitoContextLoader.class, locations = "classpath:spring.xml")
public class ProxyTest extends AbstractJUnit4SpringContextTests {

	@ReplaceWithMock
	@Autowired
	private DataSource dataSource;
	@ReplaceWithMock
	@Autowired
	private WarehouseLegacySystem warehouseLegacySystem;
	@ReplaceWithMock
	@Autowired
	private SecretProductPersistence secretProductPersistence;
	@Autowired
	private ProductService productService;

	long productId1 = 123987324L;
	long productId2 = 43209L;
	long regUserId = 32821L;
	long adminUserId = 12345L;

	@Before
	public void setUp() {
		when(dataSource.getData(ProductPersistence.PRODUCT_QUERY + productId1)).thenReturn(
			new String[]{"Liferay in Action", "A book on Liferay Portal", "978-1935182825", "44.99"});
		when(dataSource.getData(ProductPersistence.PRODUCT_QUERY + productId2)).thenReturn(
			new String[]{"Portlet in Action", "A book on Portals in General", "978-1935182542", "49.99"});

		when(warehouseLegacySystem.getProductInventory(anyLong())).thenReturn(34L);
	}

	@Test
	public void testBasicSetup() throws Exception {
		assertNotNull(applicationContext);
	}

	@Test(timeout = 2000)
	@Ignore
	public void testVirtualProxy() throws Exception {
		Product product = productService.getProduct(productId1);
		assertNotNull(product);
		verify(dataSource).getData(ProductPersistence.PRODUCT_QUERY + productId1);
		verify(warehouseLegacySystem, never()).getProductInventory(productId1);
	}

	@Test
	@Ignore
	public void testProductQuantityRetrieval() throws Exception {
		Product product = productService.getProduct(productId2);
		product.getQuantityLeft();
		assertNotNull(product);
		verify(dataSource, times(2)).getData(ProductPersistence.PRODUCT_QUERY + productId2);
		verify(warehouseLegacySystem).getProductInventory(productId2);
	}

	@Test
	@Ignore
	public void testSecretProductExclusion() throws Exception {
		UserThreadLocal.setUserId(regUserId);
		List<Product> secretProducts = productService.getSecretProducts();
		assertNull(secretProducts);
		verify(secretProductPersistence, never()).findAll();
	}

	@Test
	@Ignore
	public void testSecretProductPermission() throws Exception {
		UserThreadLocal.setUserId(adminUserId);
		List<Product> secretProducts = productService.getSecretProducts();
		assertNotNull(secretProducts);
		verify(secretProductPersistence).findAll();
	}
}