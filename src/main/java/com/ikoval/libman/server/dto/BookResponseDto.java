package com.ikoval.libman.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class BookResponseDto {

    private Long id;
    private String title;
    private List<String> genres;
    private List<String> authors;
}
