package com.example.springbootproject.repository;

import com.example.springbootproject.model.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
