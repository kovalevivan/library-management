package com.ikoval.libman.shared;

public class FilterCriteria {

    private String authorName;
    private String bookTitle;
    private String genre;
    private String publisher;
    private Integer yearOfPublishingBefore;
    private Integer yearOfPublishingAfter;
    private Integer numberOfPagesLessThan;
    private Integer numberOfPagesGreaterThan;


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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getYearOfPublishingBefore() {
        return yearOfPublishingBefore;
    }

    public void setYearOfPublishingBefore(Integer yearOfPublishingBefore) {
        this.yearOfPublishingBefore = yearOfPublishingBefore;
    }

    public Integer getYearOfPublishingAfter() {
        return yearOfPublishingAfter;
    }

    public void setYearOfPublishingAfter(Integer yearOfPublishingAfter) {
        this.yearOfPublishingAfter = yearOfPublishingAfter;
    }

    public Integer getNumberOfPagesLessThan() {
        return numberOfPagesLessThan;
    }

    public void setNumberOfPagesLessThan(Integer numberOfPagesLessThan) {
        this.numberOfPagesLessThan = numberOfPagesLessThan;
    }

    public Integer getNumberOfPagesGreaterThan() {
        return numberOfPagesGreaterThan;
    }

    public void setNumberOfPagesGreaterThan(Integer numberOfPagesGreaterThan) {
        this.numberOfPagesGreaterThan = numberOfPagesGreaterThan;
    }

    public void clear() {
        authorName = null;
        bookTitle = null;
        genre = null;
        publisher = null;
        yearOfPublishingBefore = null;
        yearOfPublishingAfter = null;
        numberOfPagesGreaterThan = null;
        numberOfPagesLessThan = null;
    }
}
