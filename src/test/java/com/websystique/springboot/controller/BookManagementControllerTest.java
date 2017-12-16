package com.websystique.springboot.controller;

import com.websystique.springboot.model.Book;
import com.websystique.springboot.service.BookManagementService;
import com.websystique.springboot.service.BookManagementServiceImpl;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

public class BookManagementControllerTest {

    @Mock
    BookManagementServiceImpl bookManagementService;

    @InjectMocks
    BookManagementController bookManagementController;


}
