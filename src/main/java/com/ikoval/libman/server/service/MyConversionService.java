package com.ikoval.libman.server.service;

import com.ikoval.libman.server.domain.Author;
import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.domain.BookGenre;
import com.ikoval.libman.server.repository.AuthorRepository;
import com.ikoval.libman.server.repository.BookGenreRepository;
import com.ikoval.libman.server.repository.BookRepository;
import com.ikoval.libman.shared.dto.BookDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MyConversionService {

    private AuthorRepository authorRepository;

    private BookGenreRepository bookGenreRepository;

    private BookRepository bookRepository;


    public Book convert(BookDto bookDto) {
        Book book = new Book();
        if(bookDto.getId() != null) book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAddedDate(new Date(new java.util.Date().getTime()));
        book.setPublisher(bookDto.getPublisher());
        book.setPages(bookDto.getPages());
        book.setYearOfPublishing(bookDto.getYearOfPublishing());
        List<Author> authors = convertToListOfAuthors(bookDto.getAuthors());
        book.setAuthors(authors);
        List<BookGenre> genres = convertToListOfGenres(bookDto.getGenres());
        book.setGenres(genres);
        return book;
    }

    private List<Author> convertToListOfAuthors(String string) {
        String[] authorsString = string.split(",");
        List<Author> list = new ArrayList<>();
        for(String s : authorsString) {
            Author author = authorRepository.findByFullName(s);
            if(author == null) {
                author = new Author(s);
                authorRepository.save(author);
            }
            list.add(author);
        }
        return list;
    }

    private List<BookGenre> convertToListOfGenres(String string) {
        String[] genres = string.split(",");
        List<BookGenre> list = new ArrayList<>();
        for(String s : genres) {
            BookGenre genre = bookGenreRepository.findByName(s);
            if(genre == null) {
                genre = new BookGenre(s);
                bookGenreRepository.save(genre);
            }
            list.add(genre);
        }
        return list;
    }


}
