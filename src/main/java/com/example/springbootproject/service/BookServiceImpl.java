package com.example.springbootproject.service;

import com.example.springbootproject.dto.BookDto;
import com.example.springbootproject.dto.CreateBookRequestDto;
import com.example.springbootproject.exception.EntityNotFoundException;
import com.example.springbootproject.mapper.BookMapper;
import com.example.springbootproject.model.Book;
import com.example.springbootproject.repository.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto bookDto) {
        Book book = bookMapper.convertDtoToModel(bookDto);
        Book savedBook = bookRepository.save(book);
        return bookMapper.convertModelToDto(savedBook);
    }

    @Override
    public List<BookDto> getAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::convertModelToDto)
                .toList();
    }

    @Override
    public BookDto getById(long id) {
        Book book = bookRepository.findBookById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot "
                        + "find book by id: " + id));
        return bookMapper.convertModelToDto(book);
    }
}
