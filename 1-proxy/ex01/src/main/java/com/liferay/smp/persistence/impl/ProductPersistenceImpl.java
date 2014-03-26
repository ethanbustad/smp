package com.liferay.smp.persistence.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.liferay.smp.SystemException;
import com.liferay.smp.legacy.WarehouseLegacySystem;
import com.liferay.smp.model.Product;
import com.liferay.smp.model.ProductImpl;
import com.liferay.smp.model.ProductProxy;
import com.liferay.smp.persistence.ProductPersistence;
import com.liferay.smp.persistence.db.DataSource;

public class ProductPersistenceImpl implements ProductPersistence {

	@Autowired
	protected DataSource dataSource;
	@Autowired
	protected WarehouseLegacySystem warehouseLegacySystem;

	@Override
	public Product findProductById(long productId) throws SystemException {
		return findProductById(productId, false);
	}

	@Override
	public Product findProductById(long productId, boolean loadExternal)
		throws SystemException {

		// Query for product info from local database;
		Product product = getLocalProductInfo(productId, loadExternal);

		if (loadExternal) {
			product.setQuantityLeft(countProductById(productId));
		}

		return product;
	}

	/**
	 * Do NOT modify this method.
	 */
	private long countProductById(long productId) throws SystemException {

		log.debug("Warehouse Legacy System is being accessed.  Should take about 4 seconds to finish ...");

		try {
			// simulate external legacy system delay
			Thread.sleep(4000);
		}
		catch(Exception e) {
			log.debug("Error encountered during sleep.");
		}

		// Query remote Warehouse Legacy System to get inventory information
		return warehouseLegacySystem.getProductInventory(productId);
	}

	private Product getLocalProductInfo(long productId, boolean loadExternal)
		throws SystemException {

		Product product = null;

		String[] results = dataSource.getData(PRODUCT_QUERY + productId);
		if (results.length >= 4) {
			if (loadExternal) {
				product = new ProductImpl(productId);
			}
			else {
				product = new ProductProxy(productId);
			}

			product.setName(results[0]);
			product.setDescription(results[1]);
			product.setModelNumber(results[2]);
			product.setPrice(results[3] != null ? Double.parseDouble(results[3]) : 0.0d);
		}
		return product;
	}

	private static Logger log = LoggerFactory.getLogger(ProductPersistenceImpl.class);
}