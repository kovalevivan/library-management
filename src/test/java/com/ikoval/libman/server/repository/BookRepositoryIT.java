package com.ikoval.libman.server.repository;


import com.ikoval.libman.server.domain.Author;
import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.domain.BookGenre;
import com.ikoval.libman.server.filter.BookSpecification;
import com.ikoval.libman.shared.FilterCriteria;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations= "classpath:application-test.properties")
public class BookRepositoryIT {

    @Autowired
    private BookRepository repository;

    private Book book;


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
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void shouldSaveAndFindById() {
        repository.save(book);
        Optional<Book> foundBook = repository.findById(book.getId());
        Assert.assertTrue(foundBook.isPresent());
        Assert.assertEquals(book, foundBook.get());
    }

    @Test
    public void shouldSaveAndDeleteById() {
        repository.save(book);
        repository.delete(book);
        Optional<Book> foundBook = repository.findById(book.getId());
        Assert.assertFalse(foundBook.isPresent());
    }

    @Test
    public void shouldFindAllByPageRequest() {
        Page<Book> pageResponse = repository.findAll(null,PageRequest.of(0,10));

        List<Book> books = pageResponse.getContent();

        Assert.assertNotNull(books);
        Assert.assertEquals(books.size(),10);
        Assert.assertEquals(pageResponse.getTotalElements(),16);
        Assert.assertFalse(pageResponse.isLast());
    }

    @Test
    public void shouldFindLastPage() {
        Page<Book> pageResponse = repository.findAll(PageRequest.of(1,10));
        Assert.assertTrue(pageResponse.isLast());
    }

    @Test
    public void shouldFindAllByPageRequestWithSorting() {
        Page<Book> pageResponseWithSorting = repository.findAll(PageRequest.of(0,16, Sort.Direction.ASC,"yearOfPublishing"));
        Page<Book> pageResponse = repository.findAll(PageRequest.of(0,16));

        List<Book> books = pageResponse.getContent();
        List<Book> booksWithSorting = pageResponseWithSorting.getContent();

        List<Book> expected = books.stream().sorted(Comparator.comparing(Book::getYearOfPublishing)).collect(Collectors.toList());

        Assert.assertEquals(expected,booksWithSorting);

    }

    @Test
    public void shouldFindWithFiltering() {
        FilterCriteria filter = new FilterCriteria();
        filter.setBookTitle("book54");
        filter.setAuthorName("author1");
        filter.setGenre("Genre1");
        Specification<Book> spec = new BookSpecification(filter);
        repository.save(book);
        Page<Book> bookResponse = repository.findAll(spec,PageRequest.of(0,10));

        Assert.assertEquals(book,bookResponse.getContent().get(0));
    }


}
