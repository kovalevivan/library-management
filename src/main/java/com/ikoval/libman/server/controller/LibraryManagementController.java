package com.ikoval.libman.server.controller;

import com.ikoval.libman.shared.BookResponseDto;
import com.ikoval.libman.server.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("demogwt/api")
@AllArgsConstructor
public class LibraryManagementController {

    BookService bookService;

    @GetMapping(value = "/bookswithpagination", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<BookResponseDto> getAllBooksWithPagination(@PageableDefault(sort = "id")Pageable pageable) {
        return bookService.getAllBooks(pageable);
    }

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookResponseDto> getAllBooks() {
        return bookService.getAllBooks();
    }


    @GetMapping(value = "/book/{id}")
    public BookResponseDto getBook(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @DeleteMapping(value = "/book/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @PostMapping(value = "/book/save",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveBook(@RequestBody BookResponseDto book) {
        bookService.save(book);
    }
}
