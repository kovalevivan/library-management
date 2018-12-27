package com.ikoval.libman.client.ui.login;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface LoginResources extends ClientBundle {

    public interface MyCss extends CssResource {
        String blackText();
        String redText();
        String loginButton();
        String box();
        String background();
    }
    @Source("login.css")
    MyCss style();
}
