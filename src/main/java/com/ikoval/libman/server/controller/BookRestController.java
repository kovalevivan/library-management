package com.ikoval.libman.server.controller;

import com.ikoval.libman.server.converter.BookConverter;
import com.ikoval.libman.server.converter.MyPageRequestConverter;
import com.ikoval.libman.server.converter.MyPageResponseConverter;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("libman/api")
@AllArgsConstructor
public class BookRestController {

    BookService bookService;

    MyConversionService conversionService;

    @PostMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public MyPageResponse<BookDto> findBooksByPageRequest(@RequestBody MyPageRequest myPageRequest) {
        PageRequest pageRequest = MyPageRequestConverter.convert(myPageRequest);
        Page<Book> pageResponse = bookService.findAll(pageRequest);
        return MyPageResponseConverter.convert(pageResponse);
    }

    @PostMapping(value = "/books/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public MyPageResponse<BookDto> findBookByPageRequestWithFilter(@RequestBody MyPageRequest myPageRequest) {
        PageRequest pageRequest = MyPageRequestConverter.convert(myPageRequest);
        FilterCriteria filter = myPageRequest.getFilter();
        Specification<Book> spec = MySpecification.filterBook(filter);
        Page pageResponse = bookService.findAll(spec,pageRequest);
        return MyPageResponseConverter.convert(pageResponse);
    }

    @GetMapping(value = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDto getBookById(@PathVariable Long id) {
        Optional<Book> bookOptional = bookService.getById(id);
        Book book = bookOptional.isPresent() ? bookOptional.get() : new Book();
        return BookConverter.convert(book);
    }

    @DeleteMapping(value = "/book/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@RequestBody BookDto bookDto) {
        bookService.delete(bookDto.getId());
    }

    @PostMapping(value = "/book/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void saveBook(@RequestBody BookDto bookDto) throws Exception {
        Book book = conversionService.convert(bookDto);
        bookService.save(book);
    }

}
