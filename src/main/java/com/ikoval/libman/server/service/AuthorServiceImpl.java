package com.ikoval.libman.server.service;

import com.ikoval.libman.server.domain.Author;
import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.server.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    AuthorRepository repository;

    @Override
    public Author getById(Long id) {
        Optional<Author> author = repository.findById(id);
        return author.isPresent() ? author.get() : new Author();
    }

    @Override
    public void save(Author author) {
        repository.save(author);
    }

    @Override
    public void delete(Author author) {
        repository.delete(author);
    }


}
