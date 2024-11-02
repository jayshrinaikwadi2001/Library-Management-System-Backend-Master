package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Entity.Publisher;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher, Integer> {

}
