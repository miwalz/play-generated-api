package genapi.controller;

import genapi.dao.TestDao;
import genapi.model.Test;
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


public class TestController extends Controller {
	
	@Inject
	FormFactory formFactory;
	
	@Inject
	private TestDao testDao;
	
	public Result createTest() {
		final Test test = formFactory.form(Test.class).bindFromRequest().get();
		test.setId(new ObjectId().toString());
		testDao.save(test);
		return ok(toJson(test));
	}
	
	public Result getTest(String id) {
		final Test test = testDao.get(id);
		return ok(toJson(test));
	}
	
	public Result getAllTest() {
		QueryResults<Test> queryResults = testDao.find();
		return ok(Json.toJson(queryResults.asList()));
	}
	
	public Result updateTest(String id) {
		final Test test = formFactory.form(Test.class).bindFromRequest().get();
		if (!testDao.exists(id)) {
			return notFound();
		}
		test.setId(id);
		testDao.save(test);
		return ok(toJson(test));
	}
	
	public Result deleteTest(String id) {
		testDao.deleteById(id);
		return ok(Json.toJson(id));
	}
	
}
