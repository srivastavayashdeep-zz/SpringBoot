package com.websystique.springboot.service;


import com.websystique.springboot.model.Book;
import com.websystique.springboot.model.LibraryBook;
import com.websystique.springboot.model.User;

import java.util.List;

public interface BookManagementService {

    List<Long> addBooks(List<Book> books);

    List<LibraryBook> searchBooks(String field , String value);

}
