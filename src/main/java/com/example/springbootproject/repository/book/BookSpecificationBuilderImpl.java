package com.example.springbootproject.repository.book;

import com.example.springbootproject.dto.BookSearchParametersDto;
import com.example.springbootproject.model.Book;
import com.example.springbootproject.repository.BookSpecificationBuilder;
import com.example.springbootproject.repository.BookSpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationBuilderImpl implements BookSpecificationBuilder<Book> {
    private static final String AUTHOR_FIELD_NAME = "author";
    private static final String ISBN_FIELD_NAME = "isbn";
    private static final String TITLE_FIELD_NAME = "title";
    private final BookSpecificationProviderManager specificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto bookSearchParametersDto) {
        Specification<Book> spec = Specification.unrestricted();
        if (bookSearchParametersDto.author() != null
                && bookSearchParametersDto.author().length > 0) {
            spec = spec.and(specificationProviderManager
                    .getBookSpecificationProvider(AUTHOR_FIELD_NAME)
                    .getSpecification(bookSearchParametersDto.author()));
        }
        if (bookSearchParametersDto.title() != null
                && bookSearchParametersDto.title().length > 0) {
            spec = spec.and(specificationProviderManager
                    .getBookSpecificationProvider(TITLE_FIELD_NAME)
                    .getSpecification(bookSearchParametersDto.title()));
        }
        if (bookSearchParametersDto.isbn() != null
                && bookSearchParametersDto.isbn().length > 0) {
            spec = spec.and(specificationProviderManager
                    .getBookSpecificationProvider(ISBN_FIELD_NAME)
                    .getSpecification(bookSearchParametersDto.isbn()));
        }
        return spec;
    }
}
