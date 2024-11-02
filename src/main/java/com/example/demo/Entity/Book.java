package com.example.demo.Entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)  
	private int id;
	
	private String name;
	
	@ManyToMany
	@JoinTable(name= "books_authors",
	joinColumns = {@JoinColumn(name= "book_id")},
	inverseJoinColumns = {@JoinColumn(name= "author_id")})
	private List<Author> authors;
	
	@ManyToMany
	@JoinTable(name= "books_categories",
	joinColumns = {@JoinColumn(name= "book_id")},
	inverseJoinColumns = {@JoinColumn(name= "category_id")})
	private List<Category> categories;
	
	@ManyToMany
	@JoinTable(name= "books_publishers",
	joinColumns = {@JoinColumn(name= "book_id")},
	inverseJoinColumns = {@JoinColumn(name= "publisher_id")})
	private List<Publisher> publishers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	public List<Publisher> getPublishers() {
		return publishers;
	}

	public void setPublishers(List<Publisher> publishers) {
		this.publishers = publishers;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
}
