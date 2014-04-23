package com.liferay.smp.bigdata;

public class MongoClient implements Mongo {

	@Override
	public DB getDB(String database) {
		return null;
	}

	@Override
	public void close() {
	}
}