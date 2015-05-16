package web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import dal.RentalFacade;
import entity.Rental;

@Named("rentalController")
@SessionScoped
public class RentalController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private DataModel<Rental> items = null;
	
	@EJB
	private RentalFacade facade;
	
	public DataModel<Rental> getItems() {
		if (items == null)
			items = new ListDataModel<Rental>(facade.findAll());
		return items;
	}
	
	public void setItems(DataModel<Rental> items) {
		this.items = items;
	}

}
