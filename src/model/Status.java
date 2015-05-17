package model;

public enum Status {
	
	REQUESTED("REQUESTED", "Request sent."),
	REJECTED("REJECTED", "The request has been rejected."),
	RECEIVABLE("RECEIVABLE", "The requested book is receivable."),
	RENTED("RENTED", "The requested book has been rented by the user."),
	EXPIRED("EXPIRED", "The book rental date expired."),
	RETURNED("RETURNED", "The book has been returned by the user");
	
	String value;
	
	String info;
	
	Status(String value, String info) {
		this.value = value;
		this.info = info;
	}
	
	public String getValue() {
		return value;
	}
}

/* 
VALUES OF STATUS:
-REQUESTED (after rental request sent)
-REJECTED (after the administrator set it to rejected)
-RECEIVABLE (after the admninistrator set it to receivable)
-RENTED (after the user received the book and administrator set it to rented)
-EXPIRED (after the return date has expired)
-RETURNED (after the book has been returned and the administrator set it to returned)
*/