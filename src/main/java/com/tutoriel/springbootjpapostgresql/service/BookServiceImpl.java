package com.tutoriel.springbootjpapostgresql.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutoriel.springbootjpapostgresql.DAO.BookDAO;
import com.tutoriel.springbootjpapostgresql.models.Book;
import com.tutoriel.springbootjpapostgresql.models.Category;


@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookDAO bookDAO;

	@Override
	public List<Book> getBooks() {
		
		List<Book> BOOKS = (List<Book>) bookDAO.findAll();
		if(!BOOKS.isEmpty()) {
			return BOOKS;
		}
		else {
			return null;
		}
	}

	@Override
	public Book saveBook(Book book) {
		
		Book _book = new Book();
		_book.setTitle(book.getTitle());
		_book.setAuthor(book.getAuthor());
		_book.setPrice(book.getPrice());
		_book.setCategory(book.getCategory());
		bookDAO.save(_book);
		return _book;
		
//	 Book savedBook = bookDAO.save(new Book(book.getTitle(),book.getAuthor(),book.getPrice()));
//	 return savedBook;
		
	}

	@Override
	public Book getBookByID(Long id) {
		
		Optional<Book> retrievedBook = bookDAO.findById(id);
		if(retrievedBook.isPresent()) {
			return retrievedBook.get();
		}
		else {
			return null;
		}
		
	}

	@Override
	public Book updateBook(Long id, Book book) {
		
		Optional<Book> retrievedBook = bookDAO.findById(id);
		
		Book _book = retrievedBook.get();
		
		_book.setTitle(book.getTitle());
		_book.setAuthor(book.getAuthor());
		_book.setPrice(book.getPrice());
		_book.setCategory(book.getCategory());
		
		bookDAO.save(_book);
		return _book;
	}

	@Override
	public void deleteByID(Long id) {
		bookDAO.deleteById(id);
		
	}

	@Override
	public void deleteBooks() {
		bookDAO.deleteAll();
	}

	@Override
	public List<Book> getBooksByCategory(Category category) {
		// TODO Auto-generated method stub
		List<Book> books = bookDAO.findByCategory(category);
		if(!books.isEmpty()) {
			return books;
		}
		return null;
	}

}
