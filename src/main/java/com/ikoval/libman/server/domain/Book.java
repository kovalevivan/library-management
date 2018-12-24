package com.ikoval.libman.server.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class Book {

    @Id @GeneratedValue
    private Long id;
    private String title;
    @ManyToMany(mappedBy = "booksOfThisGenre")
    private List<BookGenre> genres;
    @ManyToMany(mappedBy = "writtenByAuthor")
    private List<Author> authors;
}
