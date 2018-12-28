package com.ikoval.libman.server.service;

import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.shared.BookResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    Page<BookResponseDto> getAllBooks(Pageable pageable);

    List<BookResponseDto> getAllBooks();

    BookResponseDto getById(Long id);

    void deleteById(Long id);

    void save(BookResponseDto book);
}
