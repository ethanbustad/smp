package com.liferay.smp.bigdata;

import com.liferay.smp.MongoException;

public class DBImpl implements DB {

	@Override
	public boolean authenticate(String login, String password)
			throws MongoException {
		return false;
	}

	@Override
	public DBCollection getCollection(String name) {
		return null;
	}
}