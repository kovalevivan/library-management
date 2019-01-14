package com.ikoval.libman.server.converter;

import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.shared.dto.BookDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

public class BookConverter {


    public  static BookDto convert(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setPublisher(book.getPublisher());
        bookDto.setYearOfPublishing(book.getYearOfPublishing());
        bookDto.setGenres(
                book.getGenres()
                        .stream()
                        .map(genre->genre.getName())
                        .collect(Collectors.joining(", ")));
        bookDto.setAuthors(
                book.getAuthors()
                        .stream()
                        .map(author -> author.getFullName())
                        .collect(Collectors.joining(", ")));
        bookDto.setPages(book.getPages());
        bookDto.setAddedDate(book.getAddedDate().toString());
        return bookDto;
    }
}
