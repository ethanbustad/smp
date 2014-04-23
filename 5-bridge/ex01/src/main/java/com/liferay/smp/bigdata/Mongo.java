package com.liferay.smp.bigdata;


public interface Mongo {

	public DB getDB(String database);
	public void close();
}