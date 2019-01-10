package com.ikoval.libman.server.service;

import com.ikoval.libman.server.domain.Author;
import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.domain.BookGenre;
import com.ikoval.libman.server.repository.AuthorRepository;
import com.ikoval.libman.server.repository.BookGenreRepository;
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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MyConversionService {

    AuthorRepository authorRepository;

    BookGenreRepository bookGenreRepository;

    public Book convert(BookDto bookDto) {
        Book book = new Book();
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

    public BookDto convert(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setPublisher(book.getPublisher());
        bookDto.setYearOfPublishing(book.getYearOfPublishing());
        bookDto.setGenres(
                book.getGenres()
                        .stream()
                        .map(genre->genre.getName())
                        .collect(Collectors.joining(", ")));
        bookDto.setAuthors(
                book.getAuthors()
                        .stream()
                        .map(author -> author.getFullName())
                        .collect(Collectors.joining(", ")));
        bookDto.setPages(book.getPages());
        bookDto.setAddedDate(book.getAddedDate().toString());
        return bookDto;
    }

    public PageRequest convert(MyPageRequest request) {
        int size = request.getSize();
        int page = request.getPage();
        Sort.Direction direction =
                Sort.Direction.fromString(request.getDirection());
        String property = request.getProperty();

        return PageRequest.of(
                page,
                size,
                direction,
                property);
    }

    public MyPageResponse<BookDto> convert(Page<Book> page) {
        List<BookDto> bookDtos = page.stream()
                .map(entity -> convert(entity))
                .collect(Collectors.toList());
        MyPageResponse<BookDto> response = new MyPageResponse<>();
        response.setContent(bookDtos);
        response.setTotalElements((int) page.getTotalElements());
        response.setLast(page.isLast());
        return response;
    }

    public List<Author> convertToListOfAuthors(String string) {
        String[] authorsString = string.split(",");
        List<Author> list = new ArrayList<>();
        for(String s : authorsString) {
            Author author = authorRepository.getByFullName(s);
            if(author == null) {
                author = new Author(s);
                authorRepository.save(author);
            }
            list.add(author);
        }
        return list;
    }

    public List<BookGenre> convertToListOfGenres(String string) {
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
