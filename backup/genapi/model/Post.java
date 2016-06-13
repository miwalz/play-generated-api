package genapi.model;

import org.mongodb.morphia.annotations.Entity;

import core.model.BasicModel;

@Entity("post")
public class Post extends BasicModel {

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}

/*

DSL:

MorphiaModel Post {
	String text
}

*/