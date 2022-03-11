package com.example.challengerooftop.repository;

import com.example.challengerooftop.entity.Text;

public interface ITextDao extends IDefaultDao<Text> {
    Integer findByHash(String hash);
}
