package com.vector.authorbookrest.endpoint;

import com.vector.authorbookcommon.dto.AuthorDto;
import com.vector.authorbookcommon.dto.SaveAuthorRequest;
import com.vector.authorbookcommon.service.AuthorService;
import com.vector.authorbookrest.security.CurrentUser;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthorEndpoint {

    private final AuthorService authorService;


    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(value = "/authors")
    public ResponseEntity<List<AuthorDto>> getAll(@AuthenticationPrincipal CurrentUser currentUser) {
        log.info("request from {} user", currentUser.getUsername());
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<AuthorDto> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok(authorService.findById(id));
    }

    @PostMapping("/authors")
    public ResponseEntity<?> create(@RequestBody SaveAuthorRequest authorRequest) {
        if (authorService.findByPhone(authorRequest.getPhone()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        AuthorDto authorFromDb = authorService.save(authorRequest);
        return ResponseEntity.ok(authorFromDb);
    }

    @DeleteMapping("authors/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {

        authorService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
