package com.ikoval.libman.server.repository;

import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.shared.dto.BookDto;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.awt.print.Pageable;

public interface BookRepository extends PagingAndSortingRepository<Book,Long>,
        JpaSpecificationExecutor<Book>, QueryByExampleExecutor<Book> {

}
