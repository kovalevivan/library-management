package com.ikoval.libman.server.repository;

import com.ikoval.libman.server.domain.Book;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book,Long>, JpaSpecificationExecutor<Book>{

}
