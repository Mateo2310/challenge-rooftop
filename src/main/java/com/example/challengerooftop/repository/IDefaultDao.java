package com.example.challengerooftop.repository;

import java.util.List;

public interface IDefaultDao<T> {
    T createEntity(T object);
    void deleteEntity(String id);
    T findById(Integer id);
    List<T> findAll();
}
