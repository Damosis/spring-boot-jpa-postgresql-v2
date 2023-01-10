package com.tutoriel.springbootjpapostgresql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutoriel.springbootjpapostgresql.models.Book;
import com.tutoriel.springbootjpapostgresql.models.Category;
import com.tutoriel.springbootjpapostgresql.service.BookService;


@RestController
@RequestMapping(path = "/api/books")
public class BookController {

	
	@Autowired
	private BookService bookService;
	
	@GetMapping(path = "/")
	public ResponseEntity<List<Book>>  getBooks(){
		
		try {
			List<Book> books =  bookService.getBooks();
			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path = "/")
	public ResponseEntity<Book>  saveBook(@RequestBody Book book) {
		try {
			return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Book> getBookByID(@PathVariable Long id) {
		try {
			Book _book = bookService.getBookByID(id);
			if(_book != null) {
				return new ResponseEntity<>(_book, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(path = "/category")
	public ResponseEntity<List<Book>> getBooksByCategory(@RequestBody Category category){
		try {
			return new ResponseEntity<List<Book>>(bookService.getBooksByCategory(category),HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Book>  updateBook(@PathVariable Long id,@RequestBody Book book) {
		try {
			return new ResponseEntity<Book>(bookService.updateBook(id, book), HttpStatus.OK) ;
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping(path = "/{id}")
	 public void deleteBookByID(@PathVariable Long id) {
		  bookService.deleteByID(id);
	 }
	 
	@DeleteMapping(path = "/")
	 public void deleteBooks() {
		 bookService.deleteBooks();
	 }
}
