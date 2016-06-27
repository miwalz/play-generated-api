package genapi.model;

import org.mongodb.morphia.annotations.Entity;
import core.model.BasicModel;
import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;

@Entity("book")
public class Book extends BasicModel {
	
	private String title;
	
	public String getTitle() {
	    return this.title;
	}
	
	public void setTitle(String title) {
	    this.title = title;
	}
		
	private String isbn;
	
	public String getIsbn() {
	    return this.isbn;
	}
	
	public void setIsbn(String isbn) {
	    this.isbn = isbn;
	}
		
	private int pages;
	
	public int getPages() {
	    return this.pages;
	}
	
	public void setPages(int pages) {
	    this.pages = pages;
	}
		
}
