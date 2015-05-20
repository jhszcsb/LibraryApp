package web;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

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
		newRental.setReturndate(new Date());
		newRental.setStatus(Status.REQUESTED.getValue());
		newRental.setBook(book);
		Users currentUser = userFacade.getLoggedInUser();
		newRental.setUser(currentUser);
		facade.create(newRental);
	}
	
	public boolean isNotRented(int id) {
		// TODO: implement -> does the current user have an ongoing rental for this book?
		/*SecurityBean sec = new SecurityBean();
		DataModel<Rental> list = new ListDataModel<Rental>(facade.findForCustomer(sec.getUserName()));
		boolean contains = false;
		String reuestedRentalTitle = facade.find(id).getBook().getTitle();
		for(Rental r : list) {
			if(r.getBook().getTitle().equalsIgnoreCase(reuestedRentalTitle)) {
				contains = true;
			}
		}
		if(contains == true) {
			return false;
		}*/
		return true;
	}
	
	public void changeStatusToReceivable(Rental item) {
		item.setStatus(Status.RECEIVABLE.getValue());
		facade.edit(item);
	}
	
	public void changeStatusToRented(Rental item) {
		item.setStatus(Status.RENTED.getValue());
		facade.edit(item);
	}
	
	public void changeStatusToReturned(Rental item) {
		item.setStatus(Status.RETURNED.getValue());
		facade.edit(item);
	}
	
	public void changeStatusToExpired(Rental item) {
		item.setStatus(Status.EXPIRED.getValue());
		facade.edit(item);
	}
	
	public void changeStatusToRejected(Rental item) {
		item.setStatus(Status.REJECTED.getValue());
		facade.edit(item);
	}
	
	public boolean isRequested(Rental item) {
		return item.getStatus().equals("REQUESTED");	// TODO: get the status from enum
	}
	
	public boolean isReceivable(Rental item) {
		return item.getStatus().equals("RECEIVABLE");
	}
	
	public boolean isRented(Rental item) {
		return item.getStatus().equals("RENTED");
	}
	
	public void deleteRental(Rental item) {
		facade.delete(item);							// TODO: implement
	}

}
