package com.ikoval.libman.server.repository;

import com.ikoval.libman.server.domain.Author;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) // provide bridge between SpringBoot test features and JUnit, for usage of springboot tsting features
@DataJpaTest
@TestPropertySource(locations= "classpath:application-test.properties")
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void shouldSaveAndFindByName() {
        Author author = new Author("Author234");

        authorRepository.save(author);

        Author response = authorRepository.findByFullName(author.getFullName());

        Assert.assertEquals(author,response);
    }
}
