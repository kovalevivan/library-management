package com.ikoval.libman.server.domain;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
@Data
public class Book {

    @Id @GeneratedValue
    private Long id;
    private String title;
    private String publisher;
    @Column(name = "year")
    private Integer yearOfPublishing;
    @ManyToMany(mappedBy = "booksOfThisGenre")
    private List<BookGenre> genres;
    @ManyToMany(mappedBy = "writtenByAuthor")
    private List<Author> authors;
    private Integer pages;
    @Column(name = "added")
    private Date addedDate;
}
