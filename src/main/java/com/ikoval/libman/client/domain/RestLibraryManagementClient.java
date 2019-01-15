package com.ikoval.libman.client.domain;

import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.shared.dto.MyPageResponse;
import com.ikoval.libman.shared.dto.MyPageRequest;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
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
    void deleteBook(BookDto bookDto, MethodCallback callback);

    @Override
    @POST
    @Path("/api/books")
    void findAllBook(@RequestBody MyPageRequest pageable, MethodCallback<MyPageResponse<BookDto>> callback);

    @Override
    @POST
    @Path("/api/books/filter")
    void findAllBookWithFilter(@RequestBody MyPageRequest pageable, MethodCallback<MyPageResponse<BookDto>> callback);

}
