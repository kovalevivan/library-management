package com.ikoval.libman.client.model;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.ikoval.libman.client.domain.LibraryManagementClient;
import com.ikoval.libman.client.domain.RestLibraryManagementClient;
import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.shared.dto.MyPageResponse;
import com.ikoval.libman.shared.dto.MyPageRequest;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class BookTableModel extends AsyncDataProvider<BookDto> {

    private final LibraryManagementClient server = GWT.create(RestLibraryManagementClient.class);

    private List<BookDto> list;

    private MyPageRequest myPageRequest = new MyPageRequest();

    MethodCallback<MyPageResponse<BookDto>> callback = new MethodCallback<MyPageResponse<BookDto>>() {
        @Override
        public void onFailure(Method method, Throwable throwable) {
            Window.alert("Something went wrong");
        }

        @Override
        public void onSuccess(Method method, MyPageResponse<BookDto> bookDtoMyPageResponse) {
            list = bookDtoMyPageResponse.getContent();
            updateRowCount(bookDtoMyPageResponse.getTotalElements(),true);
            updateRowData(myPageRequest.getStart(),list);
        }
    };

    @Override
    protected void onRangeChanged(HasData<BookDto> display) {
        final Range range = display.getVisibleRange();

        int start = range.getStart();
        int length = range.getLength();
/*        Window.alert("start: " + start + " length: " + length);*/
        int page = start%length == 0 ? start/length : start/length + 1;
/*        Window.alert("page " + page);*/
        myPageRequest.setStart(start);
        myPageRequest.setPage(page);
        myPageRequest.setSize(length);
        server.getAllBooksWithPagination(myPageRequest,callback);
    }

    public void refresh() {
        server.getAllBooksWithPagination(myPageRequest,callback);
    }

    public void setSorting(String property, String direction) {
        myPageRequest.setProperty(property);
        myPageRequest.setDirection(direction);
    }

    public void save(BookDto bookDto) {
        server.saveBook(bookDto, new MethodCallback() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Something went wrong");
            }

            @Override
            public void onSuccess(Method method, Object response) {
                list.add(bookDto);
                refresh();
                Window.alert("Book was saved");
            }
        });
    }

    public void delete(BookDto bookDto) {
        MethodCallback callback = new MethodCallback() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Something went wrong");
            }

            @Override
            public void onSuccess(Method method, Object response) {
                list.remove(bookDto);
                refresh();
                Window.alert("Book was successfully deleted");
            }
        };
        server.deleteBook(bookDto.getId(), callback);
    }
}
