package com.example.challengerooftop.model;

import java.util.LinkedList;
import java.util.List;

public class PaginatorTextDto {
    private Long totalElements;
    private Integer totalPages;
    private Integer sizePage;
    private List<TextDto> listTextDto = new LinkedList<>();

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getSizePage() {
        return sizePage;
    }

    public void setSizePage(Integer sizePage) {
        this.sizePage = sizePage;
    }

    public List<TextDto> getListTextDto() {
        return listTextDto;
    }

    public void setListTextDto(List<TextDto> listTextDto) {
        this.listTextDto = listTextDto;
    }
}
