package dal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
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
	@PermitAll
	public List<Book> findAll() {
		return super.findAll();
	}

	@RolesAllowed({Users.CUSTOMER, Users.ADMIN})
	public List<Book> search(String searchField) {
		// TODO: use named query?
		// TODO: fix query to search parts of words
		String query = "from Book b where b.name like :title or b.author like :author";
		List<Book> result = em.createQuery(query).setParameter("title", searchField).setParameter("author", searchField).getResultList();
		
		if(!result.isEmpty()) {															// debug
			System.out.println(result.get(0).getAuthor() + result.get(0).getName());	// debug
		}																				// debug
		return result;
	}

}
