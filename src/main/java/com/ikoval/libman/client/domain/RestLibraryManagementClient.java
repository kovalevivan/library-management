package com.ikoval.libman.client.domain;

import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.shared.dto.MyPageResponse;
import com.ikoval.libman.shared.dto.MyPageRequest;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface RestLibraryManagementClient extends LibraryManagementClient, RestService {

    @Override
    @POST
    @Path("/api/book/save")
    void saveBook(BookDto bookDto, MethodCallback callback);

    @Override
    @DELETE
    @Path("/api/book/delete")
    void deleteBook(@QueryParam("id") Long id, MethodCallback callback);

    @Override
    @POST
    @Path("/api/books")
    void findAllBook(MyPageRequest pageable, MethodCallback<MyPageResponse<BookDto>> callback);

    @Override
    @POST
    @Path("/api/books/filter")
    void findAllBookWithFilter(MyPageRequest pageable, MethodCallback<MyPageResponse<BookDto>> callback);

}
