package com.example.springbootproject.repository.book;

import com.example.springbootproject.model.Book;
import com.example.springbootproject.repository.BookSpecificationProvider;
import jakarta.persistence.criteria.CriteriaBuilder;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BookBookSpecificationProviderImpl implements BookSpecificationProvider {
    @Override
    public Specification<Book> getSpecification(List<String> parameters, String fieldName) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            CriteriaBuilder.In<String> predicate = criteriaBuilder.in(root.get(fieldName));
            parameters.forEach(predicate::value);
            return predicate;
        };
    }
}
