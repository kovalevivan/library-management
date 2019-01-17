package com.ikoval.libman.server.controller;

import com.ikoval.libman.server.converter.BookConverter;
import com.ikoval.libman.server.converter.MyPageRequestConverter;
import com.ikoval.libman.server.converter.MyPageResponseConverter;
import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.exception.BadRequestException;
import com.ikoval.libman.server.filter.BookSpecification;
import com.ikoval.libman.server.service.BookService;
import com.ikoval.libman.server.service.MyConversionService;
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

/**
 * REST Controller for {@link com.ikoval.libman.server.domain.Book} entity
 *
 * @author Ivan Kovalev
 */


@RestController
@RequestMapping("libman/api")
@AllArgsConstructor
public class BookRestController {

    private BookService bookService;

    private MyConversionService conversionService;

    /**
     * Retrieved books with pagination and sorting
     *
     * @param myPageRequest must not be {@literal null}
     * @return response satisfying by given {@code myPageRequest}
     */

    @PostMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public MyPageResponse<BookDto> findBooksByPageRequest(@RequestBody MyPageRequest myPageRequest) {
        PageRequest pageRequest = MyPageRequestConverter.convert(myPageRequest);
        Page<Book> pageResponse = bookService.findAll(pageRequest);
        return MyPageResponseConverter.convert(pageResponse);
    }

    /**
     * Retrieved books with pagination, sorting and filtering
     *
     * @param myPageRequest must not be {@literal null}
     * @return response satisfying by given {@code myPageRequest}
     * @throws BadRequestException if {@code filter} is null
     */

    @PostMapping(value = "/books/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public MyPageResponse<BookDto> findBookByPageRequestWithFilter(@RequestBody MyPageRequest myPageRequest) throws BadRequestException {
        PageRequest pageRequest = MyPageRequestConverter.convert(myPageRequest);
        FilterCriteria filter = myPageRequest.getFilter();
        if(filter == null) throw new BadRequestException("Filter must not be null");
        Specification<Book> spec = new BookSpecification(filter);
        Page pageResponse = bookService.findAll(spec,pageRequest);
        return MyPageResponseConverter.convert(pageResponse);
    }

    /**
     * Retrieved book by given id
     *
     * @param id must not be {@literal null}
     * @return BookDto with given id
     * @throws BadRequestException in case when book wasn't found
     */

    @GetMapping(value = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDto getBookById(@PathVariable Long id) throws BadRequestException {
        return BookConverter.convert(bookService.findById(id));
    }

    /**
     * Delete book by given {@code id}
     *
     * @param id must not be {@literal null}
     * @throws BadRequestException in case when book wasn't found
     */

    @DeleteMapping(value = "/book/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@RequestParam Long id) throws BadRequestException {
        bookService.findById(id);
        bookService.delete(id);
    }

    /**
     * Saves book
     *
     * @param bookDto must not be {@literal} null
     */

    @PostMapping(value = "/book/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void saveBook(@Valid @RequestBody BookDto bookDto) {
        Book book = conversionService.convert(bookDto);
        bookService.save(book);
    }

}
