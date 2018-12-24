package com.ikoval.libman.server.controller;

import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.dto.BookResponseDto;
import com.ikoval.libman.server.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class LibraryManagementController {

    BookService bookService;

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<BookResponseDto> getAllBooks(@PageableDefault(sort = "id")Pageable pageable) {
        return bookService.getAllBooks(pageable);
    }

    @GetMapping(value = "/book/{id}")
    public BookResponseDto getBook(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @DeleteMapping(value = "/book/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }

    @PostMapping(value = "/book/save")
    public void saveBook(Book book) {
        bookService.save(book);
    }
}
