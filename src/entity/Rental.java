package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Rental implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue
	private int rentalid;
	
	//bi-directional many-to-one association to Book
	@ManyToOne
	@JoinColumn(name="BOOKID")
	private Book book;
	
	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USERNAME")
	private Users users;
	
	private Date rentaldate;
	
	private Date returndate;
	
	private String status;

	public Date getRentaldate() {
		return rentaldate;
	}

	public void setRentaldate(Date rentaldate) {
		this.rentaldate = rentaldate;
	}

	public Date getReturndate() {
		return returndate;
	}

	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public void setUser(Users user) {
		this.users = user;
	}
	
	public Book getBook() {
		return book;
	}
	
	public Users getUser() {
		return users;
	}

}
