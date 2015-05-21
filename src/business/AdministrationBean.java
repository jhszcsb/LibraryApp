package business;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import model.Status;
import dal.BookFacade;
import dal.RentalFacade;
import entity.Book;
import entity.Rental;

@Stateless
public class AdministrationBean {
	
	@EJB
	BookFacade bookFacade;
	
	@EJB
	RentalFacade rentalFacade;
	
	public void changeStatusToReceivable(Rental item) {
		item.setStatus(Status.RECEIVABLE.getValue());
		rentalFacade.edit(item);
	}
	
	public void changeStatusToRented(Rental item) {
		item.setStatus(Status.RENTED.getValue());
		decreaseNumberOfAvailableCopies(item.getBook());
		rentalFacade.edit(item);
	}
	
	public void changeStatusToReturned(Rental item) {
		item.setStatus(Status.RETURNED.getValue());
		increaseNumberOfAvailableCopies(item.getBook());
		rentalFacade.edit(item);
	}
	
	public void changeStatusToExpired(Rental item) {
		item.setStatus(Status.EXPIRED.getValue());
		rentalFacade.edit(item);
	}
	
	public void changeStatusToRejected(Rental item) {
		item.setStatus(Status.REJECTED.getValue());
		rentalFacade.edit(item);
	}

	public void increaseNumberOfAvailableCopies(Book book) {
		book.setAvailablecopies((book.getAvailablecopies() + 1));
		bookFacade.edit(book);
	}
	
	public void decreaseNumberOfAvailableCopies(Book book) {
		if(book.getAvailablecopies() >= 0) {
			book.setAvailablecopies((book.getAvailablecopies() - 1));
			bookFacade.edit(book);
		}
		else {
			// error message
		}
	}
	
}
