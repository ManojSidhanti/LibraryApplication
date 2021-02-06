package org.springframework.java.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.java.entity.Book;
import org.springframework.java.exception.BookNameNotFoundException;
import org.springframework.java.services.LibraryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

@RestController
@Validated
@RequestMapping(value="/books")
public class LibraryController {
	
	@Autowired
	private LibraryService libraryservice;
	
	@GetMapping
	public List<Book> getAllBooks(){
		return libraryservice.getAllBooks();
	}
	
	@PostMapping
	public Book newBook(@Valid @RequestBody Book book) {
		return libraryservice.newBook(book);
	}
	
	@GetMapping("/{bookId}")
	public Book getBookById(@PathVariable("bookId") @Min(1) Long Id) {
		
		return libraryservice.getBookById(Id);
		
	}
	
	@PutMapping("/{bookId}")
	public Book updateBookById(@PathVariable("bookId") Long Id,@RequestBody Book book) {
		return libraryservice.updateBookById(Id, book);
	}
	
	@DeleteMapping("/{bookId}")
	public void deleteBookById(@PathVariable("bookId")Long Id) {
		libraryservice.deleteBookById(Id);
	}
	
	@GetMapping("/bybookname/{bookname}")
	public Book getBookByBookname(@PathVariable("bookname")String Bookname) throws BookNameNotFoundException {
		Book book = libraryservice.getBookByBookname(Bookname);
		if(book==null)
			throw new BookNameNotFoundException("bookname:"+ Bookname + "not found in repository" );
		return book;
	}

}
