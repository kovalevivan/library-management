package com.ikoval.libman.server.service;

import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.shared.dto.BookDto;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    Page<BookDto> getAllBooks(Pageable pageable);

    Page<BookDto> findAll(BookDto bookDto, Pageable pageable);

    List<BookDto> getAllBooks();

    BookDto getById(Long id);

    void delete(BookDto bookDto);

    void save(BookDto book);
}
