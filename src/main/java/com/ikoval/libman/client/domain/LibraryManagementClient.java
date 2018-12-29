package com.ikoval.libman.client.domain;


import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.shared.dto.PageDto;
import com.ikoval.libman.shared.dto.PageRequestDto;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public interface LibraryManagementClient {

    void getAllBooks(MethodCallback<List<BookDto>> callback);

    void saveBook(BookDto bookDto, MethodCallback callback);

    void deleteBook(Long id, MethodCallback callback);

    void getAllBooksWithPagination(PageRequestDto pageable, MethodCallback<PageDto<BookDto>> callback);

}
