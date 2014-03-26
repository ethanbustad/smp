package com.liferay.smp.persistence;

import java.util.List;

import com.liferay.smp.model.Product;

public interface SecretProductPersistence {

	public List<Product> findAll();
}