package com.ikoval.libman.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikoval.libman.server.converter.MyPageRequestConverter;
import com.ikoval.libman.server.domain.Author;
import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.domain.BookGenre;
import com.ikoval.libman.server.filter.MySpecification;
import com.ikoval.libman.server.service.BookService;
import com.ikoval.libman.server.service.MyConversionService;
import com.ikoval.libman.shared.FilterCriteria;
import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.shared.dto.MyPageRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.hamcrest.core.Is.is;


@RunWith(SpringRunner.class)
@WebMvcTest(secure = false, controllers = BookRestController.class)
public class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private MyConversionService conversionService;

    private Book book;

    private BookDto bookDto;

    private MyPageRequest myPageRequest;

    private PageRequest pageRequest;

    private Page pageResponse;

    private FilterCriteria filterCriteria;

    private Specification<Book> spec;

    @Before
    public void setUp() {
        book = new Book();

        Author author1 = new Author("Author1");
        Author author2 = new Author("Author2");
        BookGenre genre1 = new BookGenre("Genre1");
        BookGenre genre2 = new BookGenre("Genre2");

        book.setId(123L);
        book.setTitle("Book54");
        book.setPublisher("Publisher23");
        book.setAuthors(Arrays.asList(author1,author2));
        book.setGenres(Arrays.asList(genre1,genre2));
        book.setYearOfPublishing(2018);
        book.setPages(200);
        book.setAddedDate(new Date(new java.util.Date().getTime()));

        bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setPublisher(book.getPublisher());
        bookDto.setAuthors(book.getAuthors().stream().map(Author::getFullName).collect(Collectors.joining(", ")));
        bookDto.setGenres(book.getGenres().stream().map(BookGenre::getName).collect(Collectors.joining(", ")));
        bookDto.setYearOfPublishing(book.getYearOfPublishing());
        bookDto.setPages(book.getPages());
        bookDto.setAddedDate(book.getAddedDate().toString());

        myPageRequest = new MyPageRequest();
        myPageRequest.setPage(0);
        myPageRequest.setSize(1);
        myPageRequest.setProperty("id");
        myPageRequest.setDirection("desc");

        pageRequest = MyPageRequestConverter.convert(myPageRequest);

        pageResponse = new PageImpl<>(Arrays.asList(book),pageRequest,1);

        filterCriteria = new FilterCriteria();
        filterCriteria.setBookTitle(book.getTitle());
        filterCriteria.setGenre("Genre1");
        filterCriteria.setAuthorName("Author2");
        myPageRequest.setFilter(filterCriteria);

        spec = MySpecification.filterBook(filterCriteria);

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testFindBookByPageRequest() throws Exception {
        when(bookService.findAll(pageRequest)).thenReturn(pageResponse);
        mockMvc.perform(post("/libman/api/books")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(myPageRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.content",isA(List.class)));
    }

    @Test
    public void testFindBookByPageRequestWithFiltering() throws Exception {

/*        when(bookService.findAll(spec,pageRequest)).thenReturn(pageResponse);

        mockMvc.perform(post("/libman/api/books/filter")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(myPageRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.content",isA(List.class)))
                .andExpect(jsonPath("$.totalElements",is(pageResponse.getTotalElements())))
                .andExpect(jsonPath("$.isLast",is(pageResponse.isLast())));*/
    }

    @Test
    public void testGetBookById() throws Exception {
        when(bookService.getById(book.getId())).thenReturn(Optional.of(book));
        mockMvc.perform(get("/libman/api/book/123"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is((bookDto.getId().intValue()))))
                .andExpect(jsonPath("$.title",is(bookDto.getTitle())))
                .andExpect(jsonPath("$.publisher",is(bookDto.getPublisher())))
                .andExpect(jsonPath("$.authors",is(bookDto.getAuthors())))
                .andExpect(jsonPath("$.genres",is(bookDto.getGenres())))
                .andExpect(jsonPath("$.yearOfPublishing",is(bookDto.getYearOfPublishing())))
                .andExpect(jsonPath("$.pages",is(bookDto.getPages())))
                .andExpect(jsonPath("$.addedDate",is(bookDto.getAddedDate())));
    }

    @Test
    public void testWrongBookById() {

    }

    @Test
    public void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/libman/api/book/delete")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(bookDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveBook() throws Exception {
        mockMvc.perform(post("/libman/api/book/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(bookDto)))
                .andExpect(status().isOk());
    }



}
