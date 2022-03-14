package com.example.challengerooftop.repository;

import com.example.challengerooftop.entity.Text;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface ITextDao extends JpaRepository<Text, Integer> {

    @Query(value = "SELECT t.id FROM Text t WHERE t.hash = :hash AND t.deleted = false", nativeQuery = true)
    Integer findByHash(String hash);

    @Query(value = "SELECT * FROM Text t WHERE t.chars = :chars AND t.deleted = false", nativeQuery = true)
    Page<Text> findAllByChars(Pageable pageable, Integer chars);

    @Query(value = "SELECT * FROM Text t WHERE t.id = :id AND t.deleted = false", nativeQuery = true)
    Optional<Text> findById(Integer id);
}
