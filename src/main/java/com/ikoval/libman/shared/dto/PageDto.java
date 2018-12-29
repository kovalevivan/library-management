package com.ikoval.libman.shared.dto;

import java.util.List;

public class PageDto<T> {

    List<T> content;

    public PageDto() {
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PageDto{" +
                "content=" + content +
                '}';
    }
}
