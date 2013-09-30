package com.nosql.mongodb;


import java.net.UnknownHostException;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.Mongo;

public class MongodbTest {
	public static void main(String[] args) throws UnknownHostException {
		Mongo mongo = new Mongo("localhost", 27017 );
		DB db = mongo.getDB( "local" );
		Set<String> colls = db.getCollectionNames();
		for (String s : colls) {
		    System.out.println(s);
		}
	}
}
