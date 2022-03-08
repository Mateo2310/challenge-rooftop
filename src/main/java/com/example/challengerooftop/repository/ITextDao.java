package com.example.challengerooftop.repository;

import com.example.challengerooftop.entity.Text;

import java.util.List;

public interface ITextDao {
    void createText(Text text);
    void deleteText(String id);
    Text findById(String id);
    List<Text> findText();
}
