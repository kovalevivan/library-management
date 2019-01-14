package com.ikoval.libman.server.converter;

import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.shared.dto.MyPageResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MyPageResponseConverter {

    public static MyPageResponse<BookDto> convert(Page<Book> page) {
        List<BookDto> bookDtos = page.stream()
                .map(entity -> BookConverter.convert(entity))
                .collect(Collectors.toList());
        MyPageResponse<BookDto> response = new MyPageResponse<>();
        response.setContent(bookDtos);
        response.setTotalElements((int) page.getTotalElements());
        response.setLast(page.isLast());
        return response;
    }
}
