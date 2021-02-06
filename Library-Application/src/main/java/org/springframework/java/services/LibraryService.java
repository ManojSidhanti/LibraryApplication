package org.springframework.java.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.java.entity.Book;
import org.springframework.java.exception.BookNameNotFoundException;
import org.springframework.java.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class LibraryService  {
	
	@Autowired
	private BookRepository bookrepository;
	
	public List<Book> getAllBooks(){
		return bookrepository.findAll();
		
	}
	
	public Book newBook(Book book) {
		return bookrepository.save(book);
	}
	
	public Book getBookById(Long id) {
		Book book = bookrepository.findOne(id);
		
		return book;
	}
	
	public Book updateBookById(Long Id, Book book) {
		book.setId(Id);
		return bookrepository.save(book);
	}
	
	public void deleteBookById(Long Id) {
		bookrepository.delete(Id);
	}
	
	public Book getBookByBookname(String bookname) {
		return bookrepository.findByBookname(bookname);
	}

}
