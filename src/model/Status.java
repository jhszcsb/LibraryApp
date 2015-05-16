package model;

public enum Status {
	
	REQUESTED("Request sent."),
	REJECTED("The request has been rejected."),
	RECEIVABLE("The requested book is receivable."),
	RENTED("The requested book has been rented by the user."),
	EXPIRED("The book rental date expired."),
	RETURNED("The book has been returned by the user");
	
	String info;
	
	Status(String info) {
		this.info = info;
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