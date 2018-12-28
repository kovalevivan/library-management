package com.ikoval.libman.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Button;
import com.ikoval.libman.shared.BookResponseDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddNewDialogBox extends DialogBox {

    BookTableController bookTableController;


    VerticalPanel dialogVPanel = new VerticalPanel();

    Button closeButton = new Button("Close");
    Button saveButton = new Button("Save");

    TextBox id = new TextBox();
    TextBox title= new TextBox();
    TextBox publisher = new TextBox();
    TextBox author = new TextBox();
    TextBox pages = new TextBox();
    TextBox yearOfPublish = new TextBox();


    public AddNewDialogBox(BookTableController bookTableController) {
        this.bookTableController = bookTableController;
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
        dialogVPanel.add(new HTML("<b>Id:</b>"));
        dialogVPanel.add(id);
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
                BookResponseDto bookResponseDto = new BookResponseDto();
                bookResponseDto.setId(Long.parseLong(id.getText()));
                bookResponseDto.setTitle(title.getText());
                bookResponseDto.setPages(Integer.parseInt(pages.getText()));
                bookResponseDto.setPublisher(publisher.getText());
                bookResponseDto.setYearOfPublishing(Integer.parseInt(yearOfPublish.getText()));
                List<String> authors = new ArrayList<>();
                authors.add(author.getText());
                bookResponseDto.setAuthors(authors);
                Date date = new Date();
                DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
                bookResponseDto.setAddedDate(dateTimeFormat.format(date));
                Window.alert(bookResponseDto.toString());
                bookTableController.saveBook(bookResponseDto);
                hide();
            }
        });
    }

}