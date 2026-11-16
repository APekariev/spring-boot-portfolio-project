package com.example.springbootproject.repository;

import com.example.springbootproject.dto.BookSearchParametersDto;
import org.springframework.data.jpa.domain.Specification;

public interface BookSpecificationBuilder<T> {

    Specification<T> build(BookSearchParametersDto searchParametersDto);
}
