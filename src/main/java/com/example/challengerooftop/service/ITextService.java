package com.example.challengerooftop.service;

import com.example.challengerooftop.entity.Text;
import com.example.challengerooftop.model.TextDto;

import java.util.List;
import java.util.Map;

public interface ITextService {
    Map<String, Object> createText(TextDto text);
    void deleteText(String id);
    Text findById(String id);
    List<Text> findText();
    Map<String, Object> findByHash(String hash);
}
