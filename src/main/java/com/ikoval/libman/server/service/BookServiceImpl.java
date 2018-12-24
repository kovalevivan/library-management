package com.ikoval.libman.server.service;

import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.dto.BookResponseDto;
import com.ikoval.libman.server.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    BookRepository repository;

    @Override
    public Page<BookResponseDto> getAllBooks(Pageable pageable) {
        Page<Book> books = repository.findAll(pageable);
        long totalElement = books.getTotalElements();
        List<BookResponseDto> bookResponseDtos = books.stream()
                .map(entity -> new BookResponseDto(entity))
                .collect(Collectors.toList());
        return new PageImpl<BookResponseDto>(bookResponseDtos,pageable,totalElement);
    }

    @Override
    public BookResponseDto getById(Long id) {
        Optional<Book> book = repository.findById(id);
        return book.isPresent() ? new BookResponseDto(book.get()) : new BookResponseDto();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void save(Book book) {
        repository.save(book);
    }


}
