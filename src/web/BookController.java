package web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import dal.BookFacade;
import entity.Book;

@Named("bookController")
@SessionScoped
public class BookController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Book current;
	
	private DataModel<Book> items = null;
	
	private String searchField;
	
	@EJB
	private BookFacade facade;
	
	public Book getCurrent() {
		if (current == null)
			current = new Book();
		return current;
	}

	public void setCurrent(Book current) {
		this.current = current;
	}
	
	public void find() {
		if (searchField == null || searchField == "") {
			FacesUtil.addInfoMessage("Please enter a value in the search field!");
			items = new ListDataModel<Book>();
		}
		else {
			items = new ListDataModel<Book>(facade.search(searchField));
		}
		//return null;
	}
	
	public DataModel<Book> getItems() {
		if (items == null)
			items = new ListDataModel<Book>(facade.findAll());
		return items;
	}
	
	public void setItems(DataModel<Book> items) {
		this.items = items;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	
	public String save() {
		facade.create(current);
		FacesUtil.addInfoMessage("Book successfully saved");
		current = null;
		return "findBooks.xhtml";
	}

}
