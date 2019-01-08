package com.ikoval.libman.server.converter;

import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.shared.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public class PageConverter {

    public static PageImpl<BookDto> convert(Page<Book> page, Pageable pageable) {
        long totalElement = page.getTotalElements();
        List<BookDto> bookDtos = page.stream()
                .map(entity -> BookConverter.convertToBookResponseDto(entity))
                .collect(Collectors.toList());
        return new PageImpl<BookDto>(bookDtos,pageable,totalElement);
    }
}
