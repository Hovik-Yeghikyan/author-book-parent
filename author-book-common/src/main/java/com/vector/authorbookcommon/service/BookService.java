package com.vector.authorbookcommon.service;


import com.vector.authorbookcommon.dto.BookDto;
import com.vector.authorbookcommon.dto.SaveBookRequest;

import java.util.List;

public interface BookService {


    List<BookDto> findAll();

    BookDto save(SaveBookRequest bookRequest);

    void deleteById(int id);

    BookDto findById(int id);

}

