package com.example.demo.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.Book;
import com.example.demo.Repo.BookRepo;

@Service
public class BookService {
	
	@Autowired
	private BookRepo bookRepo;
	
	public List<Book>getAllBooks(){
		return bookRepo.findAll();
	}
	
	 public Page<Book> getAllBooks(Pageable pageable) {
	        return bookRepo.findAll(pageable);
	    }
	
	public Book getBookById(int id) {
		return bookRepo.findById(id).orElse(null);
	}
	
	public Book saveOrUpdateBook(Book book) {
		return bookRepo.save(book);
	}
	
	public void deleteBookById(int id) {
		bookRepo.findById(id).orElse(null);
		bookRepo.deleteById(id);
	}

	
	

}
