package com.ikoval.libman.server.controller;

import com.ikoval.libman.server.dto.BookResponseDto;
import com.ikoval.libman.server.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class LibraryManagementController {

    BookService bookService;

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookResponseDto> getAllBooks() {
        return bookService.getAllBooks();
    }

}
