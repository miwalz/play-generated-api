package core.util;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public final class DBWrapper {

	public static Morphia morphia;
	public static MongoClient mongo;
	public static Datastore datastore;
	
}
