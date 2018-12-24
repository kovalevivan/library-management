package com.ikoval.libman.server.repository;

import com.ikoval.libman.server.domain.Book;
import org.springframework.data.repository.Repository;

public interface BookGenreRepository extends Repository<Book,Long> {
}
