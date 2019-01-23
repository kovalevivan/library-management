package com.ikoval.libman.server.converter;

import com.ikoval.libman.shared.dto.MyPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class MyPageRequestConverter {

    private final static int DEFAULT_SIZE = 10;
    private final static int DEFAULT_PAGE = 0;
    private final static String DEFAULT_DIRECTION = "DESC";
    private final static String DEFAULT_PROPERTY = "addedDate";

    /**
     * Convert {@link MyPageRequest} to {@link PageRequest}.
     * Default values are used when fields of {@link MyPageRequest} equals null
     *
     * @param request must not be {@literal null}.
     * @return instance of org.springframework.data.domain.PageRequest
     */


    public  static PageRequest convert(final MyPageRequest request) {
        int size = request.getSize() != null ? request.getSize() : DEFAULT_SIZE;
        int page = request.getPage() != null ? request.getPage() : DEFAULT_PAGE;
        String directionStr = request.getDirection() != null ? request.getDirection() : DEFAULT_DIRECTION;
        Sort.Direction direction =
                Sort.Direction.fromString(directionStr);
        String property = request.getProperty() != null ? request.getProperty() : DEFAULT_PROPERTY;

        return PageRequest.of(
                page,
                size,
                direction,
                property);
    }
}
