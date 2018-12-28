package com.ikoval.libman.shared;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableImpl {

    private String sort;
    private Integer page;
    private Integer size;

    public PageableImpl() {
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
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
}
