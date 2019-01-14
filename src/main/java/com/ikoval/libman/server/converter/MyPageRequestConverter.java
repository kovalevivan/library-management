package com.ikoval.libman.server.converter;

import com.ikoval.libman.shared.dto.MyPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

public class MyPageRequestConverter {


    public  static PageRequest convert(MyPageRequest request) {
        int size = request.getSize();
        int page = request.getPage();
        Sort.Direction direction =
                Sort.Direction.fromString(request.getDirection());
        String property = request.getProperty();

        return PageRequest.of(
                page,
                size,
                direction,
                property);
    }
}
