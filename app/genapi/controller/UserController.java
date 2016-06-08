package genapi.controller;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;
import org.mongodb.morphia.query.UpdateOperations;

import com.google.inject.Inject;

import core.util.DBWrapper;
import genapi.dao.PostDao;
import genapi.dao.UserDao;
import genapi.model.Post;
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

	@Inject
	private PostDao postDao;

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

	public Result addPost(String userId, String postId) {
		final Post post = postDao.get(postId);
		final Query<User> query = DBWrapper.datastore.createQuery(User.class).filter("_id ==", userId);
		final UpdateOperations<User> operations = DBWrapper.datastore.createUpdateOperations(User.class).add("posts", post);
		DBWrapper.datastore.update(query, operations);
		return ok(Json.toJson(post));
	}
	
	public Result removePost(String userId, String postId) {
		final Post post = postDao.get(postId);
		final Query<User> query = DBWrapper.datastore.createQuery(User.class).filter("_id ==", userId);
		final UpdateOperations<User> operations = DBWrapper.datastore.createUpdateOperations(User.class).removeAll("posts", post);
		DBWrapper.datastore.update(query, operations);
		return ok(Json.toJson(postId));
	}

}
