package com.ikoval.libman.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.ikoval.libman.client.ui.AddNewDialogBox;
import com.ikoval.libman.client.ui.BookTableView;

public class MyWebApp implements EntryPoint {

  private VerticalPanel mainPanel = new VerticalPanel();
  private HorizontalPanel addPanel = new HorizontalPanel();
  private BookTableView bookTableView = new BookTableView();

  Button addButton = new Button("Add new");

  public void onModuleLoad() {
    addPanel.add(addButton);
    mainPanel.add(addPanel);
    mainPanel.add(bookTableView);
    RootPanel.get("gwtContainer").add(mainPanel);
    addButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        new AddNewDialogBox(bookTableView).center();
      }
    });

  }







}
