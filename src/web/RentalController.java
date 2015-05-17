package web;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import dal.RentalFacade;
import entity.Book;
import entity.Rental;
import entity.Users;
import model.Status;

@Named("rentalController")
@SessionScoped
public class RentalController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private DataModel<Rental> items = null;
	
	@EJB
	private RentalFacade facade;
	
	public DataModel<Rental> getItems() {
		// TODO: implement -> query the rentals for the current user
		if (items == null)
			items = new ListDataModel<Rental>(facade.findAll());
		return items;
	}
	
	public void setItems(DataModel<Rental> items) {
		this.items = items;
	}
	
	public void createRental(Book book) {
		//SecurityBean sec = new SecurityBean();
		Rental newRental = new Rental();
		newRental.setRentaldate(new Date());
		newRental.setReturndate(new Date());
		newRental.setStatus(Status.REQUESTED.getValue());
		newRental.setBook(book);
		//newRental.setUser(sec.getUserName());
		Users u = new Users();	// TODO: implement -> get the current user and set it for the rental
		u.setName("Csabi");
		newRental.setUser(u);
		facade.create(newRental);
	}
	
	public boolean isNotRented(int id) {
		// TODO: implement -> does the current user have an ongoing rental for this book?
		return true;
	}

}
