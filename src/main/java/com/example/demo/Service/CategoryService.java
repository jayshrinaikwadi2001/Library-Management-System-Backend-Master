package com.example.demo.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.Category;
import com.example.demo.Repo.CategoryRepo;

@Service
public class CategoryService {
	
	@Autowired
	public CategoryRepo categoryRepo;
	
	public List<Category>getAllcategories(){
		return categoryRepo.findAll();
	}
	
	public Category getcategoryById(int id) {
		return categoryRepo.findById(id).orElse(null);
	}
	
	public Category saveOrUpdatecategory(Category category) {
		return categoryRepo.save(category);
	}
	
	public void deletecategoryById(int id) {
		categoryRepo.findById(id).orElse(null);
		categoryRepo.deleteById(id);
	}

}
