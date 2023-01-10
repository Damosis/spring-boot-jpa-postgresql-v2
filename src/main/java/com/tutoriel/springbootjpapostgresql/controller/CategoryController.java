package com.tutoriel.springbootjpapostgresql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutoriel.springbootjpapostgresql.models.Category;
import com.tutoriel.springbootjpapostgresql.service.CategoryService;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping(path = "/")
	public ResponseEntity<List<Category>> getCategories(){
		try {
			List<Category> categories = categoryService.getCategories();
			return new ResponseEntity<>(categories, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path = "/")
	public ResponseEntity<Category> saveCategory(@RequestBody Category category){
		try {
			return new ResponseEntity<Category>(categoryService.saveCategory(category), HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Category> getCategoryByID(@PathVariable Long code){
		try {
			if(categoryService.getCategoryByCode(code) != null) {
				return new ResponseEntity<Category>(categoryService.getCategoryByCode(code), HttpStatus.OK);
			}
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(path = "/{code}")
	public void deleteCategoryByCode(@PathVariable Long code) {
		categoryService.deleteCategoryByCode(code);
	}

}
