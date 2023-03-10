package com.codestates.preproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageInfo {
    private Integer page;
    private Integer size;
    private Long totalElements;
    private Integer totalPages;
}
