package com.example.springbootproject.mapper;

import com.example.springbootproject.config.MapperConfig;
import com.example.springbootproject.dto.BookDto;
import com.example.springbootproject.dto.BookSearchParametersDto;
import com.example.springbootproject.dto.CreateBookRequestDto;
import com.example.springbootproject.model.Book;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    Book convertDtoToModel(CreateBookRequestDto bookDto);

    BookDto convertModelToDto(Book book);

    void updateBookFromDto(CreateBookRequestDto bookDto,
                           @MappingTarget Book book);

    default Map<String, List<String>> convertSearchParametersDtoToBookParameters(
            BookSearchParametersDto searchParametersDto) {
        Map<String, List<String>> mapFromDtoRecord = new HashMap<>();
        String[] authors = searchParametersDto.author();
        String[] titles = searchParametersDto.title();
        String[] isbns = searchParametersDto.isbn();
        if (authors != null && authors.length > 0) {
            mapFromDtoRecord.put("author", Arrays.asList(authors));
        }
        if (titles != null && titles.length > 0) {
            mapFromDtoRecord.put("title", Arrays.asList(titles));
        }
        if (isbns != null && isbns.length > 0) {
            mapFromDtoRecord.put("isbn", Arrays.asList(isbns));
        }
        return mapFromDtoRecord;
    }
}
