package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class Book implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int bookid;
	
	// TODO: rename to title
	private String name;
	
	private String author;
	
	@Lob
	private byte[] image;
	
	private String contentType;
	
	private int availablecopies;
	
	//bi-directional many-to-one association to Rental
	@OneToMany(mappedBy="book")
	private List<Rental> rentals;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getAvailablecopies() {
		return availablecopies;
	}

	public void setAvailablecopies(int availablecopies) {
		this.availablecopies = availablecopies;
	}

}
