package web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import model.BookSearchForm;
import dal.BookFacade;
import entity.Book;

@Named("bookController")
@SessionScoped
public class BookController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private DataModel<Book> items = null;
	
	private String searchField;
	
	@EJB
	private BookFacade facade;
	
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

}
