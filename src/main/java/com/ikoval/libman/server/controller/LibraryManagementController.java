package com.ikoval.libman.server.controller;

import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.filter.MySpecification;
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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("libman/api")
@AllArgsConstructor
public class LibraryManagementController {

    BookService bookService;

    MyConversionService conversionService;

    @PostMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public MyPageResponse<BookDto> findBooksByPageRequest(@RequestBody MyPageRequest myPageRequest) {
        PageRequest pageRequest = conversionService.convert(myPageRequest);
        Page<Book> pageResponse;
        if(myPageRequest.getFilter() == null) {
            pageResponse = bookService.findAll(pageRequest);
        } else {
            FilterCriteria filter = myPageRequest.getFilter();
            Specification<Book> spec = MySpecification.filterBook(filter);
            pageResponse = bookService.findAll(spec,pageRequest);
        }
        return conversionService.convert(pageResponse);
    }

    @GetMapping(value = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDto getBookById(@PathVariable Long id) {
        Book book = bookService.getById(id);
        return conversionService.convert(book);
    }

    @DeleteMapping(value = "/book/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteBook(@RequestBody BookDto bookDto) {
        bookService.delete(bookDto.getId());
    }

    @PostMapping(value = "/book/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveBook(@RequestBody BookDto bookDto) {
        Book book = conversionService.convert(bookDto);
        bookService.save(book);
    }
}
