package genapi.controller;

import genapi.dao.PetDao;
import genapi.model.Pet;
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

import genapi.model.Color;
import genapi.dao.ColorDao;

public class PetController extends Controller {
	
	@Inject
	FormFactory formFactory;
	
	@Inject
	private PetDao petDao;
	
	@Inject 
	private ColorDao colorDao;
	
	public Result createPet() {
		final Pet pet = formFactory.form(Pet.class).bindFromRequest().get();
		pet.setId(new ObjectId().toString());
		petDao.save(pet);
		return ok(toJson(pet));
	}
	
	public Result getPet(String id) {
		final Pet pet = petDao.get(id);
		return ok(toJson(pet));
	}
	
	public Result getAllPet() {
		QueryResults<Pet> queryResults = petDao.find();
		return ok(Json.toJson(queryResults.asList()));
	}
	
	public Result updatePet(String id) {
		final Pet pet = formFactory.form(Pet.class).bindFromRequest().get();
		if (!petDao.exists(id)) {
			return notFound();
		}
		pet.setId(id);
		petDao.save(pet);
		return ok(toJson(pet));
	}
	
	public Result deletePet(String id) {
		petDao.deleteById(id);
		return ok(Json.toJson(id));
	}
	
	public Result addToColors(String petId, String colorsId) {
		final Color entityToAdd = colorDao.get(colorsId);
		final Query<Pet> query = DBWrapper.datastore.createQuery(Pet.class).filter("_id ==", petId);
		final UpdateOperations<Pet> operations = DBWrapper.datastore.createUpdateOperations(Pet.class).add("colors", entityToAdd);
		DBWrapper.datastore.update(query, operations);
		return ok(Json.toJson(entityToAdd));
	}
	
	public Result removeFromColors(String petId, String colorsId) {
		final Color entityToRemove = colorDao.get(colorsId);
		final Query<Pet> query = DBWrapper.datastore.createQuery(Pet.class).filter("_id ==", petId);
		final UpdateOperations<Pet> operations = DBWrapper.datastore.createUpdateOperations(Pet.class).removeAll("colors", entityToRemove);
		DBWrapper.datastore.update(query, operations);
		return ok(Json.toJson(colorsId));
	}
	
	public Result addToColorsEmb(String petId, String colorsEmbId) {
		final Color entityToAdd = colorDao.get(colorsEmbId);
		final Query<Pet> query = DBWrapper.datastore.createQuery(Pet.class).filter("_id ==", petId);
		final UpdateOperations<Pet> operations = DBWrapper.datastore.createUpdateOperations(Pet.class).add("colorsEmb", entityToAdd);
		DBWrapper.datastore.update(query, operations);
		return ok(Json.toJson(entityToAdd));
	}
	
	public Result removeFromColorsEmb(String petId, String colorsEmbId) {
		final Color entityToRemove = colorDao.get(colorsEmbId);
		final Query<Pet> query = DBWrapper.datastore.createQuery(Pet.class).filter("_id ==", petId);
		final UpdateOperations<Pet> operations = DBWrapper.datastore.createUpdateOperations(Pet.class).removeAll("colorsEmb", entityToRemove);
		DBWrapper.datastore.update(query, operations);
		return ok(Json.toJson(colorsEmbId));
	}
	
}
