package com.example.springbootproject.mapper;

import com.example.springbootproject.config.MapperConfig;
import com.example.springbootproject.dto.BookDto;
import com.example.springbootproject.dto.CreateBookRequestDto;
import com.example.springbootproject.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    Book convertDtoToModel(CreateBookRequestDto bookDto);

    BookDto convertModelToDto(Book book);
}
