package com.ikoval.libman.server.repository;

import com.ikoval.libman.server.domain.Book;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface BookRepository extends Repository<Book,Long> {

    List<Book> findAll();
}
