package com.ikoval.libman.client.dao;

import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;

public class AsyncDataProviderImpl<T> extends AsyncDataProvider<T> {
    @Override
    protected void onRangeChanged(HasData<T> display) {
        final Range range = display.getVisibleRange();
    }
}
