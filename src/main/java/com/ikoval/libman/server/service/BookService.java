package com.ikoval.libman.server.service;

import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.dto.BookResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    Page<BookResponseDto> getAllBooks(Pageable pageable);

    BookResponseDto getById(Long id);

    void delete(Long id);

    void save(Book book);
}
