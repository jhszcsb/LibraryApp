package web;

import java.io.Serializable;

import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "navigationController")
@RequestScoped
public class NavigationController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public String registration() {
		return "register";
	}
	
	public String myRentals() {
		return "myRentals";
	}
	
	public String loginPage() {
		return "login";
	}
	
	public String findBooks() {
		return "findBooks";
	}
	
	public String administration() {
		return "administration";
	}
	
	public String profile() {
		return "profile";
	}
	
	public String about() {
		return "about";
	}
	

}
