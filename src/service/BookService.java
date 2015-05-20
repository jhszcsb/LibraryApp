package service;

import java.util.List;

import javax.ejb.EJB;

import dal.BookFacade;
import entity.Book;

public class BookService {
	
	@EJB
	private BookFacade facade;
	
	public List<Book> findAllBooks() {
		return facade.findAll();
	}

}
