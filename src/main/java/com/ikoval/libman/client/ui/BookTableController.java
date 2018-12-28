package com.ikoval.libman.client.ui;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.view.client.ListDataProvider;
import com.ikoval.libman.client.domain.LibraryManagementClient;
import com.ikoval.libman.client.domain.RestLibraryManagementClient;
import com.ikoval.libman.shared.BookResponseDto;
import com.ikoval.libman.shared.MyPageImpl;
import com.ikoval.libman.shared.PageableImpl;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;
import java.util.stream.Collectors;

public class BookTableController extends Composite {

    private final LibraryManagementClient libraryManagementClient = GWT.create(RestLibraryManagementClient.class);

    ListDataProvider<BookResponseDto> dataProvider = new ListDataProvider<>();

    CellTable<BookResponseDto> cellTable;

    MethodCallback<List<BookResponseDto>> callback = new MethodCallback<List<BookResponseDto>>() {
        @Override
        public void onFailure(Method method, Throwable exception) {
            Window.alert("Can't get information from server");
        }

        @Override
        public void onSuccess(Method method, List<BookResponseDto> response) {
            dataProvider.setList(response);
        }
    };

    public BookTableController() {
        libraryManagementClient.getAllBooks(callback);
        createTable();
    }

    public void saveBook(BookResponseDto bookResponseDto) {
        libraryManagementClient.saveBook(bookResponseDto, new MethodCallback() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Something went wrong");
            }

            @Override
            public void onSuccess(Method method, Object response) {
                List<BookResponseDto> list = dataProvider.getList();
                list.add(bookResponseDto);
                dataProvider.refresh();
                Window.alert("Book was saved");
            }
        });
    }

    private void createTable() {
        cellTable = new CellTable<>();

        addIdColumn();
        addAuthorColumn();
        addTitleColumn();
        addNumberOfPageColumn();
        addYearOfPublishingColumn();
        addAddedDateColumn();
        addDeleteColumn();
        ColumnSortEvent.AsyncHandler handler = new ColumnSortEvent.AsyncHandler(cellTable)  {
            @Override
            public void onColumnSort(ColumnSortEvent event) {
                if (event.isSortAscending()) {
                    Window.alert("asc");
                    String name = event.getColumn().getDataStoreName();
                    PageableImpl pageable = new PageableImpl();
                    pageable.setSort(name + ",acs");
                    libraryManagementClient.getAllBooksWithPagination(pageable, new MethodCallback<MyPageImpl<BookResponseDto>>() {
                        @Override
                        public void onFailure(Method method, Throwable exception) {
                            Window.alert("Exception");
                        }

                        @Override
                        public void onSuccess(Method method, MyPageImpl<BookResponseDto> response) {
                            Window.alert(response.getContent().toString());
                        }
                    });
                } else {
                    Window.alert("desc");
                    String name = event.getColumn().getDataStoreName();
                    PageableImpl pageable = new PageableImpl();
                    pageable.setSort(name + ",desc");
                    libraryManagementClient.getAllBooksWithPagination(pageable, new MethodCallback<MyPageImpl<BookResponseDto>>() {
                        @Override
                        public void onFailure(Method method, Throwable exception) {
                            Window.alert("Exception");
                        }

                        @Override
                        public void onSuccess(Method method, MyPageImpl<BookResponseDto> response) {
                            Window.alert(response.getContent().toString());
                        }
                });
            }
        }
        };

        cellTable.addColumnSortHandler(handler);

        cellTable.setWidth("100%");
        cellTable.setAutoHeaderRefreshDisabled(true);
        cellTable.setAutoFooterRefreshDisabled(true);
        dataProvider.addDataDisplay(cellTable);
        initWidget(cellTable);
    }

    private void addIdColumn() {
        TextColumn<BookResponseDto> idColumn = new TextColumn<BookResponseDto>() {

            @Override
            public String getValue(BookResponseDto object) {
                return String.valueOf(object.getId());
            }

        };
        idColumn.setSortable(true);
        idColumn.setDataStoreName("id");
        cellTable.addColumn(idColumn, "Id");
    }

    private void addAuthorColumn() {
        TextColumn<BookResponseDto> authorColumn = new TextColumn<BookResponseDto>() {

            @Override
            public String getValue(BookResponseDto object) {
                return object.getAuthors()
                        .stream ()
                        .map (i -> i.toString ())
                        .collect (Collectors.joining (", "));
            }

        };
        authorColumn.setDataStoreName("author");
        cellTable.addColumn(authorColumn, "Author");
    }

    private void addTitleColumn() {
        TextColumn<BookResponseDto> titleColumn = new TextColumn<BookResponseDto>() {

            @Override
            public String getValue(BookResponseDto object) {
                return object.getTitle();
            }

        };
        titleColumn.setDataStoreName("title");
        titleColumn.setSortable(true);
        cellTable.addColumn(titleColumn, "Title");
    }

    private void addNumberOfPageColumn() {
        TextColumn<BookResponseDto> numberOfPageColumn = new TextColumn<BookResponseDto>() {

            @Override
            public String getValue(BookResponseDto object) {
                return String.valueOf(object.getPages());
            }

        };
        numberOfPageColumn.setSortable(true);
        numberOfPageColumn.setDataStoreName("pages");
        cellTable.addColumn(numberOfPageColumn , "Pages");
    }

    private void addYearOfPublishingColumn() {
        TextColumn<BookResponseDto> yearOfPublishing = new TextColumn<BookResponseDto>() {

            @Override
            public String getValue(BookResponseDto object) {
                return String.valueOf(object.getYearOfPublishing());
            }

        };
        yearOfPublishing.setDataStoreName("yearOfPublishing");
        yearOfPublishing.setSortable(true);
        cellTable.addColumn(yearOfPublishing , "Year of publication");
    }

    private void addAddedDateColumn() {
        TextColumn<BookResponseDto> addedDate = new TextColumn<BookResponseDto>() {
            @Override
            public String getValue(BookResponseDto object) {
                return String.valueOf(object.getAddedDate());
            }

        };
        addedDate.setSortable(true);
        addedDate.setDataStoreName("addedDate");
        cellTable.addColumn(addedDate , "Date of added");
    }

    private void addDeleteColumn() {
        Column<BookResponseDto, String> deleteButton = new Column<BookResponseDto, String>(new ButtonCell()) {
            @Override
            public String getValue(BookResponseDto object) {
                return "x";
            }
        };

        cellTable.addColumn(deleteButton , "");

        deleteButton.setFieldUpdater(new FieldUpdater<BookResponseDto, String>() {

            @Override
            public void update(int index, BookResponseDto object, String value) {
                dataProvider.getList().remove(object);
                libraryManagementClient.deleteBook(object.getId(), new MethodCallback() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        Window.alert("Something went wrong");
                    }

                    @Override
                    public void onSuccess(Method method, Object response) {
                        Window.alert("Book was successfully deleted");
                    }
                });
                dataProvider.refresh();
                cellTable.redraw();
            }
        });
    }



}
