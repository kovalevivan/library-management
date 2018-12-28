package com.ikoval.libman.client.domain;

import com.ikoval.libman.shared.BookResponseDto;
import com.ikoval.libman.shared.MyPageImpl;
import com.ikoval.libman.shared.PageableImpl;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface RestLibraryManagementClient extends LibraryManagementClient, RestService {

    @Override
    @GET
    @Path("/api/books")
    void getAllBooks(MethodCallback<List<BookResponseDto>> callback);

    @Override
    @POST
    @Path("/api/book/save")
    void saveBook(BookResponseDto bookResponseDto, MethodCallback callback);

    @Override
    @DELETE
    @Path("/api/book/{id}")
    void deleteBook(@PathParam("id") Long id, MethodCallback callback);

    @Override
    @GET
    @Path("/api/bookswithpagination")
    void getAllBooksWithPagination(PageableImpl pageable, MethodCallback<MyPageImpl<BookResponseDto>> callback);
}
