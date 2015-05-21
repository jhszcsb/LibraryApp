package web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import dal.UserFacade;
import entity.Rental;
import entity.Users;

@Named("userController")
@SessionScoped
public class UserController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Users current;
	
	private Users edit;
	
	// this is probably not needed for the Users, as there is no functionality need for listing all users...
	private DataModel<Users> items = null;
	
	private List<Users> dummy = null;
	
	private boolean editing;
	
	@EJB
	private UserFacade facade;
	
	public Users getCurrent() {
		if (current == null)
			current = new Users();
		return current;
	}

	public void setCurrent(Users current) {
		this.current = current;
	}
	
	public Users getEdit() {
		if (edit == null)
			edit = new Users();
		return edit;
	}

	public void setEdit(Users edit) {
		this.edit = edit;
	}
	
	public DataModel<Users> getItems() {
		if (items == null)
			items = new ListDataModel<Users>(facade.findAll());
		return items;
	}
	
	public void setDummy(List<Users> dummy) {
		this.dummy = dummy;
	}
	
	public List<Users> getDummy() {
		dummy = new ArrayList<Users>();
		Users user1 = new Users();
		user1.setName("Csabi");
		dummy.add(user1);
		return dummy;
	}
	
	public String test() {
		return "Test";
	}
	
	public void setItems(DataModel<Users> items) {
		this.items = items;
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}
	
	public String save() {
		facade.create(current);
		FacesUtil.addInfoMessage("Registration successfully completed");
		current = null;
		return "login.xhtml";
	}
	
	public void changePassword() {
		setCurrent(facade.getLoggedInUser());
		current.setPassword(edit.getPassword());
		facade.edit(current);
		FacesUtil.addInfoMessage("Password changed!");
	}
	
	public void deleteUser(Users item) {
		facade.remove(item);
	}

}
