package org.springframework.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.java.entity.Author;
import org.springframework.java.entity.Book;
import org.springframework.java.exception.BookNameNotFoundException;
import org.springframework.java.repositories.AuthorRepository;
import org.springframework.java.repositories.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/hateoas/authors")
public class AuthorHateoasController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@GetMapping("/{bookId}/authors")
	public Resources<Author> getAllAuthors(@PathVariable("bookId") Long bookId) throws BookNameNotFoundException {
		Book book = bookRepository.findOne(bookId);
		if(book==null)
			throw new BookNameNotFoundException("Book not found in repository");
		List<Author> allauthors = book.getAuthors();
		Resources<Author> finalResources = new Resources<Author>(allauthors);
		return finalResources;
	}

}
