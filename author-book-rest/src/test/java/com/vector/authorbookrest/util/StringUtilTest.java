package com.vector.authorbookrest.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilTest {

//    @BeforeEach
//    void setUp() {
//        System.out.println("Before test");
//    }
//
//    @AfterEach
//    void tearDown() {
//        System.out.println("After test");
//    }

    @Test
    void lastWord() {
        String words = "Hello world";
        String lastWord = StringUtil.lastWord(words);
        assertEquals("world", lastWord);
    }

    @Test
    void lastWordWithSpacesSuccess() {
        String words = "Hello world  ";
        String lastWord = StringUtil.lastWord(words);
        assertEquals("world", lastWord);
    }

    @Test
    void lastWordWithNullValue() {
        String words = null;
        String lastWord = StringUtil.lastWord(words);
        assertEquals("", lastWord);
    }

    @Test
    void lastWordWithEmptyValue() {
        String words = "";
        String lastWord = StringUtil.lastWord(words);
        assertEquals("", lastWord);
    }

    @Test
    void lastWordWithoutSpace() {
        String words = "Hello";
        String lastWord = StringUtil.lastWord(words);
        assertEquals("Hello", lastWord);
    }

    @Test
    void lastWordOneWithSpaces() {
        String words = "Hello     ";
        String lastWord = StringUtil.lastWord(words);
        assertEquals("Hello", lastWord);
    }

    @Test
    void lastWordWithOnlySpaceThrowsException() {
        String words = " ";
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> StringUtil.lastWord(words));
        assertNotNull(exception);
        assertEquals("value cant be space", exception.getMessage());
    }
}