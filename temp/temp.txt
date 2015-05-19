package service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import dal.BookFacade;
import entity.Book;

@Stateless
@WebService
public class BookWebService {
	
	private List<Book> books;
	
	@EJB
	private BookFacade facade;
	
	public BookWebService() {
		books = facade.findAll();
	}
	
	@WebMethod
	public List<Book> getBooks() {
		return books;
	}

}
