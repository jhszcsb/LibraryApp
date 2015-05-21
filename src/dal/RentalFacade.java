package dal;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Rental;
import entity.Users;

@Stateless
@LocalBean
@DeclareRoles({ Users.CUSTOMER, Users.ADMIN })
public class RentalFacade extends AbstractFacade<Rental> {
	
	@PersistenceContext
	private EntityManager em;
	
	public RentalFacade() {
		super(Rental.class);
	}
	
	@Override
	protected EntityManager em() {
		return em;
	}
	
	/* ROLE: 
	
		Rental-t listazni, letrehozni mindenki tud
		Rental-t modositani, torolni csak ADMIN tud

	 */
	
	@RolesAllowed({Users.CUSTOMER, Users.ADMIN})
	@Override
	public List<Rental> findAll() {
		return super.findAll();
	}

	@RolesAllowed({Users.CUSTOMER, Users.ADMIN})
	public List<Rental> findForCustomer(String userName) {
		// use named query?
		String query = "from Rental r where r.users.name = :userName";
		List<Rental> result = em.createQuery(query).setParameter("userName", userName).getResultList();		
		return result;
	}
	
	@RolesAllowed({Users.CUSTOMER, Users.ADMIN})
	public Rental find(Rental rental) {
		return em.find(Rental.class, rental.getId());
	}
	
	@RolesAllowed({Users.CUSTOMER, Users.ADMIN})
	public Rental find(Object id) {
		return super.find(id);
	}
	
	@RolesAllowed({Users.ADMIN})
	public void delete(Rental rental) {
		Rental toBeDeleted = find(rental);
		em.remove(toBeDeleted);
	}

	/*@RolesAllowed({Users.CUSTOMER, Users.ADMIN})
	public Rental findByBookId(int id) {
		String query = "from Rental r where r.book.bookid = :id";
		Rental result = (Rental) em.createQuery(query).setParameter("id", id).getSingleResult();	
		return result;
	}*/

}
