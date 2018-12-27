package com.ikoval.libman.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.ikoval.libman.shared.BookResponseDto;


public class MainView extends Composite {

	private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);
	interface MainViewUiBinder extends UiBinder<Widget, MainView> {}
	
	@UiField TextBox name, street, street2, city, providence;
	@UiField Button addButton, sendButton, loadButton;
	@UiField ComplexPanel panel;
	
	
	public MainView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("addButton")
	void onAddClicked(ClickEvent e){
	}
	
	@UiHandler("sendButton")
	void onSendtoServerClicked(ClickEvent e) {

	}
	
	@UiHandler("loadButton")
	void onLoadClicked(ClickEvent e){


	}
	
	private void addPerson(BookResponseDto p) {

	}
	
	private void clear(TextBox... fields){

	}

}
