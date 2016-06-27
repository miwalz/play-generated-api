package genapi.controller;

import genapi.dao.userDao;
import genapi.model.user;
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


public class userController extends Controller {
	
	@Inject
	FormFactory formFactory;
	
	@Inject
	private userDao userDao;
	
	public Result createuser() {
		final user user = formFactory.form(user.class).bindFromRequest().get();
		user.setId(new ObjectId().toString());
		userDao.save(user);
		return ok(toJson(user));
	}
	
	public Result getuser(String id) {
		final user user = userDao.get(id);
		return ok(toJson(user));
	}
	
	public Result getAlluser() {
		QueryResults<user> queryResults = userDao.find();
		return ok(Json.toJson(queryResults.asList()));
	}
	
	public Result updateuser(String id) {
		final user user = formFactory.form(user.class).bindFromRequest().get();
		if (!userDao.exists(id)) {
			return notFound();
		}
		user.setId(id);
		userDao.save(user);
		return ok(toJson(user));
	}
	
	public Result deleteuser(String id) {
		userDao.deleteById(id);
		return ok(Json.toJson(id));
	}
	
}
