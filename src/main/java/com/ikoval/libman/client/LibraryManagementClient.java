package com.ikoval.libman.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.ikoval.libman.client.model.BookTableModel;
import com.ikoval.libman.client.ui.*;
import com.vaadin.polymer.Polymer;

import java.util.Arrays;

public class LibraryManagementClient implements EntryPoint {

/*  private VerticalPanel mainPanel = new VerticalPanel();
  private HorizontalPanel addPanel = new HorizontalPanel();

  private BookTableModel bookTableModel = new BookTableModel();

  private BookTableView bookTableView = new BookTableView(bookTableModel);

  private Button addButton = new Button("Add new");
  private Button advancedSearch = new Button("Filter");

  private MainMenuBar menuBar = new MainMenuBar(bookTableModel);*/

  public void onModuleLoad() {
/*    addPanel.add(menuBar);
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
    });*/

    Polymer.startLoading();

    Polymer.importHref(Arrays.asList(
            // Paper applications must always import paper-styles
            "paper-styles",
            // Styles for paper examples, we use it for this app as well
            "paper-styles/demo-pages.html",
            // We have to load icon sets before run Application
            "iron-icons/iron-icons.html",
            "iron-icons/communication-icons.html",
            "neon-animation/neon-animations.html"
    ));

    Polymer.whenReady(o -> {
      // The app is executed when all imports succeed.
      startApplication();
      return null;
    });

  }

  private void startApplication() {
    Main main = new Main();
    main.addElement(new BookTableView(new BookTableModel()));
    RootPanel.get().add(main);
  }







}
