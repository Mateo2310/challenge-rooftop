package com.example.challengerooftop.service;

import com.example.challengerooftop.entity.Result;

import java.util.List;
import java.util.Map;

public interface IResultService extends IDefaultService<Result> {
    Result findById(Integer id);
    List<Result> mapToResult(Map<String, Integer> results);
}
