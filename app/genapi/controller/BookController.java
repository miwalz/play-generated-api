package genapi.controller;

import genapi.dao.BookDao;
import genapi.model.Book;
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


public class BookController extends Controller {
	
	@Inject
	FormFactory formFactory;
	
	@Inject
	private BookDao bookDao;
	
	public Result createBook() {
		final Book book = formFactory.form(Book.class).bindFromRequest().get();
		book.setId(new ObjectId().toString());
		bookDao.save(book);
		return ok(toJson(book));
	}
	
	public Result getBook(String id) {
		final Book book = bookDao.get(id);
		return ok(toJson(book));
	}
	
	public Result getAllBook() {
		QueryResults<Book> queryResults = bookDao.find();
		return ok(Json.toJson(queryResults.asList()));
	}
	
	public Result updateBook(String id) {
		final Book book = formFactory.form(Book.class).bindFromRequest().get();
		if (!bookDao.exists(id)) {
			return notFound();
		}
		book.setId(id);
		bookDao.save(book);
		return ok(toJson(book));
	}
	
	public Result deleteBook(String id) {
		bookDao.deleteById(id);
		return ok(Json.toJson(id));
	}
	
}
