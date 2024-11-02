package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Entity.Author;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer> {

}
