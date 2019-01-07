package com.ikoval.libman.server.service;

import com.ikoval.libman.server.converter.BookConverter;
import com.ikoval.libman.server.domain.Author;
import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.domain.BookGenre;
import com.ikoval.libman.server.repository.AuthorRepository;
import com.ikoval.libman.server.repository.BookGenreRepository;
import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.server.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public Page<BookDto> getAllBooks(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        long totalElement = books.getTotalElements();
        List<BookDto> bookDtos = books.stream()
                .map(entity -> BookConverter.convertToBookResponseDto(entity))
                .collect(Collectors.toList());
        return new PageImpl<BookDto>(bookDtos,pageable,totalElement);
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
        String authorName = bookDto.getAuthors();
        Author authorToUpdate = authorRepository.getByFullName(authorName);
        List<Book> books = authorToUpdate.getWrittenByAuthor();
        List<Book> preparedList = books.stream()
                .filter(entity-> entity.getId() != bookDto.getId())
                .collect(Collectors.toList());
        authorToUpdate.setWrittenByAuthor(preparedList);
        authorRepository.save(authorToUpdate);
        bookRepository.deleteById(bookDto.getId());
    }

    @Override
    public void save(BookDto bookDto) {
        Book book = BookConverter.convertToBook(bookDto);
        bookRepository.save(book);
        String[] authorsString = bookDto.getAuthors().split(",");
        addBookToAuthors(authorsString, book);
        String[] genres = bookDto.getGenres().split(",");
        addGenres(genres, book);
    }

    private void addGenres(String[] genres, Book book) {
        List<BookGenre> list = new ArrayList<>();
        for(String s : genres) {
            BookGenre genre = bookGenreRepository.findByName(s);
            if(genre == null) {
                genre = new BookGenre();
                genre.setName(s);
                bookGenreRepository.save(genre);
            }
            list.add(genre);
        }
        book.setGenres(list);
    }

    private void addBookToAuthors(String[] authorsString, Book book) {
        for(String s : authorsString) {
            Author author = authorRepository.getByFullName(s);
            List<Book> list;
            if(author == null) {
                author = new Author();
                author.setFullName(s);
                list = new ArrayList<>();
            } else {
                list = author.getWrittenByAuthor();
            }
            list.add(book);
            author.setWrittenByAuthor(list);
            authorRepository.save(author);
        }
    }

}
