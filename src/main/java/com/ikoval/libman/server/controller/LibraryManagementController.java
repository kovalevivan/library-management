package com.ikoval.libman.server.controller;

import com.ikoval.libman.server.service.BookService;
import com.ikoval.libman.server.service.LibraryManagementService;
import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.shared.dto.MyPageResponse;
import com.ikoval.libman.shared.dto.MyPageRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("libman/api")
@AllArgsConstructor
public class LibraryManagementController {

    BookService bookService;

    LibraryManagementService libraryManagementService;

    @PostMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public MyPageResponse<BookDto> findBooksByPageRequest(@RequestBody MyPageRequest myPageRequest) {
        return libraryManagementService.findAll(myPageRequest);
    }

    @GetMapping(value = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @DeleteMapping(value = "/book/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteBook(@RequestBody BookDto bookDto) {
        bookService.delete(bookDto);
    }

    @PostMapping(value = "/book/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveBook(@RequestBody BookDto book) {
        libraryManagementService.save(book);
    }
}
