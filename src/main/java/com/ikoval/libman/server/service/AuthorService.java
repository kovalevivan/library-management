package com.ikoval.libman.server.service;

import com.ikoval.libman.server.domain.Author;
import com.ikoval.libman.server.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "authorService")
@AllArgsConstructor
public class AuthorService {

    private AuthorRepository authorRepository;

    /**
     * Saves given author
     *
     * @param author must not be {@literal null}
     */


    public Author save(Author author) {
        return authorRepository.save(author);
    }

    /**
     * Converts string of comma-separated names into {@link List} of {@link Author}
     * with corresponding names.
     *
     * @param string must not be {@literal null}.
     * @return not be {@literal null}
     */

    public List<Author> convertToListOfAuthors(String string) {
        String[] authorsString = string.split(",");
        List<Author> list = new ArrayList<>();
        for(String s : authorsString) {
            String authorName = s.trim();
            Author author = authorRepository.findByFullName(authorName);
            if(author == null) {
                author = new Author(authorName);
                save(author);
            }
            list.add(author);
        }
        return list;
    }
}
