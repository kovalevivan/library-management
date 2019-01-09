package com.ikoval.libman.server.filter;

import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.shared.FilterCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;

public class MySpecification {


    public static Specification<Book> filterBook(FilterCriteria criteria) {
        return new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Collection<Predicate> predicates = new ArrayList<>();
                if(criteria.getBookTitle() != null) {
                    Predicate curPredicate = cb.like(cb.lower(root.get("title")),
                            getLikePattern(criteria.getBookTitle()));
                    predicates.add(curPredicate);
                }

                if(criteria.getAuthorName() != null) {
                    Predicate curPredicate = cb.like(cb.lower(root.join("authors").get("fullName")),
                            getLikePattern(criteria.getAuthorName()));
                    predicates.add(curPredicate);
                }

                if(criteria.getGenre() != null) {
                    Predicate curPredicate = cb.equal(root.join("genres").get("name"),
                            criteria.getGenre());
                    predicates.add(curPredicate);
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }

        };
    }

    public static String getLikePattern(String search) {
        return "%" + search.toLowerCase() + "%";
    }
}
