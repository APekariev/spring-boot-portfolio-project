package com.example.springbootproject.repository.book;

import com.example.springbootproject.model.Book;
import com.example.springbootproject.repository.BookSpecificationProvider;
import jakarta.persistence.criteria.Predicate;
import java.util.Locale;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class InTitleValuesRangeIgnoreCaseSpecificationProvider
        implements BookSpecificationProvider {
    private static final String TITLE_FIELD_NAME = "title";

    public String getKey() {
        return TITLE_FIELD_NAME;
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            Predicate resultPredicate = criteriaBuilder.disjunction();
            for (String title : params) {
                Predicate titlePredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(TITLE_FIELD_NAME)),
                        "%" + title.toLowerCase(Locale.ROOT) + "%"
                );

                resultPredicate = criteriaBuilder.or(
                        resultPredicate, titlePredicate);
            }
            return resultPredicate;
        };
    }
}
