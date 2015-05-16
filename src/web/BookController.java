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
	
	private DataModel<Book> items = null;
	
	@EJB
	private BookFacade facade;
	
	public DataModel<Book> getItems() {
		if (items == null)
			items = new ListDataModel<Book>(facade.findAll());
		return items;
	}
	
	public void setItems(DataModel<Book> items) {
		this.items = items;
	}

}
