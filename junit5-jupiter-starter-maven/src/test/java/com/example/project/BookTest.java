package com.example.project;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author : wecashtester
 * @CreateTime : 2019/7/1  下午11:01
 * @ContentUse ：
 */
class BookTest {

    @ParameterizedTest
    @ValueSource(strings = "Test Book" )
    void testWithImplicitFallbackArgumentConversion(Book book){
        assertEquals("Test Book", book.getTitle());
    }
}