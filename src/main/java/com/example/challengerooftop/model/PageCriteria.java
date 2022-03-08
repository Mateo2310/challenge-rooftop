package com.example.challengerooftop.model;

public class PageCriteria {
    private int page = 1;
    private int rpp = 10;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRpp() {
        return rpp;
    }

    public void setRpp(int rpp) {
        this.rpp = rpp;
    }
}
