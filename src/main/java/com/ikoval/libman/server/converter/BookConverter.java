package com.ikoval.libman.server.converter;


import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.shared.BookResponseDto;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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

    public static Book converToBook(BookResponseDto bookResponseDto) {
        Book book = new Book();
        book.setTitle(bookResponseDto.getTitle());
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (bookResponseDto.getAddedDate() != null) {
            try {
                book.setAddedDate(new Date(sdf.parse(bookResponseDto.getAddedDate()).getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
//        book.setAuthors();
//        book.setGenres();
        book.setPages(bookResponseDto.getPages());
        book.setYearOfPublishing(bookResponseDto.getYearOfPublishing());
        return book;
    }
}
