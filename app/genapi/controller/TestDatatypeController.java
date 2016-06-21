package genapi.controller;

import genapi.dao.TestDatatypeDao;
import genapi.model.TestDatatype;
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


public class TestDatatypeController extends Controller {
	
	@Inject
	FormFactory formFactory;
	
	@Inject
	private TestDatatypeDao testDatatypeDao;
	
	public Result createTestDatatype() {
		final TestDatatype testDatatype = formFactory.form(TestDatatype.class).bindFromRequest().get();
		testDatatype.setId(new ObjectId().toString());
		testDatatypeDao.save(testDatatype);
		return ok(toJson(testDatatype));
	}
	
	public Result getTestDatatype(String id) {
		final TestDatatype testDatatype = testDatatypeDao.get(id);
		return ok(toJson(testDatatype));
	}
	
	public Result getAllTestDatatype() {
		QueryResults<TestDatatype> queryResults = testDatatypeDao.find();
		return ok(Json.toJson(queryResults.asList()));
	}
	
	public Result updateTestDatatype(String id) {
		final TestDatatype testDatatype = formFactory.form(TestDatatype.class).bindFromRequest().get();
		if (!testDatatypeDao.exists(id)) {
			return notFound();
		}
		testDatatype.setId(id);
		testDatatypeDao.save(testDatatype);
		return ok(toJson(testDatatype));
	}
	
	public Result deleteTestDatatype(String id) {
		testDatatypeDao.deleteById(id);
		return ok(Json.toJson(id));
	}
	
}
