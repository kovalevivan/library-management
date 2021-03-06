package com.ikoval.libman.client.ui;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.ikoval.libman.client.model.BookTableModel;
import com.ikoval.libman.shared.dto.BookDto;

public class BookTableView extends Composite {

    private BookTableModel dataProvider;

    private CellTable<BookDto> cellTable = new CellTable<>();

    private SimplePager pager;

    private final static int DEFAULT_PAGE_SIZE = 10;

    public BookTableView(final BookTableModel bookTableModel) {
        dataProvider = bookTableModel;
        dataProvider.addDataDisplay(cellTable);
        cellTable.setEmptyTableWidget(new Label("No Data"));
        cellTable.setAutoHeaderRefreshDisabled(true);
        cellTable.setAutoFooterRefreshDisabled(true);
        cellTable.setWidth("100%",true);
        init();
    }

    private void init() {

        initTableColumns(cellTable);
        addColumnSorting(cellTable);
        addPagination(cellTable);

        VerticalPanel panel = new VerticalPanel();
        panel.add(cellTable);
        panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        panel.add(pager);
        initWidget(panel);

        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
            }
        });
    }

    private void initTableColumns(final CellTable<BookDto> table){

        table.setWidth("100%");
        table.setAutoHeaderRefreshDisabled(true);
        table.setAutoFooterRefreshDisabled(true);

        //Title
        TextColumn<BookDto> titleColumn = new TextColumn<BookDto>() {
            @Override
            public String getValue(final BookDto object) { return object.getTitle(); }
        };
        titleColumn.setDataStoreName("title");
        titleColumn.setSortable(true);
        table.addColumn(titleColumn, "Title");
        table.setColumnWidth(titleColumn,40, Style.Unit.PCT);

        //Authors
        TextColumn<BookDto> authorsColumn = new TextColumn<BookDto>() {
            @Override
            public String getValue(final BookDto object) {
                return object.getAuthors();
            }
        };
        authorsColumn.setDataStoreName("authors");
/*        authorsColumn.setSortable(true);*/
        table.addColumn(authorsColumn, "Authors");
        table.setColumnWidth(authorsColumn,25, Style.Unit.PCT);

        //Number of pages
        TextColumn<BookDto> numberOfPagesColumn = new TextColumn<BookDto>() {
            @Override
            public String getValue(final BookDto object) { return object.getPages().toString(); }
        };
        numberOfPagesColumn.setSortable(true);
        numberOfPagesColumn.setDataStoreName("pages");
        table.addColumn(numberOfPagesColumn , "Pages");
        table.setColumnWidth(numberOfPagesColumn,8, Style.Unit.PCT);

        //Publisher
        TextColumn<BookDto> publisher = new TextColumn<BookDto>() {
            @Override
            public String getValue(final BookDto object) { return object.getPublisher(); }
        };
        publisher.setDataStoreName("publisher");
        publisher.setSortable(true);
        table.addColumn(publisher , "Publisher");
        table.setColumnWidth(publisher,15, Style.Unit.PCT);

        //Year of publishing
        TextColumn<BookDto> yearOfPublishing = new TextColumn<BookDto>() {
            @Override
            public String getValue(final BookDto object) { return object.getYearOfPublishing().toString(); }
        };
        yearOfPublishing.setDataStoreName("yearOfPublishing");
        yearOfPublishing.setSortable(true);
        table.addColumn(yearOfPublishing , "Year of publication");
        table.setColumnWidth(yearOfPublishing,10, Style.Unit.PCT);

        //Genres
        TextColumn<BookDto> genres = new TextColumn<BookDto>() {
            @Override
            public String getValue(final BookDto object) { return object.getGenres(); }
        };
        genres.setDataStoreName("genres");
/*        genres.setSortable(true);*/
        table.addColumn(genres , "Genres");
        table.setColumnWidth(genres,15, Style.Unit.PCT);

        //Date when book was added to database
        TextColumn<BookDto> addedDate = new TextColumn<BookDto>() {
            @Override
            public String getValue(final BookDto object) { return object.getAddedDate(); }
        };
        addedDate.setSortable(true);
        addedDate.setDataStoreName("addedDate");
        table.addColumn(addedDate , "Updated");
        table.setColumnWidth(addedDate,13, Style.Unit.PCT);

        //Delete button
        Column<BookDto, String> deleteButton = new Column<BookDto, String>(new ButtonCell()) {
            @Override
            public String getValue(final BookDto object) { return "x"; }
        };
        table.addColumn(deleteButton , "");
        table.setColumnWidth(deleteButton,50,Style.Unit.PX);
        deleteButton.setFieldUpdater(new FieldUpdater<BookDto, String>() {
            @Override
            public void update(final int index, final BookDto object, final String value) {
                dataProvider.delete(object);
            }
        });

    }

    private void addColumnSorting(final CellTable<BookDto> table) {
        ColumnSortEvent.AsyncHandler handler = new ColumnSortEvent.AsyncHandler(cellTable)  {
            @Override
            public void onColumnSort(final ColumnSortEvent event) {
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

    private void addPagination(final CellTable<BookDto> table) {
        this.pager = new SimplePager();
        pager.setDisplay(table);
        pager.setPageSize(DEFAULT_PAGE_SIZE);
    }

    private void refresh() {

    }

}




