package com.ikoval.libman.client.domain;


import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.shared.dto.MyPageResponse;
import com.ikoval.libman.shared.dto.MyPageRequest;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public interface LibraryManagementClient {

    void getAllBooks(MethodCallback<List<BookDto>> callback);

    void saveBook(BookDto bookDto, MethodCallback callback);

    void deleteBook(BookDto bookDto, MethodCallback callback);

    void getAllBooksWithPagination(MyPageRequest pageable, MethodCallback<MyPageResponse<BookDto>> callback);

}
