package com.ikoval.libman.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.ikoval.libman.client.model.BookTableModel;
import com.ikoval.libman.shared.FilterCriteria;
import com.ikoval.libman.shared.dto.BookDto;

public class AdvancedSearchDialogBox extends DialogBox {

    private final String AUTHOR_DUMMY = "Not working yet";

    BookTableModel bookTableModel;

    FilterCriteria filter;

    private VerticalPanel dialogVPanel = new VerticalPanel();

    private TextBox title= new TextBox();
    private TextBox author = new TextBox();
    private TextBox genre = new TextBox();

    private Button closeButton = new Button("Close");
    private Button searchButton = new Button("Search");
    private Button clearButton = new Button("Clear");



    public AdvancedSearchDialogBox(BookTableView bookTableView) {
        this.bookTableModel = bookTableView.dataProvider;
        filter = bookTableModel.getFilter();
        setText("Advanced Search");
        setAnimationEnabled(true);

        closeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                hide();
            }
        });
        createFields();
/*        Window.alert(filter.toString());*/
        setInitialData();
        addEvents();
        setWidget(dialogVPanel);
    }

    private void setInitialData() {
        if (filter.getBookTitle() != null) {
            title.setText(filter.getBookTitle());
        }

        if (filter.getAuthorName() != null) {
            author.setText(filter.getAuthorName());
        }

        if (filter.getGenre() != null) {
            genre.setText(filter.getGenre());
        }
    }

    private void addEvents() {
        searchButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                filter.clear();
                if(title.getText() != "") {
                    filter.setBookTitle(title.getText());
                }
                if(author.getText() != "") {
                    filter.setAuthorName(author.getText());
                }
                if(genre.getText() != "") {
                    filter.setGenre(genre.getText());
                }
                bookTableModel.setFilter(filter);
                hide();
            }
        });

        clearButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                bookTableModel.clearFilter();
                title.setValue("");
                author.setValue("");
                genre.setValue("");
                bookTableModel.clearFilter();
            }
        });
    }

    private void createFields() {

        dialogVPanel.add(new HTML("<b>Title:</b>"));
        dialogVPanel.add(title);

        dialogVPanel.add(new HTML("<b>Genre:</b>"));
        dialogVPanel.add(genre);

        dialogVPanel.add(new HTML("<b>Author:</b>"));
        dialogVPanel.add(author);

        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_LEFT);
        dialogVPanel.add(searchButton);
        dialogVPanel.add(clearButton);
        dialogVPanel.add(closeButton);
    }
}
