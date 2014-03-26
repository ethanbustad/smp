package com.liferay.smp.persistence;

import com.liferay.smp.SystemException;
import com.liferay.smp.model.Product;

public interface ProductPersistence {
	public static final String PRODUCT_QUERY = "SELECT * FROM PRODUCT WHERE _ID = ";

	public Product findProductById(long productId) throws SystemException;
}