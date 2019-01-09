package com.ikoval.libman.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.ikoval.libman.client.model.BookTableModel;
import com.ikoval.libman.shared.dto.BookDto;

public class AdvancedSearchDialogBox extends DialogBox {

    private final String AUTHOR_DUMMY = "Not working yet";

    BookTableModel bookTableModel;

    BookDto filter;

    private VerticalPanel dialogVPanel = new VerticalPanel();

    private CheckBox param = new CheckBox();
    private CheckBox authorParam = new CheckBox();
    private CheckBox genreParam = new CheckBox();

    private TextBox title= new TextBox();
    private TextBox author = new TextBox();
    private TextBox yearOfPublish = new TextBox();

    Button closeButton = new Button("Close");
    Button searchButton = new Button("Search");
    Button clearButton = new Button("Clear");



    public AdvancedSearchDialogBox(BookTableView bookTableView) {
        this.bookTableModel = bookTableView.dataProvider;
        if(bookTableModel.getFilter() == null) {
            filter = new BookDto();
        } else {
            filter = bookTableModel.getFilter();
        }
        setText("Advanced Search");
        setAnimationEnabled(true);

        closeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                hide();
            }
        });
        createFields();
        Window.alert(filter.toString());
        setInitialData();
        addEvents();
        setWidget(dialogVPanel);
    }

    private void setInitialData() {
        if (filter.getTitle() != null) {
            title.setText(filter.getTitle());
        }

        if (filter.getAuthors() != null) {
            author.setText(filter.getAuthors());
        }

        if (filter.getYearOfPublishing() != null) {
            yearOfPublish.setText(filter.getYearOfPublishing().toString());
        }
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
                filter = new BookDto();
            }
        });

/*        param.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                boolean isEnable = event.getValue();
                title.setEnabled(isEnable);
                yearOfPublish.setEnabled(isEnable);
*//*                authorParam.setValue(false, true);*//*
            }
        });

        authorParam.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                boolean isEnable = event.getValue();
                author.setEnabled(isEnable);
*//*                param.setValue(false,true);*//*
            }
        });*/
    }

    private void createFields() {
        dialogVPanel.add(new HTML("<b>Search by parameters:</b>"));
        dialogVPanel.add(param);
        dialogVPanel.add(new HTML("<b>Title:</b>"));
        dialogVPanel.add(title);
        dialogVPanel.add(new HTML("<b>Year of publish:</b>"));
        dialogVPanel.add(yearOfPublish);
        dialogVPanel.add(new HTML("<b>Author:</b>"));
        dialogVPanel.add(new HTML("<b>Search by author:</b>"));
        dialogVPanel.add(author);
        author.setEnabled(false);
        dialogVPanel.add(authorParam);
        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_LEFT);
        dialogVPanel.add(searchButton);
        dialogVPanel.add(clearButton);
        dialogVPanel.add(closeButton);
    }
}
