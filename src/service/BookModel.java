package service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookModel {
	
	private String name;
	
	BookModel(){}
	
	BookModel(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
