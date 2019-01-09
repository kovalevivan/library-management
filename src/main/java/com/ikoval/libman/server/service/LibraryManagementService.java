package com.ikoval.libman.server.service;

import com.ikoval.libman.server.converter.BookConverter;
import com.ikoval.libman.server.converter.MyPageRequestConverter;
import com.ikoval.libman.server.converter.MyPageResponseConverter;
import com.ikoval.libman.server.domain.Author;
import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.domain.BookGenre;
import com.ikoval.libman.server.filter.MySpecification;
import com.ikoval.libman.server.repository.AuthorRepository;
import com.ikoval.libman.server.repository.BookGenreRepository;
import com.ikoval.libman.server.repository.BookRepository;
import com.ikoval.libman.shared.FilterCriteria;
import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.shared.dto.MyPageRequest;
import com.ikoval.libman.shared.dto.MyPageResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LibraryManagementService {

    private BookRepository bookRepository;

    private AuthorRepository authorRepository;

    private BookGenreRepository bookGenreRepository;

    public MyPageResponse<BookDto> findAll(MyPageRequest myPageRequest) {
        PageRequest pageRequest = MyPageRequestConverter.convert(myPageRequest);
        Page<Book> pageResponse;

        if(myPageRequest.getFilter() == null) {
            pageResponse = bookRepository.findAll(pageRequest);
        } else {
            FilterCriteria filter = myPageRequest.getFilter();
            Specification spec = MySpecification.filterBook(filter);
            pageResponse = bookRepository.findAll(spec,pageRequest);
        }
        return MyPageResponseConverter.convert(pageResponse);
    }

    public void save(BookDto bookDto) {
        Book book = BookConverter.convertToBook(bookDto);
        String[] authorsString = bookDto.getAuthors().split(",");
        addAuthors(authorsString, book);
        if(bookDto.getGenres() != null) {
            String[] genres = bookDto.getGenres().split(",");
            addGenres(genres, book);
        }
        bookRepository.save(book);
    }

    private void addGenres(String[] genres, Book book) {
        List<BookGenre> list = new ArrayList<>();
        for(String s : genres) {
            BookGenre genre = bookGenreRepository.findByName(s);
            if(genre == null) {
                genre = new BookGenre(s);
                bookGenreRepository.save(genre);
            }
            list.add(genre);
        }
        book.setGenres(list);
    }

    private void addAuthors(String[] authorsString, Book book) {
        List<Author> list = new ArrayList<>();
        for(String s : authorsString) {
            Author author = authorRepository.getByFullName(s);
            if(author == null) {
                author = new Author(s);
                authorRepository.save(author);
            }
            list.add(author);
        }
        book.setAuthors(list);
    }


}
