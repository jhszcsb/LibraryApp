package dal;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Users;

@Stateless
@LocalBean
//@DeclareRoles({ Users.CUSTOMER, Users.ADMIN })
public class UserFacade extends AbstractFacade<Users> {
	
	@PersistenceContext
	private EntityManager em;
	
	public UserFacade() {
		super(Users.class);
	}
	
	@Override
	protected EntityManager em() {
		return em;
	}
	
	/* ROLE: 
		
		TODO: megcsinalni
		
		User-t listazni, letrehozni mindenki tud
		User-t modositani, torolni csak ADMIN tud

	 */
	
	//@RolesAllowed({Users.CUSTOMER, Users.ADMIN})
	@Override
	public Users find(Object id) {
		return super.find(id);
	}

	//@RolesAllowed({Users.CUSTOMER, Users.ADMIN})
	@Override
	public List<Users> findAll() {
		return super.findAll();
	}

	//@RolesAllowed({Users.CUSTOMER_ROLE, Users.ADMIN_ROLE})
	@Override
	public List<Users> findRange(int[] range) {
		return super.findRange(range);
	}

	//@RolesAllowed({Users.CUSTOMER_ROLE, Users.ADMIN_ROLE})
	@Override
	public int count() {
		return super.count();
	}

	@Override
	//@RolesAllowed({Users.ADMIN_ROLE})
	public void create(Users entity) {
		super.create(entity);
	}

	@Override
	//@RolesAllowed({Users.ADMIN_ROLE})
	public void edit(Users entity) {
		super.edit(entity);
	}

	@Override
	//@RolesAllowed({Users.ADMIN_ROLE})
	public void remove(Users entity) {
		super.remove(entity);
	}

}
