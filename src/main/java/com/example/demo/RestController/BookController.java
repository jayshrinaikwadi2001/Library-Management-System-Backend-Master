package com.example.demo.RestController;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Entity.Author;
import com.example.demo.Entity.Book;
import com.example.demo.Entity.Category;
import com.example.demo.Entity.Publisher;
import com.example.demo.Service.AuthorService;
import com.example.demo.Service.BookService;
import com.example.demo.Service.CategoryService;
import com.example.demo.Service.PublisherService;
import org.springframework.data.domain.Sort;

//Author 
//Jayshri Naikwadi

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PublisherService publisherService;
	
//	@GetMapping
//	public ResponseEntity<List<Book>> getAllBooks(){
//		List<Book> books = bookService.getAllBooks();
//		return ResponseEntity.ok(books);	
//	}
	
	//Pagination
	@GetMapping
	   public ResponseEntity<Page<Book>> getAllBooks(
	            @RequestParam(defaultValue = "0") int page,   // default page number is 0
	            @RequestParam(defaultValue = "10") int size,  // default page size is 10
	            @RequestParam(defaultValue = "id") String sortBy // default sorting by "id"
	    ) {
	        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
	        Page<Book> books = bookService.getAllBooks(pageable);
	        return ResponseEntity.ok(books);
	    }
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBook(@PathVariable int id){
		Book book = bookService.getBookById(id);
		if(book== null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(book); 
	}
	
	@PostMapping
	public ResponseEntity<Book> saveBook(@RequestBody Book book){
		//set Author to book
		List<Author> authors = new ArrayList<Author>();
		
		for(Author author : book.getAuthors()) {
			
			Author foundAuthor = authorService.getAuthorById(author.getId());
			
			if(foundAuthor == null) {
				return ResponseEntity.notFound().build();
			}
			authors.add(foundAuthor);
		}
		book.setAuthors(authors);
		
		//set category to book (Many to many)
		List<Category> Categories = new ArrayList<Category>();
		
		for(Category category : book.getCategories()) {
			
			Category foundCategory = categoryService.getcategoryById(category.getId());
			
			if(foundCategory == null) {
				return ResponseEntity.notFound().build();
			}
			Categories.add(foundCategory);
		}
		book.setCategories(Categories);
		
				//set Publisher to book
				List<Publisher> publishers = new ArrayList<Publisher>();
				
				for(Publisher publisher : book.getPublishers()) {
					
					Publisher foundPublisher = publisherService.getPublisherById(publisher.getId());
					
					if(foundPublisher == null) {
						return ResponseEntity.notFound().build();
					}
					publishers.add(foundPublisher);
				}
				book.setPublishers(publishers);
		
		Book createBook = bookService.saveOrUpdateBook(book);
		return ResponseEntity.status(HttpStatus.CREATED) .body(createBook);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book){
		Book existingBook = bookService.getBookById(id);
		if(existingBook==null) {
			return ResponseEntity.notFound().build();
		}
		book.setId(id); 
		bookService.saveOrUpdateBook(book);
		return ResponseEntity.ok(book); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable int id){
		//if Record not Present
		Book existingBook = bookService.getBookById(id);
		if(existingBook==null) {
			return ResponseEntity.notFound().build();
		}
		
		//If Record Present then Delete it
		bookService.deleteBookById(id);
		return ResponseEntity.noContent().build();
	}
	
}
