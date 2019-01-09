package com.ikoval.libman.server.controller;

import com.ikoval.libman.server.converter.BookConverter;
import com.ikoval.libman.server.converter.MyPageResponseConverter;
import com.ikoval.libman.server.converter.MyPageRequestConverter;
import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.filter.MySpecification;
import com.ikoval.libman.server.service.AuthorService;
import com.ikoval.libman.shared.FilterCriteria;
import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.server.service.BookService;
import com.ikoval.libman.shared.dto.MyPageResponse;
import com.ikoval.libman.shared.dto.MyPageRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("demogwt/api")
@AllArgsConstructor
public class LibraryManagementController {

    BookService bookService;
    AuthorService authorService;

    @PostMapping(value = "/bookswithpagination", produces = MediaType.APPLICATION_JSON_VALUE)
    public MyPageResponse<BookDto> getAllBooksWithPagination(@RequestBody MyPageRequest myPageRequest) {
        PageRequest pageRequest = MyPageRequestConverter.convert(myPageRequest);
        Page<BookDto> page;
        if(myPageRequest.getFilter() == null) {
            page = bookService.getAllBooks(pageRequest);
        } else {
            FilterCriteria filter = myPageRequest.getFilter();
            page = bookService.findAll(MySpecification.filterBook(filter),pageRequest);
        }
        return MyPageResponseConverter.convert(page);
    }

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }


    @GetMapping(value = "/book/{id}")
    public BookDto getBook(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @DeleteMapping(value = "/book/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteBook(@RequestBody BookDto bookDto) {
        bookService.delete(bookDto);
    }

    @PostMapping(value = "/book/save",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveBook(@RequestBody BookDto book) {
        bookService.save(book);
    }
}
