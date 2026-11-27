package com.example.springbootproject.repository.book;

import com.example.springbootproject.model.Book;
import com.example.springbootproject.repository.BookSpecificationProvider;
import jakarta.persistence.criteria.Predicate;
import java.util.Locale;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class InAuthorValuesRangeIgnoreCaseSpecificationProvider
        implements BookSpecificationProvider {
    private static final String AUTHOR_FIELD_NAME = "author";

    public String getKey() {
        return AUTHOR_FIELD_NAME;
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            Predicate resultPredicate = criteriaBuilder.disjunction();
            for (String author : params) {
                Predicate authorPredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(AUTHOR_FIELD_NAME)),
                        "%" + author.toLowerCase(Locale.ROOT) + "%"
                );

                resultPredicate = criteriaBuilder.or(
                        resultPredicate, authorPredicate);
            }
            return resultPredicate;
        };
    }
}
