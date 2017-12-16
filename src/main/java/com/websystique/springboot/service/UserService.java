package com.websystique.springboot.service;


import com.websystique.springboot.model.Book;
import com.websystique.springboot.model.LibraryBook;
import com.websystique.springboot.model.User;

import java.util.List;

public interface UserService {

    User findById(long id);

    User findByName(String name);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(long id);

    List<User> findAllUsers();

    void deleteAllUsers();

    boolean isUserExist(User user);

    boolean addBooks(List<Book> books);

    List<LibraryBook> searchBooks(String keyword);

}
