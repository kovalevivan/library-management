package com.ikoval.libman.server.service;

import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.dto.BookResponseDto;
import com.ikoval.libman.server.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    BookRepository repository;

    @Override
    public List<BookResponseDto> getAllBooks() {
        List<Book> books = repository.findAll();
        return books.stream().map(entity -> new BookResponseDto(entity.getId(),
                entity.getTitle(),
                entity.getGenres()
                        .stream().map(genre->genre.getName()).collect(Collectors.toList()),
                entity.getAuthors()
                        .stream().map(author->author.getFullName()).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}
