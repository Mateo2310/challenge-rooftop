package com.example.challengerooftop.service;

import java.util.List;

public interface IDefaultService<T> {
    T createEntity(T entity);
    void deleteEntity(Integer id);
}
