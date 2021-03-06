package entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String CUSTOMER = "CUSTOMER";
	public static final String ADMIN = "ADMIN";

	@GeneratedValue(strategy = GenerationType.TABLE)
	private int userid;

	@Id
	private String name;

	private String password;
	
	private String firstname;
	
	private String lastname;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> roles = new HashSet<>();
	
	//bi-directional many-to-one association to Rental
	@OneToMany(mappedBy="users")
	private List<Rental> rentals;
	
	public Users() {
		roles.add(CUSTOMER);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Set<String> getRoles() {
		return Collections.unmodifiableSet(roles);
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public boolean isAdmin() {
		return roles.contains(ADMIN);
	}
	
	public void setAdmin(boolean val) {
		if (val)
			roles.add(ADMIN);
		else
			roles.remove(ADMIN);
	}
	
	public boolean hasRole(String role) {
		return roles.contains(role);
	}

	public void addRole(String role) {
		roles.add(role);
	}

	public void removeRole(String role) {
		roles.remove(role);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
