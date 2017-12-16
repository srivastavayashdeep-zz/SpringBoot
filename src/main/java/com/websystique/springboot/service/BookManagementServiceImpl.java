package com.websystique.springboot.service;

import com.mongodb.MongoException;
import com.mongodb.MongoWriteException;
import com.websystique.springboot.model.Book;
import com.websystique.springboot.model.LibraryBook;
import com.websystique.springboot.repository.BookRepository;
import com.websystique.springboot.util.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service("bookManagementService")
public class BookManagementServiceImpl implements BookManagementService {

    @Autowired
    BookRepository bookRepository;

    public List<Long> addBooks(List<Book> books) {
        List<Long> allBooksSaved = new ArrayList<>(books.size());

        for (Book book : books) {
            LibraryBook libraryBook = BookMapper.formLibraryBook(book);
            try {
                allBooksSaved.add(bookRepository.save(libraryBook).getBookId());
            } catch (MongoWriteException exp) {
                System.out.print("Exception Occured while writing the book into library database : " + exp);
            } catch (MongoException exp) {
                System.out.print("Exception Occured while saving the book into library database : " + exp);
            }
        }
        return allBooksSaved;
    }

    public List<LibraryBook> searchBooks(String field, String value) {
        System.out.print("Service class   field : " + field + "    value   : " + value);
        List<LibraryBook> books = new ArrayList<>();
        switch (field) {
            case "bookId":
                books = bookRepository.findByBookId(value);
                break;
            case "name":
                books = bookRepository.findByName(value);
                break;
            case "isbn":
                books = bookRepository.findByIsbn(value);
                break;
            case "genre":
                books = bookRepository.findByGenre(value);
                break;
            case "type":
                books = bookRepository.findByType(value);
                break;
            case "author":
                books = bookRepository.findByAuthor(value);
                break;
            case "edition":
                books = bookRepository.findByEdition(value);
                break;
            case "publicationDate":
                books = bookRepository.findByPublicationDate(value);
                break;
            case "price":
                books = bookRepository.findByPrice(value);
                break;
            case "metadata":
                books = findByMetaData(value);
                break;
            default:
                books = null;
        }
        return books;
    }

    private List<LibraryBook> findByMetaData(String value) {
        List<LibraryBook> books = bookRepository.findAll();
        List<LibraryBook> booksResult = new ArrayList<>();
        for (LibraryBook book : books) {
            String metaData = book.getMetaData();
            List<String> strArray = Arrays.asList(metaData.split(","));
            if (strArray.contains(value)) {
                booksResult.add(book);
            }
        }
        return booksResult;
    }
}
