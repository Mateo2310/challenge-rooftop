package com.example.challengerooftop.repository;

import com.example.challengerooftop.entity.Text;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TextDaoImpl implements ITextDao {

    private final JdbcTemplate jdbcTemplate;

    public TextDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Text createEntity(Text text) {
        String query = "INSERT INTO text(hash, chars, results)values(?, ?, ?)";
        int response = jdbcTemplate.update(query, text);
        if (response == 1){
            return text;
        }
        return null;
    }

    @Override
    public void deleteEntity(String id) {

    }

    @Override
    public Text findById(Integer id) {
        return null;
    }

    @Override
    public List<Text> findAll() {
        String sql = "SELECT * FROM Text";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Text.class));
    }

    @Override
    public Integer findByHash(String hash) {
        return null;
    }
}
