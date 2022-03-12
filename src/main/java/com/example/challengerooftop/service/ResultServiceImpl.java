package com.example.challengerooftop.service;

import com.example.challengerooftop.entity.Result;
import com.example.challengerooftop.execption.NotFound;
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
        resultDao.save(entity);
        return entity;
    }

    @Override
    public void deleteEntity(String id) {

    }

    @Override
    public Result findById(Integer id) {
        return resultDao.findById(id).orElseThrow(() -> new NotFound("Id "+id+" not found"));
    }

    @Override
    public List<Result> mapToResult(Map<String, Integer> results) {
        List<Result> resultList = new ArrayList<>();
        results.forEach((k, v)-> resultList.add(new Result(k, v)));
        resultDao.saveAll(resultList);
        return resultList;
    }

    @Override
    public List<Result> findAll() {
        return null;
    }
}
