package genapi.controller;

import genapi.dao.ArticleDao;
import genapi.model.Article;
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

import genapi.model.Author;
import genapi.dao.AuthorDao;

public class ArticleController extends Controller {
	
	@Inject
	FormFactory formFactory;
	
	@Inject
	private ArticleDao articleDao;
	
	@Inject 
	private AuthorDao authorDao;
	
	public Result createArticle() {
		final Article article = formFactory.form(Article.class).bindFromRequest().get();
		article.setId(new ObjectId().toString());
		articleDao.save(article);
		return ok(toJson(article));
	}
	
	public Result getArticle(String id) {
		final Article article = articleDao.get(id);
		return ok(toJson(article));
	}
	
	public Result getAllArticle() {
		QueryResults<Article> queryResults = articleDao.find();
		return ok(Json.toJson(queryResults.asList()));
	}
	
	public Result updateArticle(String id) {
		final Article article = formFactory.form(Article.class).bindFromRequest().get();
		if (!articleDao.exists(id)) {
			return notFound();
		}
		article.setId(id);
		articleDao.save(article);
		return ok(toJson(article));
	}
	
	public Result deleteArticle(String id) {
		articleDao.deleteById(id);
		return ok(Json.toJson(id));
	}
	
	public Result addToAuthors(String articleId, String authorsId) {
		final Author entityToAdd = authorDao.get(authorsId);
		final Query<Article> query = DBWrapper.datastore.createQuery(Article.class).filter("_id ==", articleId);
		final UpdateOperations<Article> operations = DBWrapper.datastore.createUpdateOperations(Article.class).add("authors", entityToAdd);
		DBWrapper.datastore.update(query, operations);
		return ok(Json.toJson(entityToAdd));
	}
	
	public Result removeFromAuthors(String articleId, String authorsId) {
		final Author entityToRemove = authorDao.get(authorsId);
		final Query<Article> query = DBWrapper.datastore.createQuery(Article.class).filter("_id ==", articleId);
		final UpdateOperations<Article> operations = DBWrapper.datastore.createUpdateOperations(Article.class).removeAll("authors", entityToRemove);
		DBWrapper.datastore.update(query, operations);
		return ok(Json.toJson(authorsId));
	}
	
}
