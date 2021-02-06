package org.springframework.java.controller;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.java.entity.Author;
import org.springframework.java.entity.Book;
import org.springframework.java.exception.BookNameNotFoundException;
import org.springframework.java.repositories.BookRepository;
import org.springframework.java.services.LibraryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/hateoas/books")
@Validated
public class BookHateoasController {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private LibraryService libraryService;
	
	@GetMapping
	public Resources<Book> getAllBooks() throws BookNameNotFoundException{
		List<Book> allbooks = libraryService.getAllBooks();
		for(Book book:allbooks) {
			Long bookid = book.getBookId();
			Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(bookid).withSelfRel();
			book.add(selfLink);
			
			//Relationship link with getAllAuthors
			Resources<Author> authors = ControllerLinkBuilder.methodOn(AuthorHateoasController.class).getAllAuthors(bookid);
			Link authorLink = ControllerLinkBuilder.linkTo(authors).withRel("all-authors");
			book.add(authorLink);
		}
		
		//self link for getallBooks
		
		Link selflinkgetAllBooks = ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel();
		Resources<Book> finalResources = new Resources<Book>(allbooks,selflinkgetAllBooks);
		return finalResources;
	}
	
	@GetMapping("/{bookId}")
	public Resource<Book> getBookById(@PathVariable("/bookId") @Min(1) long bookId) {
		Book book = libraryService.getBookById(bookId);
		Long bookid = book.getBookId();
		Link selflink = ControllerLinkBuilder.linkTo(this.getClass()).slash(bookid).withSelfRel();
		book.add(selflink);
		Resource<Book> finalResource = new Resource<Book>(book);
		return finalResource;
	}
}
