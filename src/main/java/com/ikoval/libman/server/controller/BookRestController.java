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
import com.ikoval.libman.shared.ApplicationEndpoints;
import com.ikoval.libman.shared.FilterCriteria;
import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.shared.dto.MyPageResponse;
import com.ikoval.libman.shared.dto.MyPageRequest;
import lombok.AllArgsConstructor;
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
 * REST Controller for a {@link Book} entity.
 *
 */


@RestController
@RequestMapping(ApplicationEndpoints.ROOT_PATH)
@AllArgsConstructor
public class BookRestController {

    private BookService bookService;

    private AuthorService authorService;

    private BookGenreService bookGenreService;

    /**
     * Return a {@link MyPageResponse} of {@link BookDto} matching the given {@link MyPageRequest}
     * and {@link FilterCriteria} if not {@literal null}.
     *
     * @param myPageRequest must not be {@literal null}.
     * @return response satisfying the given {@code myPageRequest}.
     * @throws BadRequestException when myPageRequest is {@literal null}.
     */

    @PostMapping(value = ApplicationEndpoints.FIND_BOOKS, produces = MediaType.APPLICATION_JSON_VALUE)
    public MyPageResponse<BookDto> findBooks(@RequestBody MyPageRequest myPageRequest) throws BadRequestException {
        PageRequest pageRequest = MyPageRequestConverter.convert(myPageRequest);
        FilterCriteria filterCriteria = myPageRequest.getFilter();
        Specification<Book> spec = filterCriteria != null ? new BookSpecification(filterCriteria) : null;
        Page<Book> pageResponse = bookService.findAll(spec,pageRequest);
        return buildMyPageResponseDto(pageResponse);
    }

    /**
     * Retrieve a book by the given id.
     *
     * @param id must not be {@literal null}.
     * @return BookDto with the given id.
     * @throws BookNotFoundException in case no {@link Book} entity matches the id.
     */

    @GetMapping(value = ApplicationEndpoints.GET_BOOK, produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDto getBook(@PathVariable Long id) throws BookNotFoundException {
        return buildBookDto(bookService.findById(id));
    }

    /**
     * Delete a book by the given {@code id}.
     *
     * @param id must not be {@literal null}.
     * @throws BookNotFoundException in case no {@link Book} entity of  matches the id.
     */

    @DeleteMapping(value = ApplicationEndpoints.DELETE_BOOK)
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@RequestParam Long id) throws BookNotFoundException {
        bookService.findById(id);
        bookService.delete(id);
    }

    /**
     * Save a book.
     *
     * @param bookDto must not be {@literal} null.
     * @throws BadRequestException when the book with the given id already exists.
     */

    @PostMapping(value = ApplicationEndpoints.SAVE_BOOK, consumes = MediaType.APPLICATION_JSON_VALUE)
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
     * Find a {@link Book} by id and than update according to the {@link BookDto} parameters.
     *
     * @param bookDto must not be {@literal null}.
     * @throws BadRequestException in case when id of {@link BookDto} is undefined.
     * @throws BookNotFoundException in case no {@link Book} entity matches the given id.
     */

    @PutMapping(value = ApplicationEndpoints.UPDATE_BOOK, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@Valid @RequestBody BookDto bookDto) throws BadRequestException, BookNotFoundException {
        if(bookDto.getId() == null) throw new BadRequestException("Id is undefined");
        Book book = bookService.findById(bookDto.getId());
        Book updatedBook = convertToBook(bookDto,book);
        bookService.save(updatedBook);
    }

    /**
     * Return a new instance of {@link BookDto} with parameters matching the given {@link Book}.
     *
     * @param book must not be {@literal null}.
     * @return {@literal null} if input value was {@literal null}; {@link BookDto} object otherwise.
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
     * Convert a {@link Page<Book>} into a {@link MyPageResponse<BookDto>}.
     *
     * @param page must not be {@literal null}.
     * @return can't be {@literal null}.
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
     * Return a new instance of {@link Book} with parameters matching the given {@link BookDto}.
     *
     * @param bookDto must not be {@literal null}.
     * @return new instance of {@link Book}.
     */

    private Book convertToBook(BookDto bookDto) {
        return convertToBook(bookDto,new Book());
    }

    /**
     * Convert a {@link BookDto} into a {@link Book}.
     *
     * @param bookDto must not be {@literal null}.
     * @param book must not be {@literal null}.
     * @return {@link Book} corresponding to the given {@link BookDto}.
     */


    private Book convertToBook(BookDto bookDto, Book book) {
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
     * Return a {@link Date} matching the given string if it can be parsed; the current time otherwise.
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
