package com.ikoval.libman.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.ikoval.libman.client.model.BookTableModel;

public class MainMenuBar extends Composite {

    private MenuBar menu;

    private BookTableModel bookTableModel;

    public MainMenuBar(BookTableModel bookTableModel) {
        this.bookTableModel = bookTableModel;
        initMenuBar();
    }

    private void initMenuBar() {
        // Create a menu bar
        menu = new MenuBar();
        menu.setAutoOpen(true);
        menu.setWidth("500px");
        menu.setAnimationEnabled(true);


        initWidget(menu);
    }
}
