package com.ikoval.libman.server.converter;


import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.shared.dto.BookDto;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

public class BookConverter {

    public static BookDto convertToBookResponseDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setPublisher(book.getPublisher());
        bookDto.setYearOfPublishing(book.getYearOfPublishing());
        bookDto.setGenres(
                book.getGenres()
                .stream()
                .map(genre->genre.getName())
                .collect(Collectors.toList()));
        bookDto.setAuthors(book.getAuthorsString());
        bookDto.setPages(book.getPages());
        bookDto.setAddedDate(book.getAddedDate().toString());
        return bookDto;
    }

    public static Book convertToBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (bookDto.getAddedDate() != null) {
            try {
                book.setAddedDate(new Date(sdf.parse(bookDto.getAddedDate()).getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        book.setAuthorsString(bookDto.getAuthors());
//        book.setGenres();
        book.setPages(bookDto.getPages());
        book.setYearOfPublishing(bookDto.getYearOfPublishing());
        return book;
    }
}
