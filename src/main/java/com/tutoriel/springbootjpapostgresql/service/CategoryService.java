package com.tutoriel.springbootjpapostgresql.service;

import java.util.List;

import com.tutoriel.springbootjpapostgresql.models.Category;

public interface CategoryService {

	List<Category> getCategories();
	
	Category getCategoryByCode(Long code);
	
	Category saveCategory(Category category);
	
	void deleteCategoryByCode(Long code);
}
