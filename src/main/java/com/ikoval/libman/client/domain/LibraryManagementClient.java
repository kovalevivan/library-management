package com.ikoval.libman.client.domain;


import com.ikoval.libman.shared.BookResponseDto;
import com.ikoval.libman.shared.MyPageImpl;
import com.ikoval.libman.shared.PageableImpl;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public interface LibraryManagementClient {

    void getAllBooks(MethodCallback<List<BookResponseDto>> callback);

    void saveBook(BookResponseDto bookResponseDto, MethodCallback callback);

    void deleteBook(Long id, MethodCallback callback);

    void getAllBooksWithPagination(PageableImpl pageable, MethodCallback<MyPageImpl<BookResponseDto>> callback);

}
