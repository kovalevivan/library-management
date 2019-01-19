package com.ikoval.libman.server.controller;

import com.ikoval.libman.server.converter.MyPageRequestConverter;
import com.ikoval.libman.server.domain.Author;
import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.domain.BookGenre;
import com.ikoval.libman.server.exception.BadRequestException;
import com.ikoval.libman.server.exception.BookNotFoundException;
import com.ikoval.libman.server.filter.BookSpecification;
import com.ikoval.libman.server.service.AuthorService;
import com.ikoval.libman.server.service.BookGenreService;
import com.ikoval.libman.server.service.BookService;
import com.ikoval.libman.shared.FilterCriteria;
import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.shared.dto.MyPageResponse;
import com.ikoval.libman.shared.dto.MyPageRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST Controller for {@link Book} entity
 *
 */


@RestController
@RequestMapping("libman/api")
@AllArgsConstructor
public class BookRestController {

    private BookService bookService;

    private AuthorService authorService;

    private BookGenreService bookGenreService;

    /**
     * Return a {@link MyPageResponse} of {@link BookDto} matching given {@link MyPageRequest}
     * and {@link FilterCriteria} if not {@literal null}.
     *
     * @param myPageRequest must not be {@literal null}.
     * @return response satisfying by given {@code myPageRequest}.
     * @throws BadRequestException when myPageRequest is {@literal null}.
     */

    @PostMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public MyPageResponse<BookDto> findBooksByPageRequest(@RequestBody MyPageRequest myPageRequest) throws BadRequestException {
        PageRequest pageRequest = MyPageRequestConverter.convert(myPageRequest);
        FilterCriteria filterCriteria = myPageRequest.getFilter();
        Specification<Book> spec = filterCriteria != null ? new BookSpecification(filterCriteria) : null;
        Page<Book> pageResponse = bookService.findAll(spec,pageRequest);
        return buildMyPageResponseDto(pageResponse);
    }

    /**
     * Retrieved book by given id.
     *
     * @param id must not be {@literal null}.
     * @return BookDto with given id.
     * @throws BookNotFoundException in case when entity of {@link Book} wasn't found.
     */

    @GetMapping(value = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDto getBookById(@PathVariable Long id) throws BookNotFoundException {
        return buildBookDto(bookService.findById(id));
    }

    /**
     * Delete book by given {@code id}.
     *
     * @param id must not be {@literal null}.
     * @throws BookNotFoundException in case when entity of {@link Book} wasn't found.
     */

    @DeleteMapping(value = "/book/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@RequestParam Long id) throws BookNotFoundException {
        bookService.findById(id);
        bookService.delete(id);
    }

    /**
     * Saves book.
     *
     * @param bookDto must not be {@literal} null.
     * @throws BadRequestException when book with given id is already exist
     */

    @PostMapping(value = "/book/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void saveBook(@Valid @RequestBody BookDto bookDto) throws BadRequestException {
        if(bookDto.getId() != null) {
            try {
                bookService.findById(bookDto.getId());
                throw new BadRequestException("Book with given id is already exist");
            } catch (BookNotFoundException e) {
                e.printStackTrace();
            }
        }
        Book book = convertToBook(bookDto);
        bookService.save(book);
    }

    /**
     * Converts {@link Book} to {@link BookDto}
     *
     * @param book must not be null
     * @return {@literal null} if input value was {@literal null};
     */

    private BookDto buildBookDto(Book book) {
        if(book != null) {
            BookDto bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setTitle(book.getTitle());
            bookDto.setPublisher(book.getPublisher());
            bookDto.setYearOfPublishing(book.getYearOfPublishing());
            bookDto.setGenres(
                    book.getGenres()
                            .stream()
                            .map(BookGenre::getName)
                            .collect(Collectors.joining(", ")));
            bookDto.setAuthors(
                    book.getAuthors()
                            .stream()
                            .map(Author::getFullName)
                            .collect(Collectors.joining(", ")));
            bookDto.setPages(book.getPages());
            bookDto.setAddedDate(book.getAddedDate().toString());
            return bookDto;
        }
        return null;
    }

    /**
     * Converts {@link Page<Book>} to {@link MyPageResponse<BookDto>}
     *
     * @param page must not be {@literal null}
     * @return can't be {@literal null}
     */
    private MyPageResponse<BookDto> buildMyPageResponseDto(final Page<Book> page) {
        List<BookDto> bookDtos = page.stream()
                .map(entity -> buildBookDto(entity))
                .collect(Collectors.toList());
        MyPageResponse<BookDto> response = new MyPageResponse<>();
        response.setContent(bookDtos);
        response.setTotalElements((int) page.getTotalElements());
        response.setLast(page.isLast());
        return response;
    }

    /**
     *Converts {@link BookDto} into {@link Book}
     *
     * @param bookDto must not be {@literal null}
     * @return {@link Book} corresponding to given {@link BookDto}
     */


    private Book convertToBook(BookDto bookDto) {
        Book book = new Book();
        if(bookDto.getId() != null) book.setId(bookDto.getId());
        if(bookDto.getTitle() != null) book.setTitle(bookDto.getTitle());
        if (bookDto.getAddedDate() != null) book.setAddedDate(parseDate(bookDto.getAddedDate()));
        if(bookDto.getPublisher() != null) book.setPublisher(bookDto.getPublisher());
        if(bookDto.getPages() != null) book.setPages(bookDto.getPages());
        if(bookDto.getYearOfPublishing() != null) book.setYearOfPublishing(bookDto.getYearOfPublishing());
        if(bookDto.getAuthors() != null) {
            List<Author> authors = authorService.convertToListOfAuthors(bookDto.getAuthors());
            book.setAuthors(authors);
        }
        if(bookDto.getGenres() != null) {
            List<BookGenre> genres = bookGenreService.convertToListOfGenres(bookDto.getGenres());
            book.setGenres(genres);
        }
        return book;
    }

    /**
     * Return {@link Date} matching given string or current time when string is unparseable.
     *
     */

    private Date parseDate(String dateStr) {
        Long time = null;
        try {
            time = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr).getTime();
        } catch (ParseException e) {
            System.out.println("Unparseable string");
        }
        return time != null ? new Date(time) : new Date(new java.util.Date().getTime());
    }



}
