package com.ikoval.libman.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.ikoval.libman.client.model.BookTableModel;
import com.ikoval.libman.client.ui.AddNewDialogBox;
import com.ikoval.libman.client.ui.AdvancedSearchDialogBox;
import com.ikoval.libman.client.ui.BookTableView;

public class LibraryManagementClient implements EntryPoint {

  private VerticalPanel mainPanel = new VerticalPanel();
  private HorizontalPanel addPanel = new HorizontalPanel();

  private BookTableModel bookTableModel = new BookTableModel();

  private BookTableView bookTableView = new BookTableView(bookTableModel);

  private Button addButton = new Button("Add new");
  private Button advancedSearch = new Button("Filter");

  public void onModuleLoad() {
    addPanel.add(addButton);
    addPanel.add(advancedSearch);
    mainPanel.add(addPanel);
    mainPanel.add(bookTableView);
    RootPanel.get("gwtContainer").add(mainPanel);
    addButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(final ClickEvent event) {
        new AddNewDialogBox(bookTableModel).center();
      }
    });
    advancedSearch.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(final ClickEvent event) {
        new AdvancedSearchDialogBox(bookTableModel).center();
      }
    });

    Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
      @Override
      public void execute() {
      }
    });

  }







}
