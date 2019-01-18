package com.ikoval.libman.server.repository;

import com.ikoval.libman.server.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface BookRepository extends PagingAndSortingRepository<Book,Long>, JpaSpecificationExecutor<Book>{

    Page<Book> findAll(Pageable pageable);


    Optional<Book> findById(Long id);




}
