package genapi.model;

import org.mongodb.morphia.annotations.Entity;

import core.model.BasicModel;

@Entity("user")
public class User extends BasicModel {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
