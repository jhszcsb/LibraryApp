package dal;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Book;
import entity.Users;

@Stateless
@LocalBean
@DeclareRoles({ Users.CUSTOMER, Users.ADMIN })
public class BookFacade extends AbstractFacade<Book> {
	
	@PersistenceContext
	private EntityManager em;
	
	public BookFacade() {
		super(Book.class);
	}
	
	@Override
	protected EntityManager em() {
		return em;
	}
	
	/* ROLE: 
		
		Konyvet listazni mindenki tud
		Konyvet letrehozni, modositani, torolni csak ADMIN tud
	
	*/
	
	@Override
	@RolesAllowed({Users.CUSTOMER, Users.ADMIN})
	public List<Book> findAll() {
		return super.findAll();
	}

}
