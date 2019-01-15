package com.ikoval.libman.server.filter;

import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.shared.FilterCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class BookSpecification implements Specification<Book> {

    FilterCriteria criteria;

    public BookSpecification(FilterCriteria criteria) {
        this.criteria = criteria;
    }

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

    private static String getLikePattern(String search) {
        return "%" + search.toLowerCase() + "%";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookSpecification)) return false;
        BookSpecification that = (BookSpecification) o;
        return Objects.equals(criteria, that.criteria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(criteria);
    }
}
