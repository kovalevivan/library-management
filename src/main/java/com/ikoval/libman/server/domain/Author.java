package com.ikoval.libman.server.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Author {

    @Id @GeneratedValue
    private Long id;
    private String fullName;

    @ManyToMany(mappedBy = "authors")
    private List<Book> writtenByAuthor;

    public Author() {
    }

    public Author(String fullName) {
        this.fullName = fullName;
    }

    public Author(String fullName, List<Book> writtenByAuthor) {
        this.fullName = fullName;
        this.writtenByAuthor = writtenByAuthor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Book> getWrittenByAuthor() {
        return writtenByAuthor;
    }

    public void setWrittenByAuthor(List<Book> writtenByAuthor) {
        this.writtenByAuthor = writtenByAuthor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equals(getId(), author.getId()) &&
                Objects.equals(getFullName(), author.getFullName()) &&
                Objects.equals(getWrittenByAuthor(), author.getWrittenByAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFullName(), getWrittenByAuthor());
    }
}
