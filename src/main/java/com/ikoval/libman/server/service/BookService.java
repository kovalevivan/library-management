package com.ikoval.libman.server.service;

import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.exception.BadRequestException;
import com.ikoval.libman.server.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Validates parameters and retrieves from database
 *
 */

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    public Page<Book> findAll(PageRequest pageRequest) {
        return bookRepository.findAll(pageRequest);
    }

    public Page<Book> findAll(Specification<Book> specification, PageRequest pageRequest) {
        return bookRepository.findAll(specification,pageRequest);
    }

    /**
     * Retrieves book by it id.
     *
     * @param id must not be {@literal null}.
     * @return Book with the given id.
     * @throws BadRequestException in case the given id wasn't found.
     */

    public Book findById(Long id) throws BadRequestException {
        Optional<Book> opt = bookRepository.findById(id);
        return opt.orElseThrow(() -> new BadRequestException("Book not found"));
    }

    /**
     * Saves given book.
     *
     * @param book must not be {@literal null}.
     * @return the saved book will never be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code book} is {@literal null}
     */

    public Book save(Book book) throws IllegalArgumentException {
        if(book == null) throw new IllegalArgumentException("Field book must not be null");
        return bookRepository.save(book);
    }

    /**
     * Deletes book by it id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */

    public void delete(Long id) throws IllegalArgumentException {
        if(id == null) throw new IllegalArgumentException("Field id must not be null");
        bookRepository.deleteById(id);
    }


}
