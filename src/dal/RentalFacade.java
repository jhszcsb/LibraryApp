package dal;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Book;
import entity.Rental;
import entity.Users;

@Stateless
@LocalBean
//@DeclareRoles({ Users.CUSTOMER, Users.ADMIN })
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
	
	//@RolesAllowed({Users.CUSTOMER, Users.ADMIN})
	@Override
	public List<Rental> findAll() {
		return super.findAll();
	}

	public List<Rental> findForCustomer(String userName) {
		// TODO: use named query?
		String query = "from Rental r where r.users.name = :userName";
		List<Rental> result = em.createQuery(query).setParameter("userName", userName).getResultList();		
		return result;
	}
	
	public Rental find(Rental rental) {
		return em.find(Rental.class, rental.getId());
	}
	
	public void delete(Rental rental) {
		Rental toBeDeleted = find(rental);
		em.remove(toBeDeleted);
	}

}
