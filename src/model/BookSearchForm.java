package model;

import javax.annotation.ManagedBean;

//@ManagedBean
public class BookSearchForm {
	
	private String title;
	
	private String author;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	

}
