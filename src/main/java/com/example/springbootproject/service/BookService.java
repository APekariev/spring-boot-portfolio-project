package com.example.springbootproject.service;

import com.example.springbootproject.dto.BookDto;
import com.example.springbootproject.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto bookDto);

    List<BookDto> getAll();

    BookDto getById(long id);
}
