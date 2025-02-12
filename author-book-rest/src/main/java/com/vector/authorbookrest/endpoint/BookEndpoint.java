package com.vector.authorbookrest.endpoint;


import com.vector.authorbookcommon.dto.BookDto;
import com.vector.authorbookcommon.dto.SaveBookRequest;
import com.vector.authorbookcommon.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookEndpoint {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid SaveBookRequest saveBookRequest) {

        return ResponseEntity.ok(bookService.save(saveBookRequest));
    }
}
