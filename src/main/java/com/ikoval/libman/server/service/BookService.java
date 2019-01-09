package com.ikoval.libman.server.service;

import com.ikoval.libman.shared.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BookService {
    Page<BookDto> getAllBooks(Pageable pageable);

    Page<BookDto> findAll(BookDto bookDto, Pageable pageable);

    Page<BookDto> findAll(Specification spec, Pageable pageable);

    List<BookDto> getAllBooks();

    BookDto getById(Long id);

    void delete(BookDto bookDto);

    void save(BookDto book);
}
