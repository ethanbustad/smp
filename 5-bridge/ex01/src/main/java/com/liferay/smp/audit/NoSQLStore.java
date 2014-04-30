package com.liferay.smp.audit;

import com.liferay.smp.bigdata.DB;
import com.liferay.smp.bigdata.DBCollection;
import com.liferay.smp.bigdata.DBObject;
import com.liferay.smp.bigdata.DBObjectFactory;
import com.liferay.smp.bigdata.Mongo;
import com.liferay.smp.MongoException;

public class NoSQLStore implements AuditStore {

	public void store(AuditContext ctx) {
		try {
			DB db = mongo.getDB("liferay");

			DBCollection dbCollection = db.getCollection("audit");

			DBObject dbObject = dbObjectFactory.createDBObject();

			dbObject.put("date", ctx.getDate());
			dbObject.put("level", ctx.getLevel());
			dbObject.put("message", ctx.getMessage());
			dbObject.put("type", ctx.getType());
			dbObject.put("userId", ctx.getUserId());

			dbCollection.insert(dbObject);

			mongo.close();
		}
		catch (MongoException me) {
			throw new Error("An unexpected error occurred.", me);
		}
	}

	public void setDBObjectFactory(DBObjectFactory dbObjectFactory) {
		this.dbObjectFactory = dbObjectFactory;
	}

	public void setMongo(Mongo mongo) {
		this.mongo = mongo;
	}

	private DBObjectFactory dbObjectFactory;
	private Mongo mongo;

}