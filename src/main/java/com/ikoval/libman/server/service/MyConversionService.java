package com.ikoval.libman.server.service;

import com.ikoval.libman.server.converter.BookConverter;
import com.ikoval.libman.server.converter.MyPageRequestConverter;
import com.ikoval.libman.server.converter.MyPageResponseConverter;
import com.ikoval.libman.server.domain.Author;
import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.domain.BookGenre;
import com.ikoval.libman.server.repository.AuthorRepository;
import com.ikoval.libman.server.repository.BookGenreRepository;
import com.ikoval.libman.server.repository.BookRepository;
import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.shared.dto.MyPageRequest;
import com.ikoval.libman.shared.dto.MyPageResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MyConversionService {

    private AuthorRepository authorRepository;

    private BookGenreRepository bookGenreRepository;

    private BookRepository bookRepository;


    public Book convert(BookDto bookDto) {
        Book book = new Book();
        if(bookDto.getId() != null) {
            Optional<Book> opt = bookRepository.findById(bookDto.getId());
            if(opt.isPresent()) {
                book = opt.get();
            }
        }
        book.setTitle(bookDto.getTitle());
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (bookDto.getAddedDate() != null) {
            try {
                book.setAddedDate(new Date(sdf.parse(bookDto.getAddedDate()).getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
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
