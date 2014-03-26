package com.liferay.smp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.liferay.smp.SystemException;
import com.liferay.smp.model.Product;
import com.liferay.smp.persistence.ProductPersistence;
import com.liferay.smp.persistence.SecretProductPersistence;
import com.liferay.smp.service.ProductService;

public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductPersistence productPersistence;
	@Autowired
	private SecretProductPersistence secretProductPersistence;

	@Override
	public Product getProduct(long productId) {
		try {
			return productPersistence.findProductById(productId);
		}
		catch (SystemException se) {
			// handle exception
		}
		return null;
	}

	@Override
	public Product getProduct(long productId, boolean loadExternal) {
		try {
			return productPersistence.findProductById(productId, loadExternal);
		}
		catch (SystemException se) {
			// handle exception
		}
		return null;
	}

	@Override
	public List<Product> getSecretProducts() {
		return secretProductPersistence.findAll();
	}
}