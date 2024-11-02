package com.example.demo.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.Publisher;
import com.example.demo.Repo.PublisherRepo;

@Service
public class PublisherService {
	
	@Autowired
	public PublisherRepo publisherRepo;
	
	public List<Publisher>getAllPublishers(){
		return publisherRepo.findAll();
	}
	
	public Publisher getPublisherById(int id) {
		return publisherRepo.findById(id).orElse(null);
	}
	
	public Publisher saveOrUpdatePublisher(Publisher publisher) {
		return publisherRepo.save(publisher);
	}
	
	public void deletePublisherById(int id) {
		publisherRepo.findById(id).orElse(null); 
		publisherRepo.deleteById(id);
	}

}
