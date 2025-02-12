package com.vector.authorbookcommon.mapper;


import com.vector.authorbookcommon.dto.BookDto;
import com.vector.authorbookcommon.dto.SaveBookRequest;
import com.vector.authorbookcommon.entity.Author;
import com.vector.authorbookcommon.entity.Book;
import com.vector.authorbookcommon.exception.AuthorNotFoundException;
import com.vector.authorbookcommon.repository.AuthorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class BookMapper {
    @Autowired
    private AuthorRepository authorRepository;

    public abstract BookDto toDto(Book book);

    public abstract List<BookDto> toDtoList(List<Book> books);

    @Mapping(target = "author", expression = "java(findAuthorById(bookRequest.getAuthorId()))")
    @Mapping(target = "createdAt",expression = "java(new java.util.Date())")
    public abstract Book toEntity(SaveBookRequest bookRequest);

    protected Author findAuthorById(int id) {
        return authorRepository.findById(id).orElseThrow(()-> new AuthorNotFoundException("Author not found with " + id +" id"));

    }
}
