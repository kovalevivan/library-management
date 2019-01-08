package com.ikoval.libman.server.service;

import com.ikoval.libman.server.domain.Author;
import com.ikoval.libman.server.domain.Book;

public interface AuthorService {

    Author getById(Long id);

    void save(Author author);

    void delete(Author author);

}
