package com.example.demo.RestController;

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
import com.example.demo.Entity.Category;
import com.example.demo.Service.CategoryService;

//Author 
//Jayshri Naikwadi

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<Category>> getAllCategories(){
		List<Category> categories = categoryService.getAllcategories();
		return ResponseEntity.ok(categories);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable int id){
		Category Category = categoryService.getcategoryById(id);
		if(Category== null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(Category); 
	}
	
	@PostMapping
	public ResponseEntity<Category> saveCategory(@RequestBody Category category){
		Category createCategory = categoryService.saveOrUpdatecategory(category);
		return ResponseEntity.status(HttpStatus.CREATED) .body(createCategory);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Category> updateBook(@PathVariable int id, @RequestBody Category category){
		Category existingCategory = categoryService.getcategoryById(id);
		if(existingCategory==null) {
			return ResponseEntity.notFound().build();
		}
		category.setId(id); 
		categoryService.saveOrUpdatecategory(category);
		return ResponseEntity.ok(category); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable int id){
		//if Record not Present
		Category existingCategory = categoryService.getcategoryById(id);
		if(existingCategory==null) {
			return ResponseEntity.notFound().build();
		}
		
		//If Record is Present and not assigned to any book, then delete it.
		categoryService.deletecategoryById(id);
		return ResponseEntity.noContent().build();
	}
	
}
