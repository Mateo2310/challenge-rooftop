package com.example.challengerooftop.service;

import com.example.challengerooftop.entity.Text;
import com.example.challengerooftop.model.AnalisysCriteria;
import com.example.challengerooftop.model.PaginatorTextDto;
import com.example.challengerooftop.model.TextCriteria;
import com.example.challengerooftop.model.TextDto;

import java.util.Map;

public interface ITextService extends IDefaultService<Text> {
    Map<String, Object> analyzerText(TextCriteria dto);
    TextDto findById(Integer id);
    PaginatorTextDto findAllPaginator(AnalisysCriteria criteria);
}
