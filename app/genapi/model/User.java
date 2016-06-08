package genapi.model;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import core.model.BasicModel;

@Entity("user")
public class User extends BasicModel {

	private String name;
	
	@Reference(ignoreMissing = true) // or: @Embedded
    public List<Post> posts = new ArrayList<Post>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
