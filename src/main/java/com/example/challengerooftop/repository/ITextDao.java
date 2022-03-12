package com.example.challengerooftop.repository;

import com.example.challengerooftop.entity.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ITextDao extends JpaRepository<Text, Integer> {

    @Query(value = "SELECT t.id FROM Text t WHERE t.hash = :hash", nativeQuery = true)
    Integer findByHash(String hash);
}
