package com.example.springbootproject.repository;

import com.example.springbootproject.model.Book;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public interface BookSpecificationProvider {
    Specification<Book> getSpecification(List<String> parameters, String fieldName);
}
