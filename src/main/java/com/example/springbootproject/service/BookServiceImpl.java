package com.example.springbootproject.service;

import com.example.springbootproject.dto.BookDto;
import com.example.springbootproject.dto.BookSearchParametersDto;
import com.example.springbootproject.dto.CreateBookRequestDto;
import com.example.springbootproject.exception.EntityNotFoundException;
import com.example.springbootproject.mapper.BookMapper;
import com.example.springbootproject.model.Book;
import com.example.springbootproject.repository.BookRepository;
import com.example.springbootproject.repository.BookSpecificationProvider;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationProvider bookSpecificationProvider;

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
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot "
                        + "find book by id: " + id));
        return bookMapper.convertModelToDto(book);
    }

    @Override
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Cannot find book by id: " + id);
        }
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto bookRequestDto) {
        Book foundBookInDb = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find "
                        + "the book with id: " + id));
        bookMapper.updateBookFromDto(bookRequestDto, foundBookInDb);
        Book updatedBook = bookRepository.save(foundBookInDb);
        return bookMapper.convertModelToDto(updatedBook);
    }

    @Override
    public List<BookDto> searchBooks(BookSearchParametersDto searchParameters) {
        Map<String, List<String>> bookParameters = bookMapper
                .convertSearchParametersDtoToBookParameters(searchParameters);
        Specification<Book> resultSpecification = null;
        for (Map.Entry<String, List<String>> param: bookParameters.entrySet()) {
            Specification<Book> specificationProvided = bookSpecificationProvider
                    .getSpecification(param.getValue(),
                    param.getKey());
            resultSpecification = resultSpecification == null ? specificationProvided :
                    resultSpecification.and(specificationProvided);
        }
        List<Book> allFoundBooks = bookRepository.findAll(resultSpecification);
        return allFoundBooks.stream()
                .map(bookMapper::convertModelToDto)
                .toList();
    }
}
