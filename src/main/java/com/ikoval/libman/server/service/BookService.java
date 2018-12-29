package com.ikoval.libman.server.service;

import com.ikoval.libman.shared.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    Page<BookDto> getAllBooks(Pageable pageable);

    List<BookDto> getAllBooks();

    BookDto getById(Long id);

    void deleteById(Long id);

    void save(BookDto book);
}
