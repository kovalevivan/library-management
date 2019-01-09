package com.ikoval.libman.server.converter;

import com.ikoval.libman.server.domain.Book;
import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.shared.dto.MyPageResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MyPageResponseConverter {


    public static MyPageResponse convert(Page<Book> page) {
        List<BookDto> bookDtos = page.stream()
                .map(entity -> BookConverter.convertToBookResponseDto(entity))
                .collect(Collectors.toList());
        MyPageResponse<BookDto> myPageResponse = new MyPageResponse<>();
        myPageResponse.setContent(bookDtos);
        myPageResponse.setTotalElements((int) page.getTotalElements());
        myPageResponse.setLast(page.isLast());
        return myPageResponse;
    }
}
