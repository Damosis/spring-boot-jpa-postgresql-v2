package com.tutoriel.springbootjpapostgresql.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tutoriel.springbootjpapostgresql.models.Book;
import com.tutoriel.springbootjpapostgresql.service.BookService;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class BookControllerTest {
	
	@InjectMocks
	private BookController bookController = new BookController();
	
	@Mock
	private BookService bookService;
	
	@Test
	public void getBooks_withoutException_Test() {
		
		//Given 
		
		List<Book> list = new ArrayList<>();
		
		Book firstBook = new Book();
		Book secondBook = new Book();
		
		list.add(firstBook);
		list.add(secondBook);
		
		
		//When 
		Mockito.when(bookService.getBooks()).thenReturn(list);
		ResponseEntity<List<Book>> booklist = bookController.getBooks();
		
		//Then
//		assertEquals(2, booklist.getBody().size());
		assertEquals(HttpStatus.OK, booklist.getStatusCode());
		
	}
	
	/*@Test(expected=NullPointerException.class)
	public void getBooks_withException_Test() {
		
//		Mockito.when(bookService.getBooks()).thenReturn(null);
		when(bookService.getBooks()).thenThrow(new NullPointerException("Error occurred"));
		Assert.assertTrue(bookController.getBooks().getStatusCode() ==  HttpStatus.INTERNAL_SERVER_ERROR);
		
	}*/
	
//	public void getBooksByID
	
	@Test
	public void getBookbyID() throws Exception {
		
		//given
		Book book = new Book();
		
		book.setId(2L);
		
		//when
		
		Mockito.when(bookService.getBookByID(2L)).thenReturn(book);
		ResponseEntity<Book> response =  bookController.getBookByID(2L);
		
		//Then
		assertEquals(HttpStatus.OK, response.getStatusCode());	
	}
	
	
	@Test
	public void createBook_WhitoutException() throws Exception {
		
		//given
		Book book = new Book();
		book.setId(3L);
		book.setTitle("We love code");
		book.setAuthor("Gilles H");
		book.setPrice(30);
		
		//When
		Mockito.when(bookService.saveBook(book)).thenReturn(book);
		ResponseEntity<Book> response = bookController.saveBook(book);
		
		//Then
		assertThat(response.getBody().getId()).isGreaterThan(0);
		
	}
	
	@Test
	public void deleteBookByID() throws Exception {
		
		//given 
		Book book = new Book();
		book.setId(3L);
		book.setTitle("We love code");
		book.setAuthor("Gilles H");
		book.setPrice(30);
		
		//When
		bookController.deleteBookByID(30L);
		ResponseEntity<Book> retrievedBook = bookController.getBookByID(book.getId());
		
		//Then
		assertThat(retrievedBook.getBody()).isNull();
	}
	
	@Test
	public void deleteBooks() throws Exception {
		
		Book book = new Book();
		book.setId(3L);
		book.setTitle("We love France");
		book.setAuthor("Gilles H");
		book.setPrice(14);
		
		bookController.deleteBooks();
		ResponseEntity<Book> retrievedBook = bookController.getBookByID(book.getId());
		
		//Then
		assertThat(retrievedBook.getBody()).isNull();
		
		
	}
}
