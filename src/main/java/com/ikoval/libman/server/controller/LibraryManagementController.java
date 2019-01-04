package com.ikoval.libman.server.controller;

import com.ikoval.libman.server.converter.MyPageResponseConverter;
import com.ikoval.libman.server.converter.MyPageRequestConverter;
import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.server.service.BookService;
import com.ikoval.libman.shared.dto.MyPageResponse;
import com.ikoval.libman.shared.dto.MyPageRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("demogwt/api")
@AllArgsConstructor
public class LibraryManagementController {

    BookService bookService;

    @PostMapping(value = "/bookswithpagination", produces = MediaType.APPLICATION_JSON_VALUE)
    public MyPageResponse<BookDto> getAllBooksWithPagination(@RequestBody MyPageRequest myPageRequestDto) {
        org.springframework.data.domain.PageRequest pageRequest = MyPageRequestConverter.convert(myPageRequestDto);
        Page<BookDto> page = bookService.getAllBooks(pageRequest);
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

    @DeleteMapping(value = "/book/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @PostMapping(value = "/book/save",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveBook(@RequestBody BookDto book) {
        bookService.save(book);
    }
}
