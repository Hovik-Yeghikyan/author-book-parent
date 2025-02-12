package com.vector.authorbookcommon.repository;

import com.vector.authorbookcommon.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {


}
