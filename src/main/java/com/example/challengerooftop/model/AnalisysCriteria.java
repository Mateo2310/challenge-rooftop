package com.example.challengerooftop.model;

public class AnalisysCriteria extends PageCriteria{

    private int chars = 2;

    public AnalisysCriteria(int chars) {
        super();
        this.chars = chars;
    }

    public int getChars() {
        return chars;
    }

    public void setChars(int chars) {
        this.chars = chars;
    }
}
