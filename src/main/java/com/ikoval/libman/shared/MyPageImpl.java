package com.ikoval.libman.shared;

import java.util.List;

public class MyPageImpl<T> {

    List<T> content;

    public MyPageImpl() {
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
