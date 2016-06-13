package genapi.controller;

import genapi.dao.ColorDao;
import genapi.model.Color;
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


public class ColorController extends Controller {
	
	@Inject
	FormFactory formFactory;
	
	@Inject
	private ColorDao colorDao;
	
	public Result createColor() {
		final Color color = formFactory.form(Color.class).bindFromRequest().get();
		color.setId(new ObjectId().toString());
		colorDao.save(color);
		return ok(toJson(color));
	}
	
	public Result getColor(String id) {
		final Color color = colorDao.get(id);
		return ok(toJson(color));
	}
	
	public Result getAllColor() {
		QueryResults<Color> queryResults = colorDao.find();
		return ok(Json.toJson(queryResults.asList()));
	}
	
	public Result updateColor(String id) {
		final Color color = formFactory.form(Color.class).bindFromRequest().get();
		if (!colorDao.exists(id)) {
			return notFound();
		}
		color.setId(id);
		colorDao.save(color);
		return ok(toJson(color));
	}
	
	public Result deleteColor(String id) {
		colorDao.deleteById(id);
		return ok(Json.toJson(id));
	}
	
}
