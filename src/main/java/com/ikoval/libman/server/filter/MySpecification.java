package com.ikoval.libman.server.filter;

import com.ikoval.libman.server.domain.Book;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class MySpecification {


    public static Specification<Book> filterBook(String name) {
        return new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Join<Object, Object> authorsJoin = root.join("authors", JoinType.INNER);
                return criteriaBuilder.equal(authorsJoin.get("full_name"), name);
            }
        };
    }
}
