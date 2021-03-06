package web;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entity.Users;

@Named
@RequestScoped
public class SecurityBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public String getUserName() {
		ExternalContext ectx = FacesContext.getCurrentInstance()
				.getExternalContext();
		return ((ectx == null) || (ectx.getUserPrincipal() == null)) ? null
				: ectx.getUserPrincipal().getName();
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return FacesUtil.pageWithRedirect("login.xhtml");
	}
	
	public boolean isAdmin() {
		ExternalContext ectx = FacesContext.getCurrentInstance()
				.getExternalContext();
		return ectx.isUserInRole(Users.ADMIN);
	}
	
	public boolean isCustomer() {
		ExternalContext ectx = FacesContext.getCurrentInstance()
				.getExternalContext();
		return ectx.isUserInRole(Users.CUSTOMER);
	}
}
