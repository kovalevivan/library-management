package com.ikoval.libman.server.converter;

import com.ikoval.libman.server.domain.Author;
import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.domain.BookGenre;
import com.ikoval.libman.shared.dto.BookDto;

import java.util.stream.Collectors;

public class BookConverter {


    public  static BookDto convert(final Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setPublisher(book.getPublisher());
        bookDto.setYearOfPublishing(book.getYearOfPublishing());
        bookDto.setGenres(
                book.getGenres()
                        .stream()
                        .map(BookGenre::getName)
                        .collect(Collectors.joining(", ")));
        bookDto.setAuthors(
                book.getAuthors()
                        .stream()
                        .map(Author::getFullName)
                        .collect(Collectors.joining(", ")));
        bookDto.setPages(book.getPages());
        bookDto.setAddedDate(book.getAddedDate().toString());
        return bookDto;
    }
}
