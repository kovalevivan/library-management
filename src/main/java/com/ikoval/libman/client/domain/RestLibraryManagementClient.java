package com.ikoval.libman.client.domain;

import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.shared.dto.MyPageResponse;
import com.ikoval.libman.shared.dto.MyPageRequest;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface RestLibraryManagementClient extends LibraryManagementClient, RestService {

    @Override
    @GET
    @Path("/api/books")
    void getAllBooks(MethodCallback<List<BookDto>> callback);

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
    @Path("/api/bookswithpagination")
    void getAllBooksWithPagination(@RequestBody MyPageRequest pageable, MethodCallback<MyPageResponse<BookDto>> callback);

}
