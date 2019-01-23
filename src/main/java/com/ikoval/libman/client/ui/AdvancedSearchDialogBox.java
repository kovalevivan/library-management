package com.ikoval.libman.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.ikoval.libman.client.model.BookTableModel;
import com.ikoval.libman.shared.FilterCriteria;

public class AdvancedSearchDialogBox extends DialogBox {


    private BookTableModel bookTableModel;

    private FilterCriteria filter;

    private VerticalPanel dialogVPanel = new VerticalPanel();

    private TextBox title= new TextBox();
    private TextBox author = new TextBox();
    private TextBox genre = new TextBox();

    private Button closeButton = new Button("Close");
    private Button searchButton = new Button("Apply");
    private Button clearButton = new Button("Disable");



    public AdvancedSearchDialogBox(final BookTableModel bookTableModel) {
        this.bookTableModel = bookTableModel;
        filter = this.bookTableModel.getFilter();
        setText("Advanced Search");
        setAnimationEnabled(true);

        closeButton.addClickHandler(new ClickHandler() {
            public void onClick(final ClickEvent event) {
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
            public void onClick(final ClickEvent event) {
                filter.clear();
                if(!title.getText().isEmpty()) {
                    filter.setBookTitle(title.getText());
                }
                if(!author.getText().isEmpty()) {
                    filter.setAuthorName(author.getText());
                }
                if(!genre.getText().isEmpty()) {
                    filter.setGenre(genre.getText());
                }
                bookTableModel.setFilter(filter);
/*                hide();*/
            }
        });

        clearButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
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
