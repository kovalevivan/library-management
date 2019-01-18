package com.ikoval.libman.server.service;

import com.ikoval.libman.server.domain.BookGenre;
import com.ikoval.libman.server.repository.BookGenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "bookGenreService")
@AllArgsConstructor
public class BookGenreService {

    @Autowired
    private BookGenreRepository bookGenreRepository;

    public BookGenreService() {
    }

    public void save(BookGenre bookGenre) {
        bookGenreRepository.save(bookGenre);
    }

    /**
     * Converts string of comma-separated names into {@link List} of {@link BookGenre}
     * with corresponding names.
     *
     * @param string must not be {@literal null}.
     * @return not be {@literal null}
     */

    public List<BookGenre> convertToListOfGenres(String string) {
        String[] genres = string.split(",");
        List<BookGenre> list = new ArrayList<>();
        for(String s : genres) {
            BookGenre genre = bookGenreRepository.findByName(s);
            if(genre == null) {
                genre = new BookGenre(s);
                save(genre);
            }
            list.add(genre);
        }
        return list;
    }
}
