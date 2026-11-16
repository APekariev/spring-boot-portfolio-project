package com.example.springbootproject.repository.book;

import com.example.springbootproject.model.Book;
import com.example.springbootproject.repository.BookSpecificationProvider;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class InIsbnValuesRangeSpecificationProvider implements BookSpecificationProvider {
    private static final String ISBN_FIELD_NAME = "isbn";

    public String getKey() {
        return ISBN_FIELD_NAME;
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<String> predicate = criteriaBuilder.in(root.get(ISBN_FIELD_NAME));
            for (String param : params) {
                predicate.value(param);
            }
            return predicate;
        };
    }
}
