package com.websystique.springboot.repository;

import com.websystique.springboot.model.LibraryBook;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<LibraryBook, Long> {

    List<LibraryBook> findByBookId(String value);
    List<LibraryBook> findByName(String value);
    List<LibraryBook> findByIsbn(String value);
    List<LibraryBook> findByGenre(String value);
    List<LibraryBook> findByType(String value);
    List<LibraryBook> findByAuthor(String value);
    List<LibraryBook> findByPublicationDate(String value);
    List<LibraryBook> findByEdition(String value);
    List<LibraryBook> findByPrice(String value);

}
