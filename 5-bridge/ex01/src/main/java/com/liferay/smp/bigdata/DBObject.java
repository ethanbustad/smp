package com.liferay.smp.bigdata;

public interface DBObject {
	public Object get(String key);
	public Object put(String key, Object v);
}