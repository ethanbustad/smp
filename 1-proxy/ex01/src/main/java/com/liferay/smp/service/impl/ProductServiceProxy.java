package com.liferay.smp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.liferay.smp.model.Product;
import com.liferay.smp.service.ProductService;

import com.liferay.smp.security.auth.UserThreadLocal;
import com.liferay.smp.security.PermissionUtil;

public class ProductServiceProxy implements ProductService {

	@Autowired
	private ProductServiceImpl productServiceImpl;

	@Override
	public Product getProduct(long productId) {
		return productServiceImpl.getProduct(productId);
	}

	@Override
	public Product getProduct(long productId, boolean loadExternal) {
		return productServiceImpl.getProduct(productId, loadExternal);
	}

	@Override
	public List<Product> getSecretProducts() {
		long userId = UserThreadLocal.getUserId();

		if (!PermissionUtil.isAdmin(userId)) {
			return null;
		}

		return productServiceImpl.getSecretProducts();
	}
}