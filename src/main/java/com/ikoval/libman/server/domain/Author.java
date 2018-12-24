package com.ikoval.libman.server.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Author {

    @Id @GeneratedValue
    private Long id;
    private String fullName;

    @ManyToMany
    @JoinTable(name = "jnd_author_book",
            joinColumns = @JoinColumn(name = "author_fk"),
            inverseJoinColumns = @JoinColumn(name = "book_fk"))
    private List<Book> writtenByAuthor;

}
