package com.example.challengerooftop.service;

import com.example.challengerooftop.entity.Result;
import com.example.challengerooftop.repository.IResultDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ResultServiceImpl implements IResultService{

    private final IResultDao resultDao;

    public ResultServiceImpl(IResultDao resultDao) {
        this.resultDao = resultDao;
    }

    @Override
    public Result createEntity(Result entity) {
        resultDao.createEntity(entity);
        return entity;
    }

    @Override
    public void deleteEntity(String id) {

    }

    @Override
    public Result findById(Integer id) {
        return resultDao.findById(id);
    }

    @Override
    public List<Result> mapToResult(Map<String, Integer> results) {
        List<Result> resultList = new ArrayList<>();
        results.forEach((k, v)-> resultList.add(resultDao.createEntity(new Result(k, v))));
        return resultList;
    }

    @Override
    public List<Result> findAll() {
        return null;
    }
}
