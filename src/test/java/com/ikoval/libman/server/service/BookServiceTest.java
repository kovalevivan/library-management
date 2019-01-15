package com.ikoval.libman.server.service;

import com.ikoval.libman.server.domain.Author;
import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.domain.BookGenre;
import com.ikoval.libman.server.filter.BookSpecification;
import com.ikoval.libman.server.repository.BookRepository;
import com.ikoval.libman.shared.FilterCriteria;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {


    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    private Book book;

    private PageRequest pageRequest;

    private Page<Book> page;

    @Before
    public void setUp() {
        book = new Book();
        Author author1 = new Author("Author1");
        Author author2 = new Author("Author2");
        BookGenre genre1 = new BookGenre("Genre1");
        BookGenre genre2 = new BookGenre("Genre2");

        book.setTitle("Book54");
        book.setPublisher("Publisher23");
        book.setAuthors(Arrays.asList(author1,author2));
        book.setGenres(Arrays.asList(genre1,genre2));
        book.setYearOfPublishing(2018);
        book.setPages(200);
        book.setAddedDate(new Date(new java.util.Date().getTime()));
        pageRequest = PageRequest.of(0,1);
        page = new PageImpl<>(Arrays.asList(book),pageRequest,1);

    }

    @Test
    public void shouldSaveBook() {


        when(bookRepository.save(book)).thenReturn(book);

        Book responseBook = bookService.save(book);

        Assert.assertEquals(responseBook,book);
    }

    @Test
    public void shouldFindAllByPageRequest() {
        when(bookRepository.findAll(pageRequest)).thenReturn(page);

        Page<Book> response = bookService.findAll(pageRequest);

        Assert.assertEquals(response.isLast(),page.isLast());
        Assert.assertEquals(response.getTotalElements(),page.getTotalElements());
        Assert.assertEquals(response.getContent(),page.getContent());
    }

    @Test
    public void shouldFindAllByPageRequestWithFiltering() {
        FilterCriteria filterCriteria = new FilterCriteria();
        filterCriteria.setBookTitle(book.getTitle());
        filterCriteria.setGenre("Genre1");
        filterCriteria.setAuthorName("Author2");
        Specification<Book> spec = new BookSpecification(filterCriteria);

        when(bookRepository.findAll(spec,pageRequest)).thenReturn(page);

        Page<Book> response = bookService.findAll(spec,pageRequest);

        Assert.assertEquals(response.isLast(),page.isLast());
        Assert.assertEquals(response.getTotalElements(),page.getTotalElements());
        Assert.assertEquals(response.getContent(),page.getContent());
    }

}
