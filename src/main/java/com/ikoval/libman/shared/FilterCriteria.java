package com.ikoval.libman.shared;

import java.util.Objects;

public class FilterCriteria {

    private String authorName;
    private String bookTitle;
    private String genre;


    public FilterCriteria() {
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void clear() {
        authorName = null;
        bookTitle = null;
        genre = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilterCriteria)) return false;
        FilterCriteria that = (FilterCriteria) o;
        return Objects.equals(getAuthorName(), that.getAuthorName()) &&
                Objects.equals(getBookTitle(), that.getBookTitle()) &&
                Objects.equals(getGenre(), that.getGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthorName(), getBookTitle(), getGenre());
    }
}
