package genapi.controller;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.QueryResults;
import com.google.inject.Inject;

import genapi.dao.PostDao;
import genapi.model.Post;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.FormFactory;
import play.libs.Json;
import static play.libs.Json.toJson;

public class PostController extends Controller {

	@Inject
	FormFactory formFactory;

	@Inject
	private PostDao postDao;

	public Result createPost() {
		final Post post = formFactory.form(Post.class).bindFromRequest().get();
		post.setId(new ObjectId().toString());
		postDao.save(post);
		return ok(toJson(post));
	}

	public Result getPost(String id) {
		final Post post = postDao.get(id);
		return ok(toJson(post));
	}

	public Result getAllPost() {
		QueryResults<Post> queryResults = postDao.find();
		return ok(Json.toJson(queryResults.asList()));
	}

	public Result updatePost(String id) {
		final Post post = formFactory.form(Post.class).bindFromRequest().get();
		if (!postDao.exists(id)) {
			return notFound();
		}
		post.setId(id);
		postDao.save(post);
		return ok(toJson(post));
	}

	public Result deletePost(String id) {
		postDao.deleteById(id);
		return ok(Json.toJson(id));
	}

}
