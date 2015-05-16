package test;

import javax.ejb.EJB;

import dal.UserFacade;
import entity.Users;

public class Main {
	
	@EJB
	private static UserFacade userFacade;

	public static void main(String[] args) {
		
		Users newUser = new Users();
		//newUser.setAdmin(true);
		newUser.setName("Test User");
		newUser.setPassword("dummyPassword");
		//newUser.setRoles(new Set<String>("CUSTOMER_ROLE"));
		
		userFacade.create(newUser);
		
		System.out.println("Testing EJBs...");
		System.out.println(userFacade.findAll());
	}

}