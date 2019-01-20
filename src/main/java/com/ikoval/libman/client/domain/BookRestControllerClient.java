package com.ikoval.libman.client.domain;

import com.ikoval.libman.shared.ApplicationEndpoints;
import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.shared.dto.MyPageResponse;
import com.ikoval.libman.shared.dto.MyPageRequest;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface BookRestControllerClient extends BookRestControllerClientInterface, RestService {

    @POST
    @Path(ApplicationEndpoints.SAVE_BOOK)
    void saveBook(BookDto bookDto, MethodCallback callback);

    @DELETE
    @Path(ApplicationEndpoints.DELETE_BOOK)
    void deleteBook(@QueryParam("id") Long id, MethodCallback callback);

    @POST
    @Path(ApplicationEndpoints.FIND_BOOKS)
    void findBooks(MyPageRequest pageable, MethodCallback<MyPageResponse<BookDto>> callback);

}
