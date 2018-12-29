package com.ikoval.libman.client.ui;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.ikoval.libman.client.domain.LibraryManagementClient;
import com.ikoval.libman.client.domain.RestLibraryManagementClient;
import com.ikoval.libman.shared.dto.BookDto;
import com.ikoval.libman.shared.dto.PageDto;
import com.ikoval.libman.shared.dto.PageRequestDto;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

public class BookTableView extends Composite {

    private final LibraryManagementClient server = GWT.create(RestLibraryManagementClient.class);

    ListDataProvider<BookDto> dataProvider;

    CellTable<BookDto> cellTable = new CellTable<>();

    SimplePager pager;

    public BookTableView() {
        init();
    }

    public void init() {

        initTableColumns(cellTable);
        initDataProvider(cellTable);
        retrieveDataFromServer(new PageRequestDto());
        addColumnSorting(cellTable);
        addPagination(cellTable);

        VerticalPanel panel = new VerticalPanel();
        panel.add(cellTable);
        panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        panel.add(pager);
        initWidget(panel);
    }

    private void initTableColumns(CellTable<BookDto> table){

        table.setWidth("100%");
        table.setAutoHeaderRefreshDisabled(true);
        table.setAutoFooterRefreshDisabled(true);

        //ID
        TextColumn<BookDto> idColumn = new TextColumn<BookDto>() {
            @Override
            public String getValue(BookDto object) { return object.getId().toString(); }
        };
        idColumn.setDataStoreName("id");
        idColumn.setSortable(true);
        table.addColumn(idColumn,"Id");

        //Title
        TextColumn<BookDto> titleColumn = new TextColumn<BookDto>() {
            @Override
            public String getValue(BookDto object) { return object.getTitle(); }
        };
        titleColumn.setDataStoreName("title");
        titleColumn.setSortable(true);
        table.addColumn(titleColumn, "Title");

        //Authors
        TextColumn<BookDto> authorsColumn = new TextColumn<BookDto>() {
            @Override
            public String getValue(BookDto object) {
                return object.getAuthors()
                        .stream()
                        .collect(Collectors.joining(", "));
            }
        };
        authorsColumn.setDataStoreName("authors");
        authorsColumn.setSortable(true);
        table.addColumn(authorsColumn, "Authors");

        //Number of pages
        TextColumn<BookDto> numberOfPagesColumn = new TextColumn<BookDto>() {
            @Override
            public String getValue(BookDto object) { return object.getPages().toString(); }
        };
        numberOfPagesColumn.setSortable(true);
        numberOfPagesColumn.setDataStoreName("pages");
        table.addColumn(numberOfPagesColumn , "Number of pages");

        //Year of publishing
        TextColumn<BookDto> yearOfPublishing = new TextColumn<BookDto>() {
            @Override
            public String getValue(BookDto object) { return object.getYearOfPublishing().toString(); }
        };
        yearOfPublishing.setDataStoreName("yearOfPublishing");
        yearOfPublishing.setSortable(true);
        table.addColumn(yearOfPublishing , "Year of publication");

        //Date when book was added to database
        TextColumn<BookDto> addedDate = new TextColumn<BookDto>() {
            @Override
            public String getValue(BookDto object) { return object.getAddedDate(); }
        };
        addedDate.setSortable(true);
        addedDate.setDataStoreName("addedDate");
        table.addColumn(addedDate , "Added");

        //Delete button
        Column<BookDto, String> deleteButton = new Column<BookDto, String>(new ButtonCell()) {
            @Override
            public String getValue(BookDto object) { return "x"; }
        };
        table.addColumn(deleteButton , "");
        deleteButton.setFieldUpdater(new FieldUpdater<BookDto, String>() {
            @Override
            public void update(int index, BookDto object, String value) {
                deleteBook(object);
            }
        });

    }

    private void initDataProvider(CellTable<BookDto> table) {
        dataProvider = new ListDataProvider<>();
        dataProvider.addDataDisplay(table);

        table.setEmptyTableWidget(new Label("No Data"));
    }

    private void retrieveDataFromServer(PageRequestDto pageRequest) {
        MethodCallback callback = new MethodCallback<PageDto<BookDto>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Exception");
            }

            @Override
            public void onSuccess(Method method, PageDto<BookDto> response) {
                List<BookDto> list = response.getContent();
                dataProvider.setList(list);
                dataProvider.refresh();
            }
        };
        server.getAllBooksWithPagination(pageRequest,callback);
    }

    private void addColumnSorting(CellTable<BookDto> table) {
        ColumnSortEvent.AsyncHandler handler = new ColumnSortEvent.AsyncHandler(cellTable)  {
            @Override
            public void onColumnSort(ColumnSortEvent event) {
                String name = event.getColumn().getDataStoreName();
                PageRequestDto pageRequest = new PageRequestDto();
                pageRequest.setProperty(name);
                if (event.isSortAscending()) {
                    pageRequest.setDirection("asc");
                } else {
                    pageRequest.setDirection("desc");
                }
                retrieveDataFromServer(pageRequest);
            }
        };
        table.addColumnSortHandler(handler);
    }

    private void addPagination(CellTable<BookDto> table) {
        this.pager = new SimplePager();
        pager.setDisplay(table);
        pager.setPageSize(3);
    }
    
    public void saveBook(BookDto bookDto) {
        server.saveBook(bookDto, new MethodCallback() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Something went wrong");
            }

            @Override
            public void onSuccess(Method method, Object response) {
                List<BookDto> list = dataProvider.getList();
                list.add(bookDto);
                dataProvider.refresh();
                Window.alert("Book was saved");
            }
        });
    }

    public void deleteBook(BookDto book) {
        MethodCallback callback = new MethodCallback() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Something went wrong");
            }

            @Override
            public void onSuccess(Method method, Object response) {
                dataProvider.getList().remove(book);
                dataProvider.refresh();
                cellTable.redraw();
                Window.alert("Book was successfully deleted");
            }
        };
        server.deleteBook(book.getId(), callback);
    }

}




