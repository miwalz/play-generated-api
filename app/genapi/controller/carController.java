package genapi.controller;

import genapi.dao.carDao;
import genapi.model.car;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.QueryResults;
import com.google.inject.Inject;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.FormFactory;
import play.libs.Json;
import static play.libs.Json.toJson;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import core.util.DBWrapper;


public class carController extends Controller {
	
	@Inject
	FormFactory formFactory;
	
	@Inject
	private carDao carDao;
	
	public Result createcar() {
		final car car = formFactory.form(car.class).bindFromRequest().get();
		car.setId(new ObjectId().toString());
		carDao.save(car);
		return ok(toJson(car));
	}
	
	public Result getcar(String id) {
		final car car = carDao.get(id);
		return ok(toJson(car));
	}
	
	public Result getAllcar() {
		QueryResults<car> queryResults = carDao.find();
		return ok(Json.toJson(queryResults.asList()));
	}
	
	public Result updatecar(String id) {
		final car car = formFactory.form(car.class).bindFromRequest().get();
		if (!carDao.exists(id)) {
			return notFound();
		}
		car.setId(id);
		carDao.save(car);
		return ok(toJson(car));
	}
	
	public Result deletecar(String id) {
		carDao.deleteById(id);
		return ok(Json.toJson(id));
	}
	
}
