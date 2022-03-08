package com.example.challengerooftop.service;

import com.example.challengerooftop.entity.Text;

import java.util.List;

public interface ITextService {
    void createText(Text text);
    void deleteText(String id);
    Text findById(String id);
    List<Text> findText();
    String analyseText(String text, int chars);
}
