package com.ikoval.libman.server.service;

import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.repository.BookRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {


    @Autowired
    BookService bookService;

    @MockBean
    BookRepository bookRepository;

    Book book;

    PageRequest pageRequest;

    @Before
    public void setUp() {
        book = new Book();
        book.setTitle("Title");

        pageRequest = PageRequest.of(0,1);

    }

    @Test
    public void shouldSaveBook() {


        when(bookRepository.save(book)).thenReturn(book);

        Book responseBook = bookService.save(book);

        Assert.assertEquals(responseBook,book);
    }

    @Test
    public void shouldFindAllByPageRequest() {
        Page<Book> page = new PageImpl<>(Arrays.asList(book),pageRequest,1);

        when(bookRepository.findAll(pageRequest)).thenReturn(page);

        Page<Book> response = bookService.findAll(pageRequest);

        Assert.assertEquals(response.isLast(),page.isLast());
        Assert.assertEquals(response.getTotalElements(),page.getTotalElements());
        Assert.assertEquals(response.getContent(),page.getContent());
    }

}
