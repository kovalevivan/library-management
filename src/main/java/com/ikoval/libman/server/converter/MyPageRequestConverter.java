package com.ikoval.libman.server.converter;

import com.ikoval.libman.shared.dto.MyPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class MyPageRequestConverter {

    private final static int DEFAULT_SIZE = 100;
    private final static int DEFAULT_PAGE = 0;
    private final static Sort.Direction DEFAULT_DIRECTION = Sort.Direction.ASC;
    private final static String DEFAULT_PROPERTY = "id";

    public static PageRequest convert(MyPageRequest request) {
        int size = request.getSize() != null ? request.getSize() : DEFAULT_SIZE;
        int page = request.getPage() != null ? request.getPage() : DEFAULT_PAGE;
        Sort.Direction direction = request.getProperty() != null ?
                Sort.Direction.fromString(request.getDirection()) : DEFAULT_DIRECTION;
        String property = request.getProperty() != null ? request.getProperty() : DEFAULT_PROPERTY;

            return PageRequest.of(
                    page,
                    size,
                    direction,
                    property);
    }
}
