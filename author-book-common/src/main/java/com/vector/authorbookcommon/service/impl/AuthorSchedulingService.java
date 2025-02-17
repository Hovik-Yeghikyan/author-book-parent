package com.vector.authorbookcommon.service.impl;

import com.vector.authorbookcommon.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorSchedulingService {

    private final AuthorService authorService;

    @Scheduled(cron = "0 */5 * * * *")
    public void printAuthorsCount(){
        long count = authorService.countOfAuthors();
        System.out.println("Total count of authors: " + count);
    }
}
