package genapi.model;

import org.mongodb.morphia.annotations.Entity;
import core.model.BasicModel;
import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;

@Entity("article")
public class Article extends BasicModel {
	
	private String title;
	
	public String getTitle() {
	    return this.title;
	}
	
	public void setTitle(String title) {
	    this.title = title;
	}
		
	@Reference
	private List<Author> authors = new ArrayList<Author>();
	
	public List<Author> getAuthors() {
		return this.authors;
	}
	
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
		
}
