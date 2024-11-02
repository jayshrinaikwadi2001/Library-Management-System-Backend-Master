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
import com.example.demo.Entity.Author;
import com.example.demo.Service.AuthorService;

// Author 
// Jayshri Naikwadi

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	@GetMapping
	public ResponseEntity<List<Author>> getAllAuthors(){
		List<Author> authors = authorService.getAllAuthors();
		return ResponseEntity.ok(authors);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Author> getAuthor(@PathVariable int id){
		Author author = authorService.getAuthorById(id);
		if(author== null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(author); 
	}
	
	@PostMapping
	public ResponseEntity<Author> saveAuthor(@RequestBody Author author){
		Author createAuthor = authorService.saveOrUpdateAuthor(author);
		return ResponseEntity.status(HttpStatus.CREATED) .body(createAuthor);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Author> updateAuthor(@PathVariable int id, @RequestBody Author author){
		Author existingAuthor = authorService.getAuthorById(id);
		if(existingAuthor==null) {
			return ResponseEntity.notFound().build();
		}
		author.setId(id); 
		authorService.saveOrUpdateAuthor(author);
		return ResponseEntity.ok(author); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAuthor(@PathVariable int id){
		//if Record not Present
		Author existingAuthor = authorService.getAuthorById(id);
		if(existingAuthor==null) {
			return ResponseEntity.notFound().build();
		}
		
		//If Record is Present and not assigned to any book, then delete it.
		authorService.deleteAuthorById(id);
		return ResponseEntity.noContent().build();
	}
	

}
