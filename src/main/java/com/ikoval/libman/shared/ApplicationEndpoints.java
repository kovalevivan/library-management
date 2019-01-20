package com.ikoval.libman.shared;

public class ApplicationEndpoints {

    public static final String ROOT_PATH = "/libman";

    private static final String API = "/api";

    public static final String SAVE_BOOK = API + "/book/save";
    public static final String DELETE_BOOK = API + "/book/delete";
    public static final String UPDATE_BOOK = API + "/book/update";
    public static final String GET_BOOK = API + "/book/{id}";
    public static final String FIND_BOOKS = API + "/books";
}
