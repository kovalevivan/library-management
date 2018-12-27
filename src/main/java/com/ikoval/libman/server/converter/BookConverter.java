package com.ikoval.libman.server.converter;


import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.shared.BookResponseDto;

import java.util.stream.Collectors;

public class BookConverter {

    public static BookResponseDto convertToBookResponseDto(Book book) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(book.getId());
        bookResponseDto.setTitle(book.getTitle());
        bookResponseDto.setPublisher(book.getPublisher());
        bookResponseDto.setYearOfPublishing(book.getYearOfPublishing());
        bookResponseDto.setGenres(
                book.getGenres()
                .stream()
                .map(genre->genre.getName())
                .collect(Collectors.toList()));
        bookResponseDto.setAuthors(
                book.getAuthors()
                .stream()
                .map(author->author.getFullName())
                .collect(Collectors.toList()));
        bookResponseDto.setPages(book.getPages());
        bookResponseDto.setAddedDate(book.getAddedDate().toString());
        return bookResponseDto;
    }
}
