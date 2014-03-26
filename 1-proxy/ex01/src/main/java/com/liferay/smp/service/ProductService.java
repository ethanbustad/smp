package com.liferay.smp.service;

import java.util.List;

import com.liferay.smp.model.Product;

public interface ProductService {

	public Product getProduct(long productId);
	public Product getProduct(long productId, boolean loadExternal);
	public List<Product> getSecretProducts();
}