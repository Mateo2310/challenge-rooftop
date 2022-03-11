package com.example.challengerooftop.service;

import com.example.challengerooftop.entity.Text;
import com.example.challengerooftop.model.TextDto;
import com.example.challengerooftop.model.TextResponseDto;

import java.util.Map;

public interface ITextService extends IDefaultService<Text> {
    Map<String, Object> analyzerText(TextDto dto);
    Map<String, Object> findByHash(String hash);
    TextResponseDto findById(Integer id);
}
