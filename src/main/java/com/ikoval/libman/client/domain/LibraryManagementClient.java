package com.ikoval.libman.client.domain;


import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.shared.dto.MyPageResponse;
import com.ikoval.libman.shared.dto.MyPageRequest;
import org.fusesource.restygwt.client.MethodCallback;


public interface LibraryManagementClient {

    void saveBook(BookDto bookDto, MethodCallback callback);

    void deleteBook(Long id, MethodCallback callback);

    void findAllBook(MyPageRequest pageable, MethodCallback<MyPageResponse<BookDto>> callback);

    void findAllBookWithFilter(MyPageRequest pageable, MethodCallback<MyPageResponse<BookDto>> callback);

}
