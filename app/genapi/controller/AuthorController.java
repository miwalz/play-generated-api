package genapi.controller;

import genapi.dao.AuthorDao;
import genapi.model.Author;
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

import genapi.model.Book;
import genapi.dao.BookDao;
import genapi.model.Article;
import genapi.dao.ArticleDao;

public class AuthorController extends Controller {
	
	@Inject
	FormFactory formFactory;
	
	@Inject
	private AuthorDao authorDao;
	
	@Inject 
	private BookDao bookDao;
	
	@Inject 
	private ArticleDao articleDao;
	
	public Result createAuthor() {
		final Author author = formFactory.form(Author.class).bindFromRequest().get();
		author.setId(new ObjectId().toString());
		authorDao.save(author);
		return ok(toJson(author));
	}
	
	public Result getAuthor(String id) {
		final Author author = authorDao.get(id);
		return ok(toJson(author));
	}
	
	public Result getAllAuthor() {
		QueryResults<Author> queryResults = authorDao.find();
		return ok(Json.toJson(queryResults.asList()));
	}
	
	public Result updateAuthor(String id) {
		final Author author = formFactory.form(Author.class).bindFromRequest().get();
		if (!authorDao.exists(id)) {
			return notFound();
		}
		author.setId(id);
		authorDao.save(author);
		return ok(toJson(author));
	}
	
	public Result deleteAuthor(String id) {
		authorDao.deleteById(id);
		return ok(Json.toJson(id));
	}
	
	public Result addToArticles(String authorId, String articlesId) {
		final Article entityToAdd = articleDao.get(articlesId);
		final Query<Author> query = DBWrapper.datastore.createQuery(Author.class).filter("_id ==", authorId);
		final UpdateOperations<Author> operations = DBWrapper.datastore.createUpdateOperations(Author.class).add("articles", entityToAdd);
		DBWrapper.datastore.update(query, operations);
		return ok(Json.toJson(entityToAdd));
	}
	
	public Result removeFromArticles(String authorId, String articlesId) {
		final Article entityToRemove = articleDao.get(articlesId);
		final Query<Author> query = DBWrapper.datastore.createQuery(Author.class).filter("_id ==", authorId);
		final UpdateOperations<Author> operations = DBWrapper.datastore.createUpdateOperations(Author.class).removeAll("articles", entityToRemove);
		DBWrapper.datastore.update(query, operations);
		return ok(Json.toJson(articlesId));
	}
	
	public Result addToBooks(String authorId, String booksId) {
		final Book entityToAdd = bookDao.get(booksId);
		final Query<Author> query = DBWrapper.datastore.createQuery(Author.class).filter("_id ==", authorId);
		final UpdateOperations<Author> operations = DBWrapper.datastore.createUpdateOperations(Author.class).add("books", entityToAdd);
		DBWrapper.datastore.update(query, operations);
		return ok(Json.toJson(entityToAdd));
	}
	
	public Result removeFromBooks(String authorId, String booksId) {
		final Book entityToRemove = bookDao.get(booksId);
		final Query<Author> query = DBWrapper.datastore.createQuery(Author.class).filter("_id ==", authorId);
		final UpdateOperations<Author> operations = DBWrapper.datastore.createUpdateOperations(Author.class).removeAll("books", entityToRemove);
		DBWrapper.datastore.update(query, operations);
		return ok(Json.toJson(booksId));
	}
	
}
