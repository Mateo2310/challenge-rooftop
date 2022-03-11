package com.example.challengerooftop.model;

import java.util.HashMap;
import java.util.Map;

public class TextResponseDto {

    private Integer id;
    private String hash;
    private Integer chars;
    private Map<String, Integer> results = new HashMap<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getChars() {
        return chars;
    }

    public void setChars(Integer chars) {
        this.chars = chars;
    }

    public Map<String, Integer> getResults() {
        return results;
    }

    public void setResults(Map<String, Integer> results) {
        this.results = results;
    }
}
