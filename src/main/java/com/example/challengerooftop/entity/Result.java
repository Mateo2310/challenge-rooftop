package com.example.challengerooftop.entity;

import javax.persistence.*;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String searchWord;

    private Integer matchCount;

    private Integer position;

    public Result(String searchWord, Integer matchCount) {
        this.searchWord = searchWord;
        this.matchCount = matchCount;
    }

    public Result() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String matchCount) {
        this.searchWord = searchWord;
    }

    public Integer getMatchCount() {
        return matchCount;
    }

    public void setMatchCount(Integer matchCount) {
        this.matchCount = matchCount;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
