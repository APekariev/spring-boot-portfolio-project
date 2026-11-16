package com.example.springbootproject.repository;

import com.example.springbootproject.model.Book;
import org.springframework.data.jpa.domain.Specification;

public interface BookSpecificationProvider {
    String getKey();

    Specification<Book> getSpecification(String[] params);
}
