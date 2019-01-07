package com.ikoval.libman.server.repository;

import com.ikoval.libman.server.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Author getByFullName(String fullName);

}
