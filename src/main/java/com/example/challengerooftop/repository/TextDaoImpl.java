package com.example.challengerooftop.repository;

import com.example.challengerooftop.entity.Text;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TextDaoImpl implements ITextDao{
    @Override
    public void createText(Text text) {

    }

    @Override
    public void deleteText(String id) {

    }

    @Override
    public Text findById(String id) {
        return null;
    }

    @Override
    public List<Text> findText() {
        return null;
    }

    @Override
    public Integer findByHash(String hash) {
        return null;
    }
}
