package com.example.challengerooftop.repository;

import com.example.challengerooftop.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IResultDao extends JpaRepository<Result, Integer> {
}
