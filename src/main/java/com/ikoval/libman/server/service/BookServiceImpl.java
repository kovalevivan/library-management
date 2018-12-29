package com.ikoval.libman.server.service;

import com.ikoval.libman.server.converter.BookConverter;
import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.shared.dto.BookDto;
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
    public Page<BookDto> getAllBooks(Pageable pageable) {
        Page<Book> books = repository.findAll(pageable);
        long totalElement = books.getTotalElements();
        List<BookDto> bookDtos = books.stream()
                .map(entity -> BookConverter.convertToBookResponseDto(entity))
                .collect(Collectors.toList());
        return new PageImpl<BookDto>(bookDtos,pageable,totalElement);
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = (List<Book>) repository.findAll();
        return books.stream()
                .map(entity -> BookConverter.convertToBookResponseDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public BookDto getById(Long id) {
        Optional<Book> book = repository.findById(id);
        return book.isPresent() ? BookConverter.convertToBookResponseDto(book.get()) : new BookDto();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void save(BookDto book) {
        repository.save(BookConverter.convertToBook(book));
    }


}
