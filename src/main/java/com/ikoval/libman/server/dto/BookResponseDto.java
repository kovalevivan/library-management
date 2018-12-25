package com.ikoval.libman.server.dto;

import com.ikoval.libman.server.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BookResponseDto {

    private Long id;
    private String title;
    private String publisher;
    private Integer yearOfPublishing;
    private List<String> genres;
    private List<String> authors;
    private Integer pages;
    private String addedDate;

    public BookResponseDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.publisher = book.getPublisher();
        this.yearOfPublishing = book.getYearOfPublishing();
        this.genres = book.getGenres()
                .stream()
                .map(genre->genre.getName())
                .collect(Collectors.toList());
        this.authors = book.getAuthors()
                .stream()
                .map(author->author.getFullName())
                .collect(Collectors.toList());
        this.pages = book.getPages();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.addedDate = dateFormat.format(book.getAddedDate());
    }
}
