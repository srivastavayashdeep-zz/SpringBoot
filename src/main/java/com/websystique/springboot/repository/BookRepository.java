package com.websystique.springboot.repository;

import com.websystique.springboot.model.Domain;
import com.websystique.springboot.model.LibraryBook;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<LibraryBook, Long> {

    List<LibraryBook> findBybookIdOrName(String value);
}
