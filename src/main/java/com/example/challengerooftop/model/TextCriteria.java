package com.example.challengerooftop.model;

import java.io.Serializable;

public class TextCriteria implements Serializable {

    private String text;
    private Integer chars = 2;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getChars() {
        return chars;
    }

    public void setChars(Integer chars) {
        this.chars = chars;
    }
}
