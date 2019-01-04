package com.ikoval.libman.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

public class AdvancedSearchDialogBox extends DialogBox {

    private VerticalPanel dialogVPanel = new VerticalPanel();

    private TextBox title= new TextBox();
    private TextBox author = new TextBox();
    private TextBox yearOfPublish = new TextBox();

    Button closeButton = new Button("Close");
    Button searchButton = new Button("Search");

    public AdvancedSearchDialogBox() {
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

    }

    private void createFields() {
        dialogVPanel.add(new HTML("<b>Title:</b>"));
        dialogVPanel.add(title);
        dialogVPanel.add(new HTML("<b>Author:</b>"));
        dialogVPanel.add(author);
        dialogVPanel.add(new HTML("<b>Year of publish:</b>"));
        dialogVPanel.add(yearOfPublish);
        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_LEFT);
        dialogVPanel.add(searchButton);
        dialogVPanel.add(closeButton);
    }
}
