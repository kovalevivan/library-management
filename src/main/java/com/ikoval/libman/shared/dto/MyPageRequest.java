package com.ikoval.libman.shared.dto;


import com.ikoval.libman.shared.FilterCriteria;

public class MyPageRequest {


    private Integer start;
    private Integer page = 0;
    private Integer size = 4;
    private String direction;
    private String property;
    private FilterCriteria filter;

    public MyPageRequest() {
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public FilterCriteria getFilter() {
        return filter;
    }

    public void setFilter(FilterCriteria filter) {
        this.filter = filter;
    }
}
