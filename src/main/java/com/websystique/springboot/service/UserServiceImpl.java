package com.websystique.springboot.service;

import com.mongodb.MongoException;
import com.mongodb.MongoWriteException;
import com.websystique.springboot.model.Book;
import com.websystique.springboot.model.LibraryBook;
import com.websystique.springboot.model.User;
import com.websystique.springboot.repository.BookRepository;
import com.websystique.springboot.util.LibraryHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    BookRepository bookRepository;

    private static final AtomicLong counter = new AtomicLong();

    private static List<User> users;

    static {
        users = populateDummyUsers();
    }

    public List<User> findAllUsers() {
        return users;
    }

    public User findById(long id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User findByName(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    public void saveUser(User user) {
        user.setId(counter.incrementAndGet());
        users.add(user);
    }

    public void updateUser(User user) {
        int index = users.indexOf(user);
        users.set(index, user);
    }

    public void deleteUserById(long id) {

        for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
            }
        }
    }

    public boolean isUserExist(User user) {
        return findByName(user.getName()) != null;
    }

    public void deleteAllUsers() {
        users.clear();
    }

    private static List<User> populateDummyUsers() {
        List<User> users = new ArrayList<User>();
        users.add(new User(counter.incrementAndGet(), "Sam", 30, 70000));
        users.add(new User(counter.incrementAndGet(), "Tom", 40, 50000));
        users.add(new User(counter.incrementAndGet(), "Jerome", 45, 30000));
        users.add(new User(counter.incrementAndGet(), "Silvia", 50, 40000));
        return users;
    }

    public boolean addBooks(List<Book> books) {
        List<Long> allBooksSaved = new ArrayList<>(books.size());

        for(Book book : books) {
            LibraryBook libraryBook = LibraryHelper.formLibraryBook(book);
            try {
                allBooksSaved.add(bookRepository.save(libraryBook).getBookId());
            }catch (MongoWriteException exp){
                System.out.print("Exception Occured while writing the book into library database : "+exp);
            }catch (MongoException exp){
                System.out.print("Exception Occured while saving the book into library database : "+exp);
            }
        }
            return allBooksSaved.size() == books.size() ? true : false ;
    }

    public List<LibraryBook> searchBooks(String keyword){

        return new ArrayList<>();
    }
}
