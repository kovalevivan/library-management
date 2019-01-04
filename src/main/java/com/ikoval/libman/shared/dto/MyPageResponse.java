package com.ikoval.libman.shared.dto;

import java.util.List;

public class MyPageResponse<T> {

    private List<T> content;
    private Integer totalElements;

    public MyPageResponse() {
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    @Override
    public String toString() {
        return "MyPageResponse{" +
                "content=" + content +
                '}';
    }
}
