package com.tutoriel.springbootjpapostgresql.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tutoriel.springbootjpapostgresql.models.Category;

@Repository
public interface CategoryDAO extends CrudRepository<Category, Long> {
}
