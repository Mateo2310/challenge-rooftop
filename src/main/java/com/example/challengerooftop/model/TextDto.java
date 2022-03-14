package com.example.challengerooftop.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class TextDto {

    private Integer id;
    private String hash;
    private Integer chars;
    private Map<String, Object> results = new LinkedHashMap<>();

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

    public Map<String, Object> getResults() {
        return results;
    }

    public void setResults(Map<String, Object> results) {
        this.results = results;
    }
}
