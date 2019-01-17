package com.ikoval.libman.server.validator;

import com.ikoval.libman.shared.dto.BookDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BookDtoValidator implements Validator {

    private static final String REQUIRED = "required";

    @Override
    public boolean supports(Class<?> clazz) {
        return BookDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookDto book = (BookDto) target;
        //title validation
        if(book.getTitle() == null) {
            errors.rejectValue("title",REQUIRED, REQUIRED);
        }

        //author
        if(book.getAuthors() == null || book.getGenres().length() > 1) {
            errors.rejectValue("authors",REQUIRED,REQUIRED);
        }

    }
}
