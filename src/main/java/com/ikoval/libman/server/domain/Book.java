package com.ikoval.libman.server.domain;



import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;


@Entity
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Book() {
    }

    public Book(String title, String publisher, Integer yearOfPublishing, List<BookGenre> genres, List<Author> authors, Integer pages, Date addedDate) {
        this.title = title;
        this.publisher = publisher;
        this.yearOfPublishing = yearOfPublishing;
        this.genres = genres;
        this.authors = authors;
        this.pages = pages;
        this.addedDate = addedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(Integer yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public List<BookGenre> getGenres() {
        return genres;
    }

    public void setGenres(List<BookGenre> genres) {
        this.genres = genres;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getId(), book.getId()) &&
                Objects.equals(getTitle(), book.getTitle()) &&
                Objects.equals(getPublisher(), book.getPublisher()) &&
                Objects.equals(getYearOfPublishing(), book.getYearOfPublishing()) &&
                Objects.equals(getGenres(), book.getGenres()) &&
                Objects.equals(getAuthors(), book.getAuthors()) &&
                Objects.equals(getPages(), book.getPages()) &&
                Objects.equals(getAddedDate(), book.getAddedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getPublisher(), getYearOfPublishing(), getGenres(), getAuthors(), getPages(), getAddedDate());
    }
}
