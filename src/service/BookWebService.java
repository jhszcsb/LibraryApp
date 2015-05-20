package service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import dal.BookFacade;
import entity.Book;

@Stateless
@WebService
public class BookWebService {
	
	private List<BookModel> books = null;	// for dummy service
	private List<Book> books2 = null;
	
	public BookWebService() {
		books = new ArrayList<BookModel>();	// for dummy service
		books.add(new BookModel("Asdf"));
	}
	
	@PostConstruct
	public void readBooksFromDB() {
		BookFacade facade = new BookFacade();
		List<Book> booksList = new ArrayList<Book>();
		booksList = facade.findAll();
		books2 = booksList;
	}
	
	@WebMethod
	public List<BookModel> getBooks() {		// for dummy service
		return books;
	}
	
	@WebMethod
	public List<Book> getBooks2() {
		return books2;
	}

}
