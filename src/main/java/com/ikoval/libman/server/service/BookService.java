package com.ikoval.libman.server.service;

import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Book> findAll() {
        List<Book> books = (List<Book>) bookRepository.findAll();
        return books;
    }

    public Book getById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.isPresent() ? book.get() : new Book();
    }


    public void save(Book book) {
        bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }


}
