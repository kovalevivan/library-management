package com.ikoval.libman.server.service;

import com.ikoval.libman.server.converter.BookConverter;
import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.server.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    BookRepository bookRepository;


    public List<BookDto> getAllBooks() {
        List<Book> books = (List<Book>) bookRepository.findAll();
        return books.stream()
                .map(entity -> BookConverter.convertToBookResponseDto(entity))
                .collect(Collectors.toList());
    }

    public BookDto getById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.isPresent() ? BookConverter.convertToBookResponseDto(book.get()) : new BookDto();
    }

    public void delete(BookDto bookDto) {
        bookRepository.deleteById(bookDto.getId());
    }


}
