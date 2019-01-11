package com.ikoval.libman.server.service;

import com.ikoval.libman.server.repository.AuthorRepository;
import com.ikoval.libman.server.repository.BookGenreRepository;
import com.ikoval.libman.server.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyConversionServiceTest {

    @Autowired
    private MyConversionService myConversionService;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private BookGenreRepository bookGenreRepository;

    @MockBean
    private BookRepository bookRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void testConvertToBook() {

    }

}
