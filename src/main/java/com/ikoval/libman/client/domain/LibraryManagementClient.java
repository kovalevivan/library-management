package com.ikoval.libman.client.domain;


import com.ikoval.libman.shared.BookResponseDto;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public interface LibraryManagementClient {

    void getAllBooks(MethodCallback<List<BookResponseDto>> callback);

}
