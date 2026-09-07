package com.example.springbootproject.service;

import com.example.springbootproject.model.Book;
import java.util.List;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
