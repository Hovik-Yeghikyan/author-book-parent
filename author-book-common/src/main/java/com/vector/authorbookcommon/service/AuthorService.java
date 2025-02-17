package com.vector.authorbookcommon.service;



import com.vector.authorbookcommon.dto.AuthorDto;
import com.vector.authorbookcommon.dto.SaveAuthorRequest;
import com.vector.authorbookcommon.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Page<Author> findAll(Pageable pageable);

    List<AuthorDto> findAll();

    AuthorDto save(SaveAuthorRequest saveAuthorRequest);

    void deleteById(int id);

    AuthorDto findById(int id);

    Optional<Author> findByPhone(String phone);

    long countOfAuthors();

}
