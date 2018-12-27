package com.ikoval.libman.client.domain;

import com.ikoval.libman.shared.BookResponseDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface RestLibraryManagementClient extends LibraryManagementClient, RestService {

    @GET
    @Path("/api/books")
    void getAllBooks(MethodCallback<List<BookResponseDto>> callback);
}
