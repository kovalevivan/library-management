package com.ikoval.libman.server.service;

import com.ikoval.libman.server.domain.Author;
import com.ikoval.libman.server.repository.AuthorRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @MockBean
    private AuthorRepository authorRepository;

    @Test
    public void testSaveAuthor() {
        Author author = new Author();
        author.setFullName("Author");

        when(authorRepository.save(author)).thenReturn(author);

        Author response = authorService.save(author);

        Assert.assertEquals(author.getFullName(),response.getFullName());
        Assert.assertEquals(author.getId(),response.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveAuthorWithNullValue() {
        when(authorRepository.save(null)).thenThrow(new IllegalArgumentException());
        authorService.save(null);
    }

}
