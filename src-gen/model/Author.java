package genapi.model;

import org.mongodb.morphia.annotations.Entity;
import core.model.BasicModel;
import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;

@Entity("author")
public class Author extends BasicModel {
	
	private String name;
	
	public String getName() {
	    return this.name;
	}
	
	public void setName(String name) {
	    this.name = name;
	}
		
	private boolean married;
	
	public boolean getMarried() {
	    return this.married;
	}
	
	public void setMarried(boolean married) {
	    this.married = married;
	}
		
	@Reference
	private Book favouriteBook;
	
	public Book getFavouriteBook() {
	    return this.favouriteBook;
	}
	
	public void setFavouriteBook(Book favouriteBook) {
	    this.favouriteBook = favouriteBook;
	}
		
	@Reference
	private List<Article> articles = new ArrayList<Article>();
	
	public List<Article> getArticles() {
		return this.articles;
	}
	
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
		
	@Embedded
	private List<Book> books = new ArrayList<Book>();
	
	public List<Book> getBooks() {
		return this.books;
	}
	
	public void setBooks(List<Book> books) {
		this.books = books;
	}
		
	private List<String> aliases = new ArrayList<String>();
	
	public List<String> getAliases() {
		return this.aliases;
	}
	
	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}
		
}
