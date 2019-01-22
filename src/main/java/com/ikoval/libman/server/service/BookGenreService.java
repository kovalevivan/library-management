package com.ikoval.libman.server.service;

import com.ikoval.libman.server.domain.BookGenre;
import com.ikoval.libman.server.repository.BookGenreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookGenreService {

    private BookGenreRepository bookGenreRepository;

    public BookGenreService(BookGenreRepository bookGenreRepository) {
        this.bookGenreRepository = bookGenreRepository;
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
            String genreName = s.trim();
            BookGenre genre = bookGenreRepository.findByName(genreName);
            if(genre == null) {
                genre = new BookGenre(genreName);
                save(genre);
            }
            list.add(genre);
        }
        return list;
    }
}
