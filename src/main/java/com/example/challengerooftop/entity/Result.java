package com.example.challengerooftop.entity;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    private String searchWord;

    @NonNull
    private Integer matchCount;

    public Result(@NonNull String searchWord, @NonNull Integer matchCount) {
        this.searchWord = searchWord;
        this.matchCount = matchCount;
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
}
