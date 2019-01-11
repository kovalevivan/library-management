package com.ikoval.libman.server.repository;

import com.ikoval.libman.server.domain.BookGenre;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookGenreRepository extends CrudRepository<BookGenre,Long> {

     BookGenre findByName(String name);

     BookGenre save(BookGenre bookGenre);


}
