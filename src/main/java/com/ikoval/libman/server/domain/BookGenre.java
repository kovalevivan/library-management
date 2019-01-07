package com.ikoval.libman.server.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class BookGenre {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<Book> booksOfThisGenre;

    public BookGenre() {
    }

    public BookGenre(String name, List<Book> booksOfThisGenre) {
        this.name = name;
        this.booksOfThisGenre = booksOfThisGenre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooksOfThisGenre() {
        return booksOfThisGenre;
    }

    public void setBooksOfThisGenre(List<Book> booksOfThisGenre) {
        this.booksOfThisGenre = booksOfThisGenre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookGenre)) return false;
        BookGenre bookGenre = (BookGenre) o;
        return Objects.equals(getId(), bookGenre.getId()) &&
                Objects.equals(getName(), bookGenre.getName()) &&
                Objects.equals(getBooksOfThisGenre(), bookGenre.getBooksOfThisGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBooksOfThisGenre());
    }
}
