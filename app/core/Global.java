package core;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import core.util.DBWrapper;
import play.Logger;

@Singleton
public final class Global {

	@Inject
	public Global() {
		Logger.debug("** Starting Application **");
		try {
			DBWrapper.mongo = new MongoClient("127.0.0.1", 27017);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBWrapper.morphia = new Morphia();
		DBWrapper.datastore = DBWrapper.morphia.createDatastore(DBWrapper.mongo, "play-generated-api");
		DBWrapper.datastore.ensureIndexes();
		DBWrapper.datastore.ensureCaps();
	}

}