package com.example.challengerooftop.service;

import com.example.challengerooftop.entity.Result;
import com.example.challengerooftop.exception.NotFoundTextException;
import com.example.challengerooftop.model.ResultCriteria;
import com.example.challengerooftop.repository.IResultDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public void deleteEntity(Integer id) {

    }

    @Override
    public Result findById(Integer id) {
        return resultDao.findById(id).orElseThrow(() -> new NotFoundTextException("Id "+id+" not found"));
    }

    @Override
    public List<Result> mapToResult(List<ResultCriteria> results) {
        List<Result> resultList = new ArrayList<>();
        results.forEach((result)-> resultList.add(
                new Result(result.getSearchWord(), result.getMatchCount(), result.getPosition())));
        resultDao.saveAll(resultList);
        return resultList;
    }
}
