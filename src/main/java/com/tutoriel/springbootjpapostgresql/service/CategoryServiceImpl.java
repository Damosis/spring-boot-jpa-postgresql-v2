package com.tutoriel.springbootjpapostgresql.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutoriel.springbootjpapostgresql.DAO.CategoryDAO;
import com.tutoriel.springbootjpapostgresql.models.Category;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDAO categoryDAO;

	@Override
	public List<Category> getCategories() {
		
		List<Category> categories = (List<Category>) categoryDAO.findAll();
		if(!categories.isEmpty()) {
			return categories;
		}
		return null;
	}

	@Override
	public Category getCategoryByCode(Long code) {
		// TODO Auto-generated method stub
		Optional<Category> category = categoryDAO.findById(code);
		if(category.isPresent()) {
			return category.get();
		}
		return null;
	}

	@Override
	public Category saveCategory(Category category) {
		return categoryDAO.save(new Category(category.getName()));
	}

	@Override
	public void deleteCategoryByCode(Long code) {
		// TODO Auto-generated method stub
		categoryDAO.deleteById(code);
		
	}

}
