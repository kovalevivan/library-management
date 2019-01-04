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
import com.ikoval.libman.client.model.BookTableModel;
import com.ikoval.libman.client.domain.LibraryManagementClient;
import com.ikoval.libman.client.domain.RestLibraryManagementClient;
import com.ikoval.libman.shared.dto.BookDto;

public class BookTableView extends Composite {

    private final LibraryManagementClient server = GWT.create(RestLibraryManagementClient.class);

    BookTableModel dataProvider;

    CellTable<BookDto> cellTable = new CellTable<>();

    SimplePager pager;

    private final static int DEFAULT_PAGE_SIZE = 4;

    public BookTableView() {
        init();
    }

    public void init() {

        initTableColumns(cellTable);
        initDataProvider(cellTable);
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
                return object.getAuthors();
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
        dataProvider = new BookTableModel();
        dataProvider.addDataDisplay(table);

        table.setEmptyTableWidget(new Label("No Data"));
    }

    private void addColumnSorting(CellTable<BookDto> table) {
        ColumnSortEvent.AsyncHandler handler = new ColumnSortEvent.AsyncHandler(cellTable)  {
            @Override
            public void onColumnSort(ColumnSortEvent event) {
/*                Window.alert("Column sort event");*/
                String name = event.getColumn().getDataStoreName();
                if (event.isSortAscending()) {
                    dataProvider.setSorting(name,"asc");
                } else {
                    dataProvider.setSorting(name,"desc");
                }
                dataProvider.refresh();
            }
        };
        table.addColumnSortHandler(handler);

    }

    private void addPagination(CellTable<BookDto> table) {
        this.pager = new SimplePager();
        pager.setDisplay(table);
        pager.setPageSize(DEFAULT_PAGE_SIZE);
    }

    public void saveBook(BookDto bookDto) {
        dataProvider.save(bookDto);
    }

    public void deleteBook(BookDto bookDto) {
        dataProvider.delete(bookDto);
        cellTable.redraw();
    }

}




