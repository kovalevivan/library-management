package com.ikoval.libman.server.service;

import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.exception.BadRequestException;
import com.ikoval.libman.server.exception.BookNotFoundException;
import com.ikoval.libman.server.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Validates parameters and retrieves books from the database.
 *
 */

@Service(value = "bookService")
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;


    /**
     * Retrieve a {@link Page} of the {@link Book} matching the given {@link Specification}
     *
     * @param specification can be {@literal null}.
     * @param pageRequest must not be {@literal null}.
     * @return page, never {@literal null}.
     * @throws BadRequestException when pageRequest is null.
     */

    public Page<Book> findAll(Specification<Book> specification, PageRequest pageRequest) throws BadRequestException {
        if(pageRequest == null) throw new BadRequestException("Request must not be null");
        return bookRepository.findAll(specification,pageRequest);
    }

    /**
     * Retrieve a {@link Book} by its id.
     *
     * @param id must not be {@literal null}.
     * @return Book with the given id.
     * @throws BookNotFoundException in case the given id wasn't found.
     */

    public Book findById(Long id) throws BookNotFoundException {
        if(id == null) throw new IllegalArgumentException("Field id must not be null");
        Optional<Book> opt = bookRepository.findById(id);
        return opt.orElseThrow(() -> new BookNotFoundException("Book not found"));
    }

    /**
     * Save the given book.
     *
     * @param book must not be {@literal null}.
     * @return the saved book will never be {@literal null}.
     * @throws IllegalArgumentException in case the given book is {@literal null}.
     */

    public Book save( Book book) throws IllegalArgumentException {
        if(book == null) throw new IllegalArgumentException("Book must not be null");
        return bookRepository.save(book);
    }

    /**
     * Delete a book by its id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given id is {@literal null}.
     */

    public void delete(Long id) throws IllegalArgumentException {
        if(id == null) throw new IllegalArgumentException("Field id must not be null");
        bookRepository.deleteById(id);
    }


}
