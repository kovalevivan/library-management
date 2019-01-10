package com.ikoval.libman.shared.dto;


import com.ikoval.libman.shared.FilterCriteria;


public class MyPageRequest {

    private final static int DEFAULT_SIZE = 10;
    private final static int DEFAULT_PAGE = 0;
    private final static String DEFAULT_DIRECTION = "ASC";
    private final static String DEFAULT_PROPERTY = "id";


    private Integer start;
    private Integer page = DEFAULT_PAGE;
    private Integer size = DEFAULT_SIZE;
    private String direction = DEFAULT_DIRECTION;
    private String property = DEFAULT_PROPERTY;
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
