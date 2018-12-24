package com.ikoval.libman.server.service;

import com.ikoval.libman.server.dto.BookResponseDto;

import java.util.List;

public interface BookService {
    public List<BookResponseDto> getAllBooks();
}
