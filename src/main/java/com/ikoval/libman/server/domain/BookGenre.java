package com.ikoval.libman.server.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class BookGenre {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "jnd_genre_book",
            joinColumns = @JoinColumn(name = "book_fk"),
            inverseJoinColumns = @JoinColumn(name = "genre_fk"))
    private List<Book> booksOfThisGenre;

}
