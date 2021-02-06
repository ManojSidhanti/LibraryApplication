package org.springframework.java.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

@Entity
public class Book extends ResourceSupport{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long bookId;
	
	@NotEmpty(message = "Book name is mandatory, please provide book name")
	private String bookname;
	@OneToMany(mappedBy="book")
	private List<Author> authors;
	
	
	
	public List<Author> getAuthors() {
		return authors;
	}


	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}


	public Book() {
		
	}
	
	
	public Book(long id, String bookname) {
		
		bookId = id;
		bookname = bookname;
	}


	public long getBookId() {
		return bookId;
	}
	public void setId(long id) {
		bookId = id;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		bookname = bookname;
	}

}
