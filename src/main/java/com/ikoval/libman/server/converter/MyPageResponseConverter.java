package com.ikoval.libman.server.converter;

import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.shared.dto.MyPageResponse;
import org.springframework.data.domain.Page;

public class MyPageResponseConverter {

    public static MyPageResponse convert(Page<BookDto> page) {
        MyPageResponse<BookDto> myPageResponse = new MyPageResponse();
        myPageResponse.setContent(page.getContent());
        myPageResponse.setTotalElements((int) page.getTotalElements());
        myPageResponse.setLast(page.isLast());
        return myPageResponse;
    }
}
