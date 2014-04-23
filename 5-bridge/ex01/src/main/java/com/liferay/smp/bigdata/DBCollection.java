package com.liferay.smp.bigdata;

import com.liferay.smp.MongoException;

public interface DBCollection {
	public DBObject findOne();
	public void insert(DBObject dbObject) throws MongoException;
}