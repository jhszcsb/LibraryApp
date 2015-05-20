package service;

import java.util.ArrayList;
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
	
	private List<BookModel> books = null;
	//private List<Book> books = null;
	
	//@EJB
	//private BookFacade facade;
	
	//private BookService service;
	
	public BookWebService() {
		books = new ArrayList<BookModel>();
		books.add(new BookModel("Asdf"));
		
		//service = new BookService();
		//books = service.findAllBooks();
		//books = null;
		//books = facade.findAll();
	}
	
	@WebMethod
	public List<BookModel> getBooks() {
		return books;
	}
	
	/*@WebMethod
	public List<Book> getBooks() {
		return books;
	}*/

}
