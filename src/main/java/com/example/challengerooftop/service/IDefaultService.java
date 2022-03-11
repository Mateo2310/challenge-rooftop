package com.example.challengerooftop.service;

import com.example.challengerooftop.entity.Text;
import com.example.challengerooftop.model.TextDto;

import java.util.List;
import java.util.Map;

public interface IDefaultService<T> {
    T createEntity(T entity);
    void deleteEntity(String id);
    List<T> findAll();
}
