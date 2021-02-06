package org.springframework.java.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.hateoas.ResourceSupport;

@Entity
public class Author extends ResourceSupport {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long authorid; 
	
	private String authorname;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Book book;

	

	public Author() {
		
		
	}

	public Author(Long authorid, String authorname) {
		
		this.authorid = authorid;
		this.authorname = authorname;
	}

	public Long getAuthorid() {
		return authorid;
	}

	public void setAuthorid(Long authorid) {
		this.authorid = authorid;
	}

	public String getAuthorname() {
		return authorname;
	}

	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	
	
	
	
	

}
