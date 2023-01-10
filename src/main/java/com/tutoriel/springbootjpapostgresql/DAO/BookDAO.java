package com.tutoriel.springbootjpapostgresql.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tutoriel.springbootjpapostgresql.models.Book;
import com.tutoriel.springbootjpapostgresql.models.Category;

@Repository
public interface BookDAO extends CrudRepository<Book, Long> {
	List<Book> findByTitle(String Title);
	List<Book> findByCategory(Category category);
}
