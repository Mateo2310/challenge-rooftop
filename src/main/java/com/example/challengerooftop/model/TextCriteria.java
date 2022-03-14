package com.example.challengerooftop.model;

import java.io.Serializable;

public class TextCriteria implements Serializable {

    private String searchWord;
    private Integer chars = 2;

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public Integer getChars() {
        return chars;
    }

    public void setChars(Integer chars) {
        this.chars = chars;
    }
}
