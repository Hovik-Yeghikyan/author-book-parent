package com.vector.authorbookcommon.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public void test() {
        throw new RuntimeException();
    }
}
