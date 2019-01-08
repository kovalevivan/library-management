package com.ikoval.libman.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.ikoval.libman.client.model.BookTableModel;
import com.ikoval.libman.shared.dto.BookDto;

public class AdvancedSearchDialogBox extends DialogBox {

    BookTableModel bookTableModel;

    BookDto filter;

    private VerticalPanel dialogVPanel = new VerticalPanel();

    private TextBox title= new TextBox();
    private TextBox author = new TextBox();
    private TextBox yearOfPublish = new TextBox();

    Button closeButton = new Button("Close");
    Button searchButton = new Button("Search");
    Button clearButton = new Button("Clear");

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
        addEvents();
        setWidget(dialogVPanel);
    }

    private void addEvents() {
        searchButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if(title.getText() != "") {
                    filter.setTitle(title.getText());
                }
                if(author.getText() != "") {
                    filter.setAuthors(author.getText());
                }
                if(yearOfPublish.getText() != "") {
                    filter.setYearOfPublishing(Integer.parseInt(yearOfPublish.getText()));
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
                yearOfPublish.setValue("");
                bookTableModel.clearFilter();
            }
        });
    }

    private void createFields() {
        dialogVPanel.add(new HTML("<b>Title:</b>"));
        dialogVPanel.add(title);
        if(filter.getTitle() != null) {
            title.setText(filter.getTitle());
        }
        dialogVPanel.add(new HTML("<b>Author:</b>"));
        dialogVPanel.add(author);
        if(filter.getAuthors() != null) {
            author.setText(filter.getAuthors());
        }
        dialogVPanel.add(new HTML("<b>Year of publish:</b>"));
        dialogVPanel.add(yearOfPublish);
        if(filter.getYearOfPublishing() !=null) {
            yearOfPublish.setText(filter.getYearOfPublishing().toString());
        }
        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_LEFT);
        dialogVPanel.add(searchButton);
        dialogVPanel.add(clearButton);
        dialogVPanel.add(closeButton);
    }
}
