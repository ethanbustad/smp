package com.liferay.smp.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.liferay.smp.model.Product;

public class ProductServiceUtil {

	private static ProductService _productService;

	@Autowired(required = true)
	private ProductServiceUtil(ProductService productService) {
		_productService = productService;
	}

	private static ProductService getService() {
		return _productService;
	}

	public static Product getProduct(long productId) {
		return getService().getProduct(productId);
	}

	public static Product getProduct(long productId, boolean loadExternal) {
		return getService().getProduct(productId, loadExternal);
	}

}