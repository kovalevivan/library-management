package com.ikoval.libman.client.model;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.ikoval.libman.client.domain.LibraryManagementClient;
import com.ikoval.libman.client.domain.RestLibraryManagementClient;
import com.ikoval.libman.shared.FilterCriteria;
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


    private MethodCallback<MyPageResponse<BookDto>> callback = new MethodCallback<MyPageResponse<BookDto>>() {
        @Override
        public void onFailure(final Method method, final Throwable throwable) {
            Window.alert("Something went wrong");
        }

        @Override
        public void onSuccess(final Method method, final MyPageResponse<BookDto> bookDtoMyPageResponse) {
            list = bookDtoMyPageResponse.getContent();
            boolean isLast = bookDtoMyPageResponse.getLast();
            updateRowCount(bookDtoMyPageResponse.getTotalElements(),isLast);
            updateRowData(myPageRequest.getStart(),list);
        }
    };

    public BookTableModel() {
    }

    @Override
    protected void onRangeChanged(final HasData<BookDto> display) {
        final Range range = display.getVisibleRange();

        int start = range.getStart();
        int length = range.getLength();
/*        Window.alert("start: " + start + " length: " + length);*/
        int page = start%length == 0 ? start/length : start/length + 1;
/*        Window.alert("page " + page);*/
        myPageRequest.setStart(start);
        myPageRequest.setPage(page);
        myPageRequest.setSize(length);
        refresh();
    }

    public void refresh() {
        if(myPageRequest.getFilter() == null) {
            server.findAllBook(myPageRequest, callback);
        } else {
            server.findAllBookWithFilter(myPageRequest,callback);
        }
    }

    public void setSorting(final String property, final String direction) {
        myPageRequest.setProperty(property);
        myPageRequest.setDirection(direction);
    }

    public void setFilter(final FilterCriteria filter) {
        myPageRequest.setFilter(filter);
        refresh();
    }

    public FilterCriteria getFilter() {
        if(myPageRequest.getFilter() == null) {
            return new FilterCriteria();
        }
        return myPageRequest.getFilter();
    }

    public void clearFilter() {
        myPageRequest.setFilter(null);
        refresh();
    }

    public void save(final BookDto bookDto) {
        server.saveBook(bookDto, new MethodCallback() {
            @Override
            public void onFailure(final Method method, final Throwable exception) {
                Window.alert(exception.toString());
            }

            @Override
            public void onSuccess(final Method method, final Object response) {
                list.add(bookDto);
                refresh();
                Window.alert("Book was saved");
            }
        });
    }

    public void delete(final BookDto bookDto) {
        MethodCallback callback = new MethodCallback() {
            @Override
            public void onFailure(final Method method, final Throwable exception) {
                Window.alert("Something went wrong");
            }

            @Override
            public void onSuccess(final Method method, final Object response) {
                list.remove(bookDto);
                refresh();
                Window.alert("Book was successfully deleted");
            }
        };
        server.deleteBook(bookDto, callback);
    }
}
