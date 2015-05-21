package web;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import business.AdministrationBean;
import dal.BookFacade;
import dal.RentalFacade;
import dal.UserFacade;
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
	
	@EJB
	private UserFacade userFacade;
	
	@EJB
	private BookFacade bookFacade;
	
	@EJB
	AdministrationBean administrationBean;
	
	public DataModel<Rental> getItems() {
		SecurityBean sec = new SecurityBean();
		if (items == null){
			if(sec.isCustomer()) {
				items = new ListDataModel<Rental>(facade.findForCustomer(sec.getUserName()));
			}
			else {
				items = new ListDataModel<Rental>(facade.findAll());
			}
		}
		return items;
	}
	
	public void setItems(DataModel<Rental> items) {
		this.items = items;
	}
	
	public void createRental(Book book) {
		if (book.getAvailablecopies() <= 0) {
			FacesUtil.addErrorMessage("Request can not be created! Reason: no copies available.");
			return;
		}
		Rental newRental = new Rental();
		newRental.setRentaldate(new Date());
		Date returnDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(returnDate);
		cal.add(Calendar.MONTH, 1);
		newRental.setReturndate(cal.getTime());
		newRental.setStatus(Status.REQUESTED.getValue());
		newRental.setBook(book);
		Users currentUser = userFacade.getLoggedInUser();
		newRental.setUser(currentUser);
		facade.create(newRental);
	}
	
	public boolean isNotRented(int id) {
		return true;
		// TODO: implement -> does the current user have an ongoing rental for this book?
		/*SecurityBean sec = new SecurityBean();
		DataModel<Rental> listOfCurrentCustomersRentals = new ListDataModel<Rental>(facade.findForCustomer(sec.getUserName()));
		boolean contains = false;
		System.out.println(id);
		Book bookToBeChecked = null;
		BookFacade bookFacade = new BookFacade();
		bookToBeChecked = bookFacade.find(id);
		System.out.println(bookToBeChecked.getTitle());*/
		/*
		for(Rental r : listOfCurrentCustomersRentals) {
			if(r.getBook().getTitle().equalsIgnoreCase(titleOfRequestedRental)) {
				contains = true;
			}
		}
		if(contains == true) {
			return false;
		}
		return true;*/
	}
	
	public void changeStatusToReceivable(Rental item) {
		administrationBean.changeStatusToReceivable(item);
	}
	
	public void changeStatusToRented(Rental item) {
		administrationBean.changeStatusToRented(item);
	}
	
	public void changeStatusToReturned(Rental item) {
		administrationBean.changeStatusToReturned(item);
	}
	
	public void changeStatusToExpired(Rental item) {
		administrationBean.changeStatusToExpired(item);
	}
	
	public void changeStatusToRejected(Rental item) {
		administrationBean.changeStatusToRejected(item);
	}
	
	public boolean isRequested(Rental item) {
		return item.getStatus().equals(Status.REQUESTED.getValue());
	}
	
	public boolean isReceivable(Rental item) {
		return item.getStatus().equals(Status.RECEIVABLE.getValue());
	}
	
	public boolean isRented(Rental item) {
		return item.getStatus().equals(Status.RENTED.getValue());
	}
	
	public void deleteRental(Rental item) {
		facade.delete(item);
	}
	
	public void reload() {
		SecurityBean sec = new SecurityBean();
		if (items == null){
			if(sec.isCustomer()) {
				items = new ListDataModel<Rental>(facade.findForCustomer(sec.getUserName()));
			}
			else {
				items = new ListDataModel<Rental>(facade.findAll());
			}
		}
	}

}
