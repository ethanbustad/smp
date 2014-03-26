package com.liferay.smp.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import com.liferay.smp.model.Product;
import com.liferay.smp.persistence.SecretProductPersistence;

public class SecretProductPersistenceImpl implements SecretProductPersistence {

	@Override
	public List<Product> findAll() {
		return new ArrayList<Product>();
	}
}