package com.ikoval.libman.server.dto;

import com.ikoval.libman.server.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BookResponseDto {

    private Long id;
    private String title;
    private List<String> genres;
    private List<String> authors;

    public BookResponseDto(Book book) {
        id = book.getId();
        title = book.getTitle();
        genres = book.getGenres()
                .stream()
                .map(genre->genre.getName())
                .collect(Collectors.toList());
        authors = book.getAuthors()
                .stream()
                .map(author->author.getFullName())
                .collect(Collectors.toList());
    }
}
