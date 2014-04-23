package com.liferay.smp.bigdata;

import com.liferay.smp.MongoException;

public interface DB {

	public boolean authenticate(String login, String password) throws MongoException;
	public DBCollection getCollection(String name);
}