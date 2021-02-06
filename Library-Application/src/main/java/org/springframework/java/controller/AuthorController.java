package org.springframework.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.java.entity.Author;
import org.springframework.java.entity.Book;
import org.springframework.java.exception.BookNameNotFoundException;
import org.springframework.java.repositories.AuthorRepository;
import org.springframework.java.repositories.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/books")
public class AuthorController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@GetMapping("/{bookId}/authors")
	public List<Author> getAllAuthors(@PathVariable("bookId") Long bookId) throws BookNameNotFoundException {
		Book book = bookRepository.findOne(bookId);
		if(book==null)
			throw new BookNameNotFoundException("Book not found in repository");
		return book.getAuthors();
	}
	
	@PostMapping("/{bookId}/authors")
	public Author createAuthor(@PathVariable("/{bookId}") Long bookId, @RequestBody Author author) throws BookNameNotFoundException  {
		Book book = bookRepository.findOne(bookId);
		if(book==null)
			throw new BookNameNotFoundException("Book not found in repository");
		author.setBook(book);
		return authorRepository.save(author);
		
	}
	
	
	//get author by id
	public Author getAuthorById(@PathVariable("/{bookId}") Long bookId,@PathVariable("/{authorId}") Long authorId) throws BookNameNotFoundException  {
		Book book = bookRepository.findOne(bookId);
		if(book==null)
			throw new BookNameNotFoundException("Book not found in repository");
		return authorRepository.findOne(authorId);
	}
	
	

}
