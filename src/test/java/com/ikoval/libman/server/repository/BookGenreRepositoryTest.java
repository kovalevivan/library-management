package com.ikoval.libman.server.repository;

import com.ikoval.libman.server.domain.BookGenre;
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
public class BookGenreRepositoryTest {

    @Autowired
    BookGenreRepository repository;

    @Test
    public void shouldSaveAndFindByName() {
        BookGenre bookGenre = new BookGenre("Genre123");

        repository.save(bookGenre);

        BookGenre response = repository.findByName(bookGenre.getName());

        Assert.assertEquals(bookGenre,response);
    }
}
