package com.vector.authorbookcommon.mapper;


import com.vector.authorbookcommon.dto.AuthorDto;
import com.vector.authorbookcommon.dto.SaveAuthorRequest;
import com.vector.authorbookcommon.entity.Author;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {


    //  @Mapping(source = "name", target = "name")
    AuthorDto toDto(Author author);

    List<AuthorDto> toDtoList(List<Author> authors);

    Author toEntity(SaveAuthorRequest saveAuthorRequest);
}
