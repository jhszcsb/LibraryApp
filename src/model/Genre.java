package model;

public enum Genre {
	
    DRAMA("Drama"),
    ROMANCE("Romance"),
    SATIRE("Satire"),
    TRAGEDY("Tragedy"),
    COMEDY("Comedy"),
    TRAGICOMEDY("Tragicomedy"),
    REALISTIC_FICTON("Realistic Fiction"),
    NON_FICTION("Non Fiction");

	String value;
	
	Genre(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

}
