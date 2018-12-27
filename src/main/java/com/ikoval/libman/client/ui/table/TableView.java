package com.ikoval.libman.client.ui.table;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.ikoval.libman.shared.BookResponseDto;

public class TableView extends Composite {

    interface TableUiBinder extends UiBinder<Widget, TableView> {}

    private static TableUiBinder uiBinder = GWT.create(TableUiBinder.class);

    @UiField(provided = true)
    DataGrid<BookResponseDto> dataGrid;

/*    @UiField(provided = true)
    SimplePager pager;*/

    public TableView() {
        dataGrid = new DataGrid<>();
        dataGrid.setWidth("100%");
        dataGrid.setAutoHeaderRefreshDisabled(true);
        dataGrid.setEmptyTableWidget(new Label("My table"));
        initTableColumns();
        Window.alert("Hello");
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void initTableColumns() {
        Column<BookResponseDto, String> checkColumn = new Column<BookResponseDto, String>(new TextCell()) {
            @Override
            public String getValue(BookResponseDto object) {
                return object.getTitle();
            }
        };
        dataGrid.addColumn(checkColumn);
    }


}
