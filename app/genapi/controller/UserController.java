package genapi.controller;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.QueryResults;
import com.google.inject.Inject;

import genapi.dao.UserDao;
import genapi.model.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.FormFactory;
import play.libs.Json;
import static play.libs.Json.toJson;

public class UserController extends Controller {

	@Inject
	FormFactory formFactory;

	@Inject
	private UserDao userDao;

	public Result createUser() {
		final User user = formFactory.form(User.class).bindFromRequest().get();
		user.setId(new ObjectId().toString());
		userDao.save(user);
		return ok(toJson(user));
	}

	public Result getUser(String id) {
		final User user = userDao.get(id);
		return ok(toJson(user));
	}

	public Result getAllUser() {
		QueryResults<User> queryResults = userDao.find();
		return ok(Json.toJson(queryResults.asList()));
	}

	public Result updateUser(String id) {
		final User user = formFactory.form(User.class).bindFromRequest().get();
		if (!userDao.exists(id)) {
			return notFound();
		}
		user.setId(id);
		userDao.save(user);
		return ok(toJson(user));
	}

	public Result deleteUser(String id) {
		userDao.deleteById(id);
		return ok(Json.toJson(id));
	}

}
