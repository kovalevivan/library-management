package com.ikoval.libman.server.service;

import com.ikoval.libman.server.converter.BookConverter;
import com.ikoval.libman.server.converter.PageConverter;
import com.ikoval.libman.server.domain.Author;
import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.domain.BookGenre;
import com.ikoval.libman.server.repository.AuthorRepository;
import com.ikoval.libman.server.repository.BookGenreRepository;
import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.server.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;

    AuthorRepository authorRepository;

    BookGenreRepository bookGenreRepository;

    @Override
    public Page<BookDto> getAllBooks(Pageable pageRequest) {
        Page<Book> pageResponse = bookRepository.findAll(pageRequest);
        return PageConverter.convert(pageResponse,pageRequest);
    }

    @Override
    public Page<BookDto> findAll(BookDto bookDtoExample, Pageable pageRequest) {
        Book bookExample = BookConverter.convertToBook(bookDtoExample);
        Example<Book> example = Example.of(bookExample);
        Page<Book> pageResponse = bookRepository.findAll(example,pageRequest);
        return PageConverter.convert(pageResponse,pageRequest);
    }

    @Override
    public Page<BookDto> findAll(Specification spec, Pageable pageRequest) {
        Page<Book> pageResponse = bookRepository.findAll(spec,pageRequest);
        return PageConverter.convert(pageResponse,pageRequest);
    }


    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = (List<Book>) bookRepository.findAll();
        return books.stream()
                .map(entity -> BookConverter.convertToBookResponseDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public BookDto getById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.isPresent() ? BookConverter.convertToBookResponseDto(book.get()) : new BookDto();
    }

    @Override
    public void delete(BookDto bookDto) {
        bookRepository.deleteById(bookDto.getId());
    }

    @Override
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
