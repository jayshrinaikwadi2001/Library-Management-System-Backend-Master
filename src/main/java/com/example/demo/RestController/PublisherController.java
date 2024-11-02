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
import com.example.demo.Entity.Publisher;
import com.example.demo.Service.PublisherService;

//Author 
//Jayshri Naikwadi

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {
	
	@Autowired
	private PublisherService publisherService;
	
	@GetMapping
	public ResponseEntity<List<Publisher>> getAllPublishers(){
		List<Publisher> publishers = publisherService.getAllPublishers();
		return ResponseEntity.ok(publishers);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Publisher> getPublisher(@PathVariable int id){
		Publisher publisher = publisherService.getPublisherById(id);
		if(publisher== null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(publisher); 
	}
	
	@PostMapping
	public ResponseEntity<Publisher> savePublisher(@RequestBody Publisher publisher){
		Publisher createPublisher = publisherService.saveOrUpdatePublisher(publisher);
		return ResponseEntity.status(HttpStatus.CREATED) .body(createPublisher);
	}
	
	@PutMapping
	public ResponseEntity<Publisher> updatePublisher(@PathVariable int id, @RequestBody Publisher publisher){
		Publisher existingPublisher = publisherService.getPublisherById(id);
		if(existingPublisher==null) {
			return ResponseEntity.notFound().build();
		}
		publisher.setId(id); 
		publisherService.saveOrUpdatePublisher(publisher);
		return ResponseEntity.ok(publisher); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePublisher(@PathVariable int id){
		//if Record not Present
		Publisher existingPublisher = publisherService.getPublisherById(id);
		if(existingPublisher==null) {
			return ResponseEntity.notFound().build();
		}
		
		//If the record is present and not assigned to any book, then delete it.
		publisherService.deletePublisherById(id);
		return ResponseEntity.noContent().build();
	}
	
}
