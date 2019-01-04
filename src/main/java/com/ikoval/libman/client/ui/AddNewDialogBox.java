package com.ikoval.libman.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Button;
import com.ikoval.libman.shared.dto.BookDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddNewDialogBox extends DialogBox {

    private BookTableView bookTableView;


    private VerticalPanel dialogVPanel = new VerticalPanel();

    private Button closeButton = new Button("Close");
    private Button saveButton = new Button("Save");

    private TextBox title= new TextBox();
    private TextBox publisher = new TextBox();
    private TextBox author = new TextBox();
    private TextBox pages = new TextBox();
    private TextBox yearOfPublish = new TextBox();


    public AddNewDialogBox(BookTableView bookTableView) {
        this.bookTableView = bookTableView;
        setText("Add new Book");
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

    private void createFields() {
        dialogVPanel.add(new HTML("<b>Title:</b>"));
        dialogVPanel.add(title);
        dialogVPanel.add(new HTML("<b>Author:</b>"));
        dialogVPanel.add(author);
        dialogVPanel.add(new HTML("<b>Publisher:</b>"));
        dialogVPanel.add(publisher);
        dialogVPanel.add(new HTML("<b>Pages:</b>"));
        dialogVPanel.add(pages);
        dialogVPanel.add(new HTML("<b>Year of publish:</b>"));
        dialogVPanel.add(yearOfPublish);
        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_LEFT);
        dialogVPanel.add(saveButton);
        dialogVPanel.add(closeButton);
    }

    void addEvents() {
        saveButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                BookDto bookDto = new BookDto();
                bookDto.setTitle(title.getText());
                bookDto.setPages(Integer.parseInt(pages.getText()));
                bookDto.setPublisher(publisher.getText());
                bookDto.setYearOfPublishing(Integer.parseInt(yearOfPublish.getText()));
                bookDto.setAuthors(author.getText());
                Date date = new Date();
                DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
                bookDto.setAddedDate(dateTimeFormat.format(date));
/*                Window.alert(bookDto.toString());*/
                bookTableView.saveBook(bookDto);
                hide();
            }
        });
    }

}
